package de.hdm_stuttgart.mi.PetStudyBuddies.models;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.UpdateQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pet extends Model {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(Pet.class);

    /**
     * Name of the pet
     */
    private String name;

    /**
     * Emotion of the pet
     */
    private String emotion;

    /**
     * @param ID
     */
    public Pet(int ID) {
        super(ID);
        try {
            ResultSet pet = new SelectQuery("Pet", "*", "ID=" + ID, null, null).fetchAll();
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
     * Sets the name
     *
     * @param name New name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @see Pet#emotion
     */
    public String getEmotion() {
        return emotion;
    }

    /**
     * Sets the emotion
     *
     * @param newEmotion New Emotion
     */
    public void setEmotion(String newEmotion) {
        emotion = newEmotion;
    }

    /**
     * @see Model#save()
     */
    public boolean save() {
        log.debug("Trying to save");
        return new UpdateQuery(getTable(), new String[]{"Name", "Emotion"},
                new String[]{name, emotion},
                "ID=" + getID()).Count() == 1;
    }
}
