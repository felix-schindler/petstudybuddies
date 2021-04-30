package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Shareable;

public class ToDoList extends Model implements Shareable {
    private String title;
    private Task[] tasks;

    public ToDoList() {

    }

    public boolean share(int ID) {
        return false;
    }

    @Override
    public String getTable() {
        return "ToDoList";
    }
}
