package de.hdm_stuttgart.mi.PetStudyBuddies;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.Query;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;

import java.sql.SQLException;

/**
 * For now just a class for testing
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(new SelectQuery("User", "EMail", "Username='test' AND Password='" + Utils.sha1("test")+ "';").fetch());

        try {
            System.out.println(new Query("SELECT EMail FROM User WHERE Username='test' AND Password='" + Utils.sha1("test") + "';").Fetch().getString("EMail"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
