package de.hdm_stuttgart.mi.PetStudyBuddies.core;

import java.util.Date;

public class Task {
    private int ID;
    private String title;
    private String content;
    private Date until;
    private Date createdOn;

    public Task() {

    }

    public boolean assignPerson(int NoteID, String username) {
        return false;
    }
}
