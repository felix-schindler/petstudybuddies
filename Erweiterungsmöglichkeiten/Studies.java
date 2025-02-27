package de.hdm_stuttgart.mi.PetStudyBuddies.models;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.UpdateQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Studies extends Model {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(Studies.class);

    /**
     *
     */
    private int majorID;

    /**
     *
     */
    private int lectureID;

    /**
     * @param ID
     */
    public Studies(int ID) {
        super(ID);
        try {
            ResultSet studies = new SelectQuery("Studies", "*", "ID=" + ID, null, null).fetchAll();
            majorID = studies.getInt("MajorID");
            lectureID = studies.getInt("LectureID");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @see Model#getTable()
     */
    @Override
    public String getTable() {
        return "Studies";
    }

    /**
     * @see Studies#majorID
     */
    public int getMajorID() {
        return majorID;
    }

    /**
     * Sets the major id
     */
    public void setMajorID(int majorID) {
        this.majorID = majorID;
    }

    /**
     * @see Studies#lectureID
     */
    public int getLectureID() {
        return lectureID;
    }

    /**
     * Sets the lecture id
     */
    public void setLectureID(int lectureID) {
        this.lectureID = lectureID;
    }

    /**
     * @see Model#save()
     */
    public boolean save() {
        log.debug("Trying to safe changes");
        return new UpdateQuery(getTable(), new String[]{"MajorID", "LectureID"},
                new String[]{Integer.toString(majorID), Integer.toString(lectureID)},
                "ID=" + getID()).Count() == 1;
    }
}

