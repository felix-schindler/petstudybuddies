package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.Query;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

public class Task extends Model {
    private int toDoList;
    private String content;
    private String until;
    private int assignedTo;
    public Task(int ID) {
        super(ID);
        try {
            ResultSet task = new Query("SELECT * FROM Task WHERE ID=" + ID).Fetch();
            toDoList = task.getInt("ToDoList");
            content = task.getString("Content");
            until = task.getString("Until");
            assignedTo = task.getInt("assignedTo");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
