package de.hdm_stuttgart.mi.PetStudyBuddies;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.Query;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.UpdateQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.Lecture;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.Note;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * For now just a class for testing
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        // Test save
        try {
            Lecture vorlesung = new Lecture(1);
            System.out.println(vorlesung.save());
        } catch (Exception ignored) {}

        System.out.println(new UpdateQuery("User", "Password", null, "UserID=1").Success());
        // UPDATE User SET Password=NULL
        // UPDATE User SET Password='null'

        // Test queries
        System.out.println(new SelectQuery("User", "EMail", "Username='test' AND Password='" + Utils.sha1("test")+ "';", null, null).fetch());

        System.out.println("There exist " + new Query("SELECT * FROM User;").Count() + " Users:");
        Utils.printResultSet(new Query("SELECT * FROM User;").Fetch());

        System.out.println(new Query("SELECT ID FROM User WHERE Username='test' AND Password='" + Utils.sha1("test") + "';").FetchSingleField());
        Utils.printResultSet(new Query("SELECT EMail FROM User WHERE Username='test' AND Password='" + Utils.sha1("test") + "';").Fetch());

        // Try to print some notes
        try {
            ResultSet idResults = new Query("SELECT ID FROM Note WHERE UserID=1 LIMIT 5").Fetch();
            HashMap<Integer, Note> notes = new HashMap<Integer, Note>();

            while (idResults.next()) {
                int id = Integer.parseInt(idResults.getString("ID"));
                notes.put(id, new Note(id));
            }

            for (Map.Entry<Integer, Note> note : notes.entrySet()) {
                System.out.print(note.getValue().getTitle() + " | ");
                System.out.println(note.getValue().getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
