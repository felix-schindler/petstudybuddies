package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Calendar extends Model {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(Calendar.class);
    /**
     *
     * @param ID UserID
     */
    public Calendar(int ID) {
        super(ID);
    }

    /**
     *
     * @return table
     */
    @Override
    public String getTable() {
        return null;
    }
}
