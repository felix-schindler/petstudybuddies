package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Shareable;

public class ToDoList extends Model implements Shareable {
    public ToDoList(int ID) {
        super(ID);
    }

    public boolean share(int ID) {
        return false;
    }

    public String getUserID(){
        return getField("UserID");
    }
    public String getTitle(){
        return getField("Title");
    }

    @Override
    public String getTable() {
        return "ToDoList";
    }



}
