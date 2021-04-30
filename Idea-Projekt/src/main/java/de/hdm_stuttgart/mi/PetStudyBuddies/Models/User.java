package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;

public class User extends Model {
    public User() {

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
        return "";
    }

    /**
     * TODO get actual email
     * @return email
     */
    public String getEMail() {
        return "";
    }

    // TODO set field via SQL
    public void setEMail() {
    }

    // TODO get actual password (as sha1 straight out of the database)
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
