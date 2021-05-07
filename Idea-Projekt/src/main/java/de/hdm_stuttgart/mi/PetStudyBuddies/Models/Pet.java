package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.Query;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Pet extends Model {
    private String name;
    private String emotion;
    public Pet(int ID) {
        super(ID);
        try {
            ResultSet pet = new Query("SELECT * FROM Pet WHERE ID=" + ID).Fetch();
            name = pet.getString("Name");
            emotion = pet.getString("Emotion");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getUserID() {
        return getField("UserID");
    }

    public String getName() {
        return getField("Name");
    }

    public String getEmotion() {
        return getField("Emotion");
    }

    @Override
    public String getTable() {
        return "Pet";
    }
}