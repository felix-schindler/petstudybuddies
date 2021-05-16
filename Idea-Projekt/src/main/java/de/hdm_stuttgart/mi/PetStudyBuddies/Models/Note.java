package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.Query;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Shareable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Note extends Model implements Shareable {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(Note.class);

    /**
     *
     */
    private String title;

    /**
     *
     */
    private String content;

    /**
     *
     */
    private String lastEditedOn;

    /**
     *
     */
    private String createdOn;

    /**
     *
     * @param ID
     */
    public Note(int ID) {
        super(ID);
        try {
            ResultSet note = new Query("SELECT * FROM Note WHERE ID=" + ID).Fetch();
            title = note.getString("Title");
            content = note.getString("Content");
            lastEditedOn = note.getString("LastEditedOn");
            createdOn = note.getString("CreatedOn");
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
    public String getUserID() {
        return getField("UserID");
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return getField("Title");
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
    public Date getLastEditedOn() {
        return new Date(Integer.parseInt(getField("LastEditedOn")));
    }

    /**
     *
     * @return
     */
    public Date getCreatedOn() {
        return new Date(Integer.parseInt(getField("CreatedOn")));
    }

    /**
     *
     * @return
     */
    @Override
    public String getTable() {
        return "Note";
    }
}
