package de.hdm_stuttgart.mi.PetStudyBuddies.core;

public class User {
    private int ID;
    private String eMail;
    private String username;
    private String token;

    public static boolean login(String eMail, String password) {
        return false;
    }

    public static boolean loginFromToken(String eMail, String token) {
        return false;
    }

    public User() {

    }

    public static boolean register(String eMail, String password, String passwordRepeat) {
        return false;
    }

    public boolean changePassword(String oldPw, String newPw, String newPwRepeat) {
        return false;
    }

    public boolean changeEmail(String newMail, String password) {
        return false;
    }
}
