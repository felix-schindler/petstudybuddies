package de.hdm_stuttgart.mi.PetStudyBuddies.Core;

import de.hdm_stuttgart.mi.PetStudyBuddies.Models.User;

public class Auth {
    private String eMail;
    private String username;
    private String password;
    private static String token;

    /**
     *
     * @param eMail
     * @param password
     * @return User if login successful : null
     */
    public static User login(String eMail, String password) {
        String query = "SELECT * FROM User WHERE EMail='" + eMail + " AND Password='SHA1(" + password + ");";
        return null;
    }

    public static User loginFromToken(String eMail, String token) {
        return null;
    }

    public static User register(String eMail, String username, String password) {
        String query = "INSERT INTO User (EMail, Username, Password) VALUES (" + eMail + "," + username + ",SHA1(" + password + "));";
        return null;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Auth.token = token;
    }
}
