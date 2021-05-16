package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class User extends Model {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(User.class);

    /**
     * username of the user
     */
    private String username;

    /**
     * eMail of the user
     */
    private String eMail;

    /**
     * password of the user
     */
    private String password;

    /**
     * Creates a new user linked to the users database-entry via his ID
     * @param ID ID of the user in the database
     */
    public User(int ID) {
        super(ID);
    }

    /**
     * @return The table name of the model
     */
    @Override
    public String getTable() {
        return "User";
    }

    /**
     * @return Username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return EMail of the user
     */
    public String getEMail() {
        return eMail;
    }

    /**
     * Updates the Users' EMail in the Database
     */
    public void setEMail(String newMail) {
        setField("EMail", newMail);
    }

    /**
     * TODO this should probably not exist
     * @return The SHA1 Password of the User
     */
    public String getPassword() {
        return password;
    }


    /*
    Controller: (calls setPassword of this Model)
    public boolean changePassword(String oldPw, String newPw, String newPwRepeat) {
        return false;
    }

    Controller: (calls setEMail of this Model)
    public boolean changeEmail(String newMail, String password) {
        return false;
    }
    */
}
