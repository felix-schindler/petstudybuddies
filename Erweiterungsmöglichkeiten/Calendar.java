package de.hdm_stuttgart.mi.PetStudyBuddies.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Calendar extends Model {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(Calendar.class);

    /**
     * @param ID UserID
     */
    public Calendar(int ID) {
        super(ID);
    }

    /**
     * @see Model#getTable()
     */
    @Override
    public String getTable() {
        return null;
    }

    /**
     * @see Model#save()
     */
    public boolean save() throws Exception {
        log.debug("Trying to save");
        throw new Exception("Not implemented!");
    }
}

