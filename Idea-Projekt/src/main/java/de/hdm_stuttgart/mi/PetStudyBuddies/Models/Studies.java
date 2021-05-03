package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;

public class Studies extends Model {
    public Studies(int ID) {
        super(ID);
    }

    public String getTitle() {
        return getField("Title");
    }

    public String getUserID() {
        return getField("UserID");
    }

    public String getLectureID() {
        return getField("LectureID");
    }


    @Override
    public String getTable() {
        return "Studies";
    }
}
