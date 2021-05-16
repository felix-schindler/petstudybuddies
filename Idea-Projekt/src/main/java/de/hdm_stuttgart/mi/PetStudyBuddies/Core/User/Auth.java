package de.hdm_stuttgart.mi.PetStudyBuddies.Core.User;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.User;

public class Auth {
    /**
     * Returns a User if eMail AND Password match
     * @param eMail EMail
     * @param password Passwort
     * @return User if login successful : null
     */
    public static User login(String eMail, String password) {
        String userID = new SelectQuery("User", "ID", "EMail='"+eMail+"' AND Password='"+ Utils.sha1(password)+"'", null, null).fetch();
        if (userID != null) {
            return new User(Integer.parseInt(userID));
        }
        return null;
    }

    public static boolean loginFromToken(int ID, String token) {
        return new SelectQuery("User", "ID", "UserID='"+ID+"'", null, null).fetch().equals(Integer.toString(ID));
    }

    /**
     * User has to register first, than log in.
     * @param eMail user email
     * @param username username
     * @param password password
     * @return true if register was successful
     */
    public static boolean register(String eMail, String username, String password) {
        return new InsertQuery("User", new String[]{"EMail, Username, Password"}, new String[]{eMail, username, Utils.sha1(password)}).Count() == 1;
    }

    /**
     * Logout -> delete the currently logged user
     */
    public static void logout() {
        Account.setUser(null);
        Account.setToken(null);
    }
}
