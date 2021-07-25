package de.hdm_stuttgart.mi.PetStudyBuddies.models;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.UpdateQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToDoList extends Model implements Shareable {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(ToDoList.class);

    /**
     * UserID of the owner
     */
    private int owner;

    /**
     * Title of the ToDoList
     */
    private String title;
    /**
     * Boolean if Flag is set
     */
    private boolean flagged;
    /**
     * ToDoList ID
     */
    private int todoID;

    /**
     * Creates a new ToDoList linked to the ToDoLists database-entry via its ID
     *
     * @param ID ID of the ToDoList in the database
     */
    public ToDoList(int ID) {
        super(ID);
        try {
            ResultSet toDoList = new SelectQuery("ToDoList", "*", "ID=" + ID).fetchAll();
            owner = toDoList.getInt("UserID");
            title = toDoList.getString("Title");
            flagged = toDoList.getBoolean("Flagged");
            todoID = toDoList.getInt("ID");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @see Model#getTable()
     */
    @Override
    public String getTable() {
        return "ToDoList";
    }

    /**
     * @return UserID of the owner
     */
    public int getOwner() {
        return owner;
    }

    /**
     * @return Title of the ToDoList
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a new title for a ToDoList
     *
     * @param newTitle New title for ToDoList
     */
    public void setTitle(String newTitle) {
        title = newTitle;
    }

    /**
     * Changes value flagged to opposite of current value
     */
    /*public void changeFlagged() {
        if(this.flagged){
            new UpdateQuery("ToDoList",new String[]{"Flagged"},new String[]{"false"},"ID="+ todoID);

            this.flagged=false;
        }else {
            new UpdateQuery("ToDoList",new String[]{"Flagged"},new String[]{"true"},"ID="+ todoID);
            this.flagged = true;
        }
    }*/

    /**
     * @return Flagged of the ToDoList
     */
    public boolean getFlagged() {
        return flagged;
    }

    /**
     * sets flagged
     *
     * @param flagged
     */
    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    /**
     * TODO this.
     *
     * @param ID
     * @return
     */
    public boolean share(int ID) {
        if (ID == Account.getLoggedUser().getID() || new SelectQuery("ToDoListShare", "ID", "UserID=" + ID + " AND ToDoListID=" + getID()).fetch() != null) {
            log.debug("User " + ID + " already got access");
            return true;    // User already has access
        }
        return new InsertQuery("ToDoListShare", new String[]{"UserID", "ToDoListID"}, new String[]{String.valueOf(ID), String.valueOf(getID())}).Count() == 1;

    }

    /**
     * TODO save -> Throw Exception if owner != UserID (in DB) weil Owner kann nicht geändert werden.
     *
     * @see Model#save()
     */
    public boolean save() {
        log.debug("Trying to safe changes");
        return new UpdateQuery(getTable(), new String[]{"Title", "Flagged"}, new String[]{title, flagged ? "1" : "0"}, "ID=" + getID()).Count() == 1;
        //return new UpdateQuery(getTable(), new String[]{"Title"}, new String[]{title}, "ID=" + getID()).Count() == 1;
    }
}
