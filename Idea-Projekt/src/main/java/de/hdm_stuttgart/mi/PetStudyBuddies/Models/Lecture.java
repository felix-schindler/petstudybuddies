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
     * String containing the lecture title
     */
    private String title;

    /**
     * Integer containing the ECTS of the lecture
     */
    int ects;

    /**
     * Integer containing the MajorID
     */
    int majorID;

    /**
     * fetches all lectures of User with DB statement und sets variables with found values
     * @param ID UserID
     */
    public Lecture(int ID) {
        super(ID);
        try {
            ResultSet lecture = new SelectQuery("Lecture", "*", "ID="+ID, null, null).ReadData();
            title = lecture.getString("Title");
            ects = lecture.getInt("ECTS");
            majorID = lecture.getInt("MajorID");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Save an updated lecture to the database
     * @return successfully updated or not
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

    /**
     * @return Title of the lecture
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return MajorID
     */
    public int getMajorID() {
        return majorID;
    }

    /**
     * @return Name of table in database
     */
    @Override
    public String getTable() {
        return "Lecture";
    }
}
