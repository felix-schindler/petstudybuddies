package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.Query;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Shareable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Note extends Model implements Shareable {
    private String title;
    private String content;
    private String lastEditedOn;
    private String createdOn;

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

    public boolean share(int ID) {
        return false;
    }

    public String getUserID() {
        return getField("UserID");
    }

    public String getTitle() {
        return getField("Title");
    }

    public String getContent() {
        return getField("Content");
    }

    public Date getLastEditedOn() {
        return new Date(Integer.parseInt(getField("LastEditedOn")));
    }

    public Date getCreatedOn() {
        return new Date(Integer.parseInt(getField("CreatedOn")));
    }

    @Override
    public String getTable() {
        return "Note";
    }
}
