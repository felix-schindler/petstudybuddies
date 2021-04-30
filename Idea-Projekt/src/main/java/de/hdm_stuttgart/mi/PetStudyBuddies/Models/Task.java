package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;

import java.util.Date;

public class Task extends Model {
    private String title;
    private String content;
    private Date until;
    private Date createdOn;

    public Task(int ID) {
        super(ID);
    }

    public boolean assignPerson(int NoteID, String username) {
        return false;
    }

    @Override
    public String getTable() {
        return "Task";
    }
}
