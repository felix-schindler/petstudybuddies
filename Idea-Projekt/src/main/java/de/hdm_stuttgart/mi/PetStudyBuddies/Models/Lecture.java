package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.Query;
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
    private static Logger log = LogManager.getLogger(Lecture.class);
    /**
     * String containing the lecture title
     */
    private String title;
    /**
     * Integer containing the ECTS of the lecture
     */
    int ECTS;
    /**
     * Integer containing the MajorID
     */
    int MajorID;

    /**
     * fetches all lectures of User with DB statement und sets variables with found values
     * @param ID UserID
     */
    public Lecture(int ID) {
        super(ID);
        try {
            ResultSet lecture = new Query("SELECT * FROM Lecture WHERE ID=" + ID).Fetch();
            title = lecture.getString("Title");
            ECTS = lecture.getInt("ECTS");
            MajorID = lecture.getInt("MajorID");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * //TODO
     * @return
     */
    public boolean save() {
        return new Query("UPDATE Note SET Title='"+title+"', ECTS="+ECTS+", MajorID="+MajorID+" WHERE ID="+getID()+";").Success();
    }

    /**
     *
     * @param ID UserID
     * @return // TODO
     */
    public boolean share(int ID) {
        return false;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
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
    public String getMajorID() {
        return getField("MajorID");
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
        return "Lecture";
    }
}
