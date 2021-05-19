package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.UpdateQuery;
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
     * Title of the note
     */
    private String title;

    /**
     * Content of the note
     */
    private String content;

    /**
     * Date when the note was last edited
     */
    private Date lastEditedOn;

    /**
     * Date when the note was created
     */
    private Date createdOn;

    /**
     *
     * @param ID
     */
    public Note(int ID) {
        super(ID);
        try {
            ResultSet note = new SelectQuery("Note", "*", "ID="+ID, null, null).fetchAll();
            title = note.getString("Title");
            content = note.getString("Content");
            lastEditedOn = note.getDate("LastEditedOn");
            createdOn = note.getDate("CreatedOn");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @see Model#getTable()
     */
    @Override
    public String getTable() {
        return "Note";
    }

    /**
     * @see Note#title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @see Note#content
     */
    public String getContent() {
        return content;
    }

    /**
     * @see Note#lastEditedOn
     */
    public Date getLastEditedOn() {
        return lastEditedOn;
    }

    /**
     * @see Note#createdOn
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * Sets the new title of the note
     * @param title new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the new content of the note
     * @param content The new content of the note
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return true if saved successfully, false otherwise
     */
    public boolean save() {
        return new UpdateQuery(getTable(), new String[]{"Title", "Content", "LastEditedOn", "CreatedOn"},
                new String[]{title, content, lastEditedOn.toString(), createdOn.toString()},
                "ID="+getID()).Count() == 1;
    }

    public boolean share(int ID) {
        return false;
    }
}
