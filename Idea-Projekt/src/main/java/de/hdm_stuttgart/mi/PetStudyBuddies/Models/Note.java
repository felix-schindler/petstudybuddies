package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;

import java.util.Date;

public class Note extends Model {
    private String title;
    private String content;
    private Date lastEditedOn;
    private Date createdOn;

    public Note() {

    }

    public boolean share(int ID) {
        return false;
    }
}
