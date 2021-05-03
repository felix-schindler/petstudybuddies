package de.hdm_stuttgart.mi.PetStudyBuddies.Core.User;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.User;

public class Auth {
    /**
     * Returns a User if eMail AND Password match
     * @param eMail
     * @param password
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
    /* TODO this is (probably) just another controller
    public static boolean register(String eMail, String username, String password) {
        String query = "INSERT INTO User (EMail, Username, Password) VALUES (" + eMail + "," + username + ",SHA1(" + password + "));";
        return Math.floor(Math.random())%2==0;      // Always true but this way IntelliJ doesn't know it's redundant
    }
    */

    /**
     * Logout -> delete the currently logged user
     */
    public static void logout() {
        Account.setUser(null);
        Account.setToken(null);
    }
}
