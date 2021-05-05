package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Shareable;

import java.util.Date;

public class Note extends Model implements Shareable {
    public Note(int ID) {
        super(ID);
    }

    public boolean share(int ID) {
        return false;
    }

    public String getUserID() {
        return getField("UserID");
    }

    public String getTitle() {
        return getField("Title");
    }

    public String getContent() {
        return getField("Content");
    }

    public Date getLastEditedOn() {
        return new Date(Integer.parseInt(getField("LastEditedOn")));
    }

    public Date getCreatedOn() {
        return new Date(Integer.parseInt(getField("CreatedOn")));
    }

    @Override
    public String getTable() {
        return "Note";
    }
}
