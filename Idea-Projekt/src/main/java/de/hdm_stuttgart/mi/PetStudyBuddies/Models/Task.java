package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.UpdateQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
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
    private Date until;

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
            ResultSet task = new SelectQuery("Task", "*", "ID=" + ID, null, null).fetchAll();
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
    public Date getUntil() {
        return until;
    }

    /**
     * Set due to date
     */
    public void setUntil(Date newUntil) {
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
     * TODO das kommt entweder in the save funktion selbst oder wird dort aufgerufen. wahrscheinlich aber eher in die save funktion selbst
     * Assigns the task to a person
     *
     * @param TaskID ID of the note to be shared
     * @param UserID ID of the user to be assigned to
     * @return true if the person could be assigned, false otherwise
     */
    public boolean assignPerson(int TaskID, int UserID) {

        if (new SelectQuery(getTable(), "ID", "TaskID=" + TaskID + " AND UserID=" + UserID).fetch().equals(null) ) {
            log.debug("No User existing with that Username");
            return false;
        }
        // User is already assigned
        if (new SelectQuery(getTable(), "ID", "TaskID=" + TaskID + " AND UserID=" + UserID).fetch().equals(Integer.toString(UserID))) {
            log.debug("Newly assigned Person is the same as before.");
            return true;
        }

        // TODO default wert?? -> change with -1
        if (assignedPerson != -1 && new SelectQuery(getTable(), "ID", "TaskID=" + TaskID + " AND UserID=" + UserID).fetch() != null) {
            log.debug("Try to insert newly assigned person");
            return new InsertQuery(getTable(), new String[]{"NoteID", "UserID"}, new String[]{Integer.toString(TaskID), Integer.toString(UserID)}).Count() == 1;
        } else {
            log.debug("Try to update assigned person");
            return new UpdateQuery(getTable(), new String[]{"UserID"}, new String[]{Integer.toString(UserID)}, "ID=" + TaskID).Count() == 1;
        }
    }

    /**
     * @see Model#save()
     */
    public boolean save() {
        log.debug("Trying to safe changes");
        return new UpdateQuery(getTable(), new String[]{"ToDoListID", "Content", "Until", "AssignedTo"},
                new String[]{Integer.toString(toDoList), content, until.toString(), Integer.toString(assignedPerson)},
                "ID=" + getID()).Count() == 1;
    }
}
