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
    private LocalDate lastEditedOn;

    /**
     * Date when the note was created
     */
    private LocalDate createdOn;

    /**
     * @param ID
     */
    public Note(int ID) {
        super(ID);
        try {
            ResultSet note = new SelectQuery("Note", "*", "ID=" + ID).fetchAll();
            title = note.getString("Title");
            content = note.getString("Content");
            lastEditedOn = Utils.parseDate(note.getString("LastEditedOn"));
            createdOn = Utils.parseDate(note.getString("CreatedOn"));
        } catch (SQLException e) {
            log.catching(e);
            log.error("Failed to create note");
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
     * Sets the new title of the note
     *
     * @param title new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @see Note#content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the new content of the note
     *
     * @param content The new content of the note
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @see Note#lastEditedOn
     */
    public LocalDate getLastEditedOn() {
        return lastEditedOn;
    }

    /**
     * @see Note#createdOn
     */
    public LocalDate getCreatedOn() {
        return createdOn;
    }

    /**
     * @see Shareable#share(int)
     */
    @Override
    public boolean share(int ID) {
        if (ID == Account.getLoggedUser().getID() || new SelectQuery("NoteShare", "ID", "UserID=" + ID + " AND NoteID=" + getID()).fetch() != null) {
            log.debug("User " + ID + " already got access");
            return true;    // User already has access
        }
        return new InsertQuery("NoteShare", new String[]{"UserID", "NoteID"}, new String[]{String.valueOf(ID), String.valueOf(getID())}).Count() == 1;
    }

    /**
     * @see Model#save()
     */
    public boolean save() {
        return new UpdateQuery(
                getTable(),
                new String[]{"Title", "Content", "LastEditedOn"},
                new String[]{title, content, String.valueOf(new Date(System.currentTimeMillis()).getTime())},
                "ID=" + getID()).Count() == 1;
    }
}
