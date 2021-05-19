package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.UpdateQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Shareable;
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
     * Title of the lecture
     */
    private String title;

    /**
     * ECTS of the lecture
     */
    int ects;

    /**
     * MajorID the lecture belongs to
     */
    int majorID;

    /**
     * Fetches all lectures of Lecture with DB statement und sets variables with found values
     * @param ID LectureID
     */
    public Lecture(int ID) {
        super(ID);
        try {
            ResultSet lecture = new SelectQuery(getTable(), "*", "ID="+ID).ReadData();
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
     * @see Lecture#ects
     */
    public int getEcts() {
        return ects;
    }

    /**
     * @see Lecture#majorID
     */
    public int getMajorID() {
        return majorID;
    }

    /**
     * Sets the title of the lecture
     * @param title New title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the ECTS
     * @param ects New ECTS
     */
    public void setEcts(int ects) {
        this.ects = ects;
    }

    /**
     * Sets the major
     * @param majorID New major ID
     */
    public void setMajorID(int majorID) {
        this.majorID = majorID;
    }

    /**
     * Save an updated lecture to the database
     * @see Model#save()
     */
    public boolean save() {
        return new UpdateQuery(getTable(), new String[]{"Title", "ECTS", "MajorID"}, new String[]{title, Integer.toString(ects), Integer.toString(majorID)}, "ID="+getID()).Count() == 1;
    }

    /**
     * @param ID UserID to be shared to
     * @return successfully shared or not
     */
    public boolean share(int ID) {
        return false;
    }
}
