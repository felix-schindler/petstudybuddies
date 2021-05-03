package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;

public class Pet extends Model {
    public Pet(int ID) {
        super(ID);
    }

    public String getUserID() {
        return getField("UserID");
    }

    public String getName() {
        return getField("Name");
    }

    public String getEmotion() {
        return getField("Emotion");
    }

    @Override
    public String getTable() {
        return "Pet";
    }
}