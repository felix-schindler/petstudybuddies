package de.hdm_stuttgart.mi.PetStudyBuddies.core.user;

import de.hdm_stuttgart.mi.PetStudyBuddies.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Account - This class holds the current logged-in User
 */
public class Account {
    /**
     * Logger object for smart (error) logging
     */
    private static final Logger log = LogManager.getLogger(Account.class);
    /**
     * Currently logged in user
     */
    private static User user = null;

    /**
     * Saves the logged-in user
     *
     * @param user User to be logged in
     */
    public static void setUser(User user) {
        if (user == null)
            return;
        Account.user = user;
        log.info(user.getUsername() + " now logged in");
    }

    /**
     * @return The currently logged-in user
     */
    public static User getLoggedUser() {
        return user;
    }
}
