package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.Query;
import de.hdm_stuttgart.mi.PetStudyBuddies.CoreTest.DBTest.Query;
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
     *
     */
    private String title;

    /**
     *
     * @param ID
     */
    public ToDoList(int ID) {
        super(ID);
        try {
            ResultSet toDoList = new Query("SELECT * FROM ToDoList WHERE ID=" + ID).Fetch();
            title = toDoList.getString("Title");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     *
     * @param ID
     * @return
     */
    public boolean share(int ID) {
        return false;
    }

    /**
     *
     * @return
     */
    public String getUserID(){
        return getField("UserID");
    }

    /**
     *
     * @return
     */
    public String getTitle(){
        return getField("Title");
    }

    /**
     *
     * @return
     */
    @Override
    public String getTable() {
        return "ToDoList";
    }



}
