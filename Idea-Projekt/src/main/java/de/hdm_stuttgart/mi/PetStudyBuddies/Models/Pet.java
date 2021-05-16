package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.CoreTest.DBTest.Query;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Pet extends Model {
    /**
     * log object for error handling
     */
    private static Logger log = LogManager.getLogger(Pet.class);
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
            ResultSet pet = new Query("SELECT * FROM Pet WHERE ID=" + ID).Fetch();
            name = pet.getString("Name");
            emotion = pet.getString("Emotion");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
    public String getName() {
        return getField("Name");
    }

    /**
     *
     * @return
     */
    public String getEmotion() {
        return getField("Emotion");
    }

    /**
     *
     * @return
     */
    @Override
    public String getTable() {
        return "Pet";
    }
}