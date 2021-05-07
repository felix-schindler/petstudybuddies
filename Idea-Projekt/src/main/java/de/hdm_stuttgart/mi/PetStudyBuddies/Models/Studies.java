package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.Query;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Studies extends Model {
    private int majorID;
    private int lectureID;
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

    public String getTitle() {
        return getField("Title");
    }

    public String getUserID() {
        return getField("UserID");
    }

    public String getLectureID() {
        return getField("LectureID");
    }


    @Override
    public String getTable() {
        return "Studies";
    }
}
