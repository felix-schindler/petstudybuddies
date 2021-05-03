package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;

import java.text.DateFormat;
import java.util.Date;

public class Task extends Model {
    public Task(int ID) {
        super(ID);
    }

    public String getToDoListID() {
        return getField("ToDoListID");
    }

    public String getContent() {
        return getField("Content");
    }

    public Date getUntil() {
        return new Date(Integer.parseInt(getField("Until")));
    }

    public String getAssignedTo(){
        return getField("AssignedTo");
    }

    public boolean assignPerson(int NoteID, String username) {
        return false;
    }

    @Override
    public String getTable() {
        return "Task";
    }
}
