package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.Model;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Shareable;

import java.util.Date;

public class Note extends Model implements Shareable {
    private String title;
    private String content;
    private Date lastEditedOn;
    private Date createdOn;

    public Note() {

    }

    public boolean share(int ID) {
        return false;
    }

    @Override
    public String getTable() {
        return "Note";
    }
}
