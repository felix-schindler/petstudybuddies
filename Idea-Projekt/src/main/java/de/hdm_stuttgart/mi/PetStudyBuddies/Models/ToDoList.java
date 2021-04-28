package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;

public class ToDoList extends Model {
    private String title;
    private Task[] tasks;

    public ToDoList() {

    }

    public boolean share(int ID) {
        return false;
    }
}
