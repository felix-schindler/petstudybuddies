package de.hdm_stuttgart.mi.PetStudyBuddies.core;

import java.util.Date;

public class Note {
    private int ID;
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
