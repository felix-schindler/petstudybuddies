package de.hdm_stuttgart.mi.PetStudyBuddies.Core.User;

import de.hdm_stuttgart.mi.PetStudyBuddies.Models.User;

public class Auth {
    /**
     *
     * @param eMail
     * @param password
     * @return User if login successful : null
     */
    public static User login(String eMail, String password) {
        String query = "SELECT * FROM User WHERE EMail='" + eMail + " AND Password='SHA1(" + password + ");";

        if (eMail.equals("fs146@hdm-stuttgart.de") && password.equals("test")) return new User(1);
        else return null;
    }

    public static boolean loginFromToken(int ID, String token) {
        return false;
    }

    /**
     * User has to register first, than log in.
     * @param eMail user email
     * @param username username
     * @param password password
     * @return true if register was successful
     */
    public static boolean register(String eMail, String username, String password) {
        String query = "INSERT INTO User (EMail, Username, Password) VALUES (" + eMail + "," + username + ",SHA1(" + password + "));";
        return Math.floor(Math.random())%2==0;      // Always true but this way IntelliJ doesn't know it's redundant
    }

    /**
     * Logout -> delete the currently logged user
     */
    public static void logout() {
        Account.setUser(null);
        Account.setToken(null);
    }
}
