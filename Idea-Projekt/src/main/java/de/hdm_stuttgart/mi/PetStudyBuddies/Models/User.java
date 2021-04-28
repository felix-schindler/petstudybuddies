package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;

public class User extends Model {
    public User() {

    }

    String getEMail() {
        // TODO get field via SQL
        return "";
    }

    public void setEMail() {
        // TODO set field via SQL
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
