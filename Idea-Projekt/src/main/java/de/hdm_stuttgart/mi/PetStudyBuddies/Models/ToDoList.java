package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.UpdateQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Shareable;
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
     * Creates a new ToDoList linked to the ToDoLists database-entry via its ID
     * @param ID ID of the ToDoList in the database
     */
    public ToDoList(int ID) {
        super(ID);
        try {
            ResultSet toDoList = new SelectQuery("ToDoList", "*", "ID="+ID).fetchAll();
            owner = toDoList.getInt("UserID");
            title = toDoList.getString("Title");
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
    public int getOwner(){
        return owner;
    }

    /**
     * @return Title of the ToDoList
     */
    public String getTitle(){
        return title;
    }

    /**
     * Sets a new title for a ToDoList
     * @param newTitle New title for ToDoList
     */
    public void setTitle(String newTitle) {
        title = newTitle;
    }

    /**
     * TODO this.
     * @param ID
     * @return
     */
    public boolean share(int ID) {
        return false;
    }

    /**
     * TODO save -> Throw Exception if owner != UserID (in DB) weil Owner kann nicht geändert werden.
     * @see Model#save()
     */
    public boolean save() {
        log.debug("Trying to safe changes");
        return new UpdateQuery(getTable(), new String[]{"Title"}, new String[]{title}, "ID=" + ID).Count() == 1;
    }
}
