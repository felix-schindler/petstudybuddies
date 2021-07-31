package de.hdm_stuttgart.mi.PetStudyBuddies.models;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.UpdateQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Task extends Model {
    /**
     * Log object for error handling
     */
    private static final Logger log = LogManager.getLogger(Task.class);
    /**
     * ID of the parent ToDoList
     */
    private int toDoList;
    /**
     * Content of the task
     */
    private String content;
    /**
     * Due to date
     */
    private LocalDate until;
    /**
     * UserID of the assigned person
     */
    private int assignedPerson;

    /**
     * Creates a new task linked to the tasks database-entry via its ID
     *
     * @param ID ID of the task in the database
     */
    public Task(int ID) {
        super(ID);
        try {
            ResultSet task = new SelectQuery("Task", "*", "ID=" + ID).fetchAll();
            toDoList = task.getInt("ToDoListID");
            content = task.getString("Content");
            until = Utils.parseDate(task.getString("Until"));
            assignedPerson = task.getInt("AssignedTo");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @see Model#getTable()
     */
    @Override
    public String getTable() {
        return "Task";
    }

    /**
     * @see Task#toDoList
     */
    public int getToDoListID() {
        return toDoList;
    }

    /**
     * @see Task#content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content
     */
    public void setContent(String newContent) {
        content = newContent;
    }

    /**
     * @see Task#until
     */
    public LocalDate getUntil() {
        return until;
    }

    /**
     * Set due to date
     */
    public void setUntil(LocalDate newUntil) {
        until = newUntil;
    }

    /**
     * @see Task#assignedPerson
     */
    public int getAssignedTo() {
        return assignedPerson;
    }

    /**
     * Moves the task to a new parent-todolist
     */
    public void setToDoList(int ToDoListID) {
        toDoList = ToDoListID;
    }

    /**
     * Set new assigned person
     */
    public void setAssignedPerson(int UserID) {
        assignedPerson = UserID;
    }

    /**
     * @see Model#save()
     */
    public boolean save() {
        log.debug("Trying to safe...");
        return new UpdateQuery(getTable(), new String[]{"ToDoListID", "Content", "Until", "AssignedTo"},
                new String[]{Integer.toString(toDoList), content, String.valueOf(until), String.valueOf(assignedPerson == 0 ? null : assignedPerson)},
                "ID=" + getID()).Count() == 1;
    }
}
