package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Shareable;

public class Lecture extends Model implements Shareable {
    private String title;

    public Lecture(int ID) {
        super(ID);
    }

    public boolean share(int ID) {
        return false;
    }

    public String getUserID() {
        return getField("UserID");
    }

    public String getMajorID() {
        return getField("MajorID");
    }

    public String getLectureID() {
        return getField("LectureID");
    }

    @Override
    public String getTable() {
        return "Lecture";
    }
}
