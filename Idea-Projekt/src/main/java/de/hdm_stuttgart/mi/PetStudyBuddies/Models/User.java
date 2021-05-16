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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return email
     */
    public String getEMail() {
        return eMail;
    }

    /**
     *
     */
    public void setEMail(String newMail) {
        setField("EMail", newMail);
    }

    // TODO get actual password (as sha1 straight out of the database)

    /**
     *
     * @return
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
