package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.UpdateQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Lecture extends Model implements Shareable {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(Lecture.class);
    /**
     * ECTS of the lecture
     */
    int ects;
    /**
     * MajorID the lecture belongs to
     */
    int majorID;
    /**
     * Title of the lecture
     */
    private String title;

    /**
     * Fetches all lectures of Lecture with DB statement und sets variables with found values
     *
     * @param ID LectureID
     */
    public Lecture(int ID) {
        super(ID);
        try {
            ResultSet lecture = new SelectQuery(getTable(), "*", "ID=" + ID).ReadData();
            title = lecture.getString("Title");
            ects = lecture.getInt("ECTS");
            majorID = lecture.getInt("MajorID");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @see Model#getTable()
     */
    @Override
    public String getTable() {
        return "Lecture";
    }

    /**
     * @see Lecture#title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the lecture
     *
     * @param title New title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @see Lecture#ects
     */
    public int getEcts() {
        return ects;
    }

    /**
     * Sets the ECTS
     *
     * @param ects New ECTS
     */
    public void setEcts(int ects) {
        this.ects = ects;
    }

    /**
     * @see Lecture#majorID
     */
    public int getMajorID() {
        return majorID;
    }

    /**
     * Sets the major
     *
     * @param majorID New major ID
     */
    public void setMajorID(int majorID) {
        this.majorID = majorID;
    }

    /**
     * @see Model#save()
     */
    public boolean save() {
        log.debug("Trying to save");
        return new UpdateQuery(getTable(), new String[]{"Title", "ECTS", "MajorID"}, new String[]{title, String.valueOf(ects), String.valueOf(majorID)}, "ID=" + getID()).Count() == 1;
    }

    /**
     * @see Shareable#share(int)
     */
    public boolean share(int ID) {
        return false;
    }
}
