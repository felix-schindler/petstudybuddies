package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Shareable;

public class Lecture extends Model implements Shareable {
    private String title;

    public Lecture() {

    }

    public boolean share(int ID) {
        return false;
    }
}
