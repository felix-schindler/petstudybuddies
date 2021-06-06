package de.hdm_stuttgart.mi.PetStudyBuddies.Core.User;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Account - This class holds the current User and the token
 */
public class Account {
    private static final Logger log = LogManager.getLogger(Account.class);
    private static User user = null;
    private static String token = null;

    public static void setUser(User user) {
        if (user == null)
            return;
        Account.user = user;
        token = Utils.sha1(user.getPassword());     // calc token (double sha1)
        log.info("New User set");
    }

    public static User getLoggedUser() {
        /* TODO remove bc not needed in java
        if (user == null || token == null)
            return null;

        if (!Auth.loginFromToken(user.getID(), token))
            Auth.logout();
         */

        return user;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Account.token = token;
    }
}
