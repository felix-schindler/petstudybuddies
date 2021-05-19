package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.UpdateQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Pet extends Model {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(Pet.class);

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String emotion;

    /**
     *
     * @param ID
     */
    public Pet(int ID) {
        super(ID);
        try {
            ResultSet pet = new SelectQuery("Pet", "*", "ID="+ID, null, null).fetchAll();
            name = pet.getString("Name");
            emotion = pet.getString("Emotion");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @see Model#getTable()
     */
    @Override
    public String getTable() {
        return "Pet";
    }

    /**
     * @see Pet#name
     */
    public String getName() {
        return name;
    }

    /**
     * @see Pet#emotion
     */
    public String getEmotion() {
        return emotion;
    }

    /**
     * Sets the name
     * @param name New name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the emotion
     * @param emotion new emotion
     */
    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public boolean save() {
        return new UpdateQuery(getTable(), new String[]{"Name", "Emotion"},
                new String[]{name, emotion},
                "ID="+getID()).Count() == 1;
    }
}