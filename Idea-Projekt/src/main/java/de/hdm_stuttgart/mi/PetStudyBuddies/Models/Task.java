package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Task extends Model {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(Task.class);
    /**
     *
     */
    private int toDoList;
    /**
     *
     */
    private String content;
    /**
     *
     */
    private String until;
    /**
     *
     */
    private int assignedTo;

    /**
     *
     * @param ID
     */
    public Task(int ID) {
        super(ID);
        try {
            ResultSet task = new SelectQuery("Task", "*", "ID="+ID, null, null).fetchAll();
            toDoList = task.getInt("ToDoList");
            content = task.getString("Content");
            until = task.getString("Until");
            assignedTo = task.getInt("assignedTo");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public String getToDoListID() {
        return getField("ToDoListID");
    }

    /**
     *
     * @return
     */
    public String getContent() {
        return getField("Content");
    }

    /**
     *
     * @return
     */
    public Date getUntil() {
        return new Date(Integer.parseInt(getField("Until")));
    }

    /**
     *
     * @return
     */
    public String getAssignedTo(){
        return getField("AssignedTo");
    }

    /**
     *
     * @param NoteID
     * @param username
     * @return
     */
    public boolean assignPerson(int NoteID, String username) {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public String getTable() {
        return "Task";
    }
}
