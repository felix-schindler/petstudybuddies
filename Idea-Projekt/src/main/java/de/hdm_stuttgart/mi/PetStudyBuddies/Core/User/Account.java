package de.hdm_stuttgart.mi.PetStudyBuddies.Core.User;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.*;

/**
 * Account - This class holds the current User and the token
 */
public class Account {
    private static User user;
    private static String token;

    public Account() {
        user = null; // no user is logged in
        token = null;
    }

    public static void setUser(User user) {
        Account.user = user;
        token = Utils.sha1(User.getPassword()); // calc token (double sha1)
    }

    public static User getLoggedUser() { return user; }
    public static String getToken() {
        return token;
    }
    public static void setToken(String token) {
        Account.token = token;
    }


    /*
    TODO Alles nur Abläufe in irgendwelchen Controllern

    boolean deleteAccount() {
        // TODO Delete everything (set Constrainteigenschaften -> ON DELETE)
        return false;
    }

    boolean createPet(String name) {
        return false;
    }

    boolean deletePet(String name) {
        return false;
    }

    boolean createCalendar() {
        return false;
    }

    boolean deleteCalendar() {
        return false;
    }

    boolean createNote(String title, String content) {
        return false;
    }

    boolean deleteNote(int ID) {
        return false;
    }

    boolean createToDoList(String title) {
        return false;
    }

    boolean deleteToDoList(int ID) {
        return false;
    }

    boolean createStudies() {
        return false;
    }

    boolean deleteStudies(int ID){
        return false;
    }
    */
}
