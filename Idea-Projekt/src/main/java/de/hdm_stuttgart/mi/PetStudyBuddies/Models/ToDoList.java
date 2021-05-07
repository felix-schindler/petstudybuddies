package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.Query;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Shareable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToDoList extends Model implements Shareable {
    private String title;
    public ToDoList(int ID) {
        super(ID);
        try {
            ResultSet toDoList = new Query("SELECT * FROM ToDoList WHERE ID=" + ID).Fetch();
            title = toDoList.getString("Title");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
