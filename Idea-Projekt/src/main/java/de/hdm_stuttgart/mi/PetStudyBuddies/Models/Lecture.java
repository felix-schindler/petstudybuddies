package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.Query;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Shareable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Lecture extends Model implements Shareable {
    private String title;
    int ECTS;
    int MajorID;

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

    public boolean save() {
        return new Query("UPDATE Note SET Title='"+title+"', ECTS="+ECTS+", MajorID="+MajorID+" WHERE ID="+getID()+";").Success();
    }

    public boolean share(int ID) {
        return false;
    }

    public String getTitle() {
        return title;
    }

    public String getUserID() {
        return getField("UserID");
    }

    public String getMajorID() {
        return getField("MajorID");
    }

    public String getLectureID() {
        return getField("LectureID");
    }

    @Override
    public String getTable() {
        return "Lecture";
    }
}
