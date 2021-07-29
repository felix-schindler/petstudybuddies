package de.hdm_stuttgart.mi.PetStudyBuddies.models;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.UpdateQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
    private LocalDate until;
    /**
     * UserID of the assigned person
     */
    private int assignedPerson;
    /**
     * Current Tasks in Future
     */
    private int tasksInFuture = -1;
    /**
     * sum of all User Tasks
     */
    private int sumAllTasks = -1;
    /**
     * average Task Balance
     */
    private double taskBalance=-1;

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
     * returns Tasks in future
     */
    public int getTasksInFuture(){
        return tasksInFuture;
    }

    /**
     * returns Tasks in Past
     */
    public int getTasksInPast(){
        return sumAllTasks-tasksInFuture;
    }

    /**
     * updates @tasksInFuture and @sumAllTasks with SelectQuery
     */
    public void updateTasks(){
            tasksInFuture = new SelectQuery("Task","*","Until >= CURRENT_DATE",null,null,true).Count();
            sumAllTasks = new SelectQuery("Task","*",null,null,null,true).Count();
    }

    public double getTaskBalance() {
        return taskBalance;
    }

    public void setTaskBalance() {
        try{
            if (checkTasks())
                taskBalance = tasksInFuture/sumAllTasks;
        }catch(Exception o){
            log.debug("Invalid Tasks. Could not calculate Balance.");
        }
    }

    private boolean checkTasks() {
        return tasksInFuture != -1 && (sumAllTasks != -1 || sumAllTasks != 0);
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
