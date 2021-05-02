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
        System.out.println(new SelectQuery("User", "EMail", "Username='test' AND Password='" + Utils.sha1("test")+ "';").fetch());

        System.out.println("There exist " + new Query("SELECT * FROM User;").Count() + " Users:");
        Utils.printResultSet(new Query("SELECT * FROM User;").Fetch());

        System.out.println(new Query("SELECT ID FROM User WHERE Username='test' AND Password='" + Utils.sha1("test") + "';").FetchSingleField());
        Utils.printResultSet(new Query("SELECT EMail FROM User WHERE Username='test' AND Password='" + Utils.sha1("test") + "';").Fetch());
    }
}
