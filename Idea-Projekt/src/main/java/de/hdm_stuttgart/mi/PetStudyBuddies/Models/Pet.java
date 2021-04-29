package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Emotion;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.Model;

public class Pet extends Model {
    private String name;
    private Emotion emotion;

    public Pet() {

    }

    @Override
    public String getTable() {
        return null;
    }
}