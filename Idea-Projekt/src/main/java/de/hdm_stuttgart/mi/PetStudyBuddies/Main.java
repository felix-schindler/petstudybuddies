package de.hdm_stuttgart.mi.PetStudyBuddies;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.Query;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;

import java.sql.SQLException;

/**
 * For now just a class for testing
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println(new Query("INSERT INTO User (Username,EMail,Password) VALUES ('testuser1','testuser1@example.com','"+Utils.sha1("test")+"');").Success());
        System.out.println(new SelectQuery("User", "EMail", null, null, null).fetch());
        Utils.printResultSet(new Query("SELECT EMail FROM User;").Fetch());
        Utils.printResultSet(new SelectQuery("User", "EMail", null, null, null).fetchAll());
    }
}
