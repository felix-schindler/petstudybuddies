package de.hdm_stuttgart.mi.PetStudyBuddies.Core.User;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Account - This class holds the current User and the token
 */
public class Account {
    private static User user = null;
    private static String token = null;

    private static final Logger log = LogManager.getLogger(Account.class);

    public static void setUser(User user) {
        if (user == null)
            return;
        Account.user = user;
        token = Utils.sha1(user.getPassword());     // calc token (double sha1)
        log.info("New User set");
    }

    public static User getLoggedUser() {
        if (user == null || token == null)
            return null;

        if (!Auth.loginFromToken(user.getID(), token))
            Auth.logout();

        return user;
    }

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
