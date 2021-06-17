package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.UpdateQuery;
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
     * TODO delete bc only needed in DB
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
            flagged=toDoList.getBoolean("Flagged");
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
     *
     * @return Flagged of the ToDoList
     */
    public boolean getFlagged(){
        return flagged;
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
     * TODO this.
     *
     * @param ID
     * @return
     */
    public boolean share(int ID) {
        return false;
    }

    /**
     * TODO save -> Throw Exception if owner != UserID (in DB) weil Owner kann nicht geändert werden.
     *
     * @see Model#save()
     */
    public boolean save() {
        log.debug("Trying to safe changes");
        return new UpdateQuery(getTable(), new String[]{"Title"}, new String[]{title}, "ID=" + getID()).Count() == 1;
    }
}
