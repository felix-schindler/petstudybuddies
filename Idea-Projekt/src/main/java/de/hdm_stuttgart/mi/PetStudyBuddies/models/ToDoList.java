package de.hdm_stuttgart.mi.PetStudyBuddies.models;

import de.hdm_stuttgart.mi.PetStudyBuddies.models.Task;

public class ToDoList extends Model {
    private String title;
    private Task[] tasks;

    public ToDoList() {

    }

    public boolean share(int ID) {
        return false;
    }
}
