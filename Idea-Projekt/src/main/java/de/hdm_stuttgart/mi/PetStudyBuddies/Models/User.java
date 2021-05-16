package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class User extends Model {
    /**
     * log object for error handling
     */
    private static Logger log = LogManager.getLogger(User.class);
    /**
     *
     */
    private String username;
    /**
     *
     */
    private String eMail;
    /**
     *
     */
    private String password;

    /**
     *
     * @param ID
     */
    public User(int ID) {
        super(ID);
    }

    /**
     * @return the table name of the model
     */
    @Override
    public String getTable() {
        return "User";
    }

    /**
     * TODO get actual username
     * @return username
     */
    public String getUsername() {
        return getField("Username");
    }

    /**
     * TODO get actual email
     * @return email
     */
    public String getEMail() {
        return getField("EMail");
    }

    // TODO set field via SQL

    /**
     *
     */
    public void setEMail() {
    }

    // TODO get actual password (as sha1 straight out of the database)

    /**
     *
     * @return
     */
    public static String getPassword() {
        return "";
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
