package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.CoreTest.DBTest.Query;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Studies extends Model {
    /**
     * log object for error handling
     */
    private static Logger log = LogManager.getLogger(Studies.class);
    /**
     *
     */
    private int majorID;
    /**
     *
     */
    private int lectureID;

    /**
     *
     * @param ID
     */
    public Studies(int ID) {
        super(ID);
        try {
            ResultSet studies = new Query("SELECT * FROM Studies WHERE ID=" + ID).Fetch();
            majorID = studies.getInt("MajorID");
            lectureID = studies.getInt("LectureID");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
    public String getUserID() {
        return getField("UserID");
    }

    /**
     *
     * @return
     */
    public String getLectureID() {
        return getField("LectureID");
    }

    /**
     *
     * @return
     */
    @Override
    public String getTable() {
        return "Studies";
    }
}
