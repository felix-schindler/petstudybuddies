package de.hdm_stuttgart.mi.PetStudyBuddies.core;

public class ToDoList {
    private int ID;
    private String title;
    private Task[] tasks;

    public ToDoList() {

    }

    public boolean share(int ID) {
        return false;
    }
}
