package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.Model;

public class Studies extends Model {
    private String title;
    private Lecture[] lectures;

    public Studies() {

    }

    @Override
    public String getTable() {
        return "Studies";
    }
}
