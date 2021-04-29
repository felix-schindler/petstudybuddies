package de.hdm_stuttgart.mi.PetStudyBuddies.Core;

import java.sql.*;

/**
 * Static functions for testing
 */
public class UnitTest {
    public static boolean databaseConnectionIsValid() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:psb.sqlite").isValid(1);
        } catch (Exception e) {
            return false;
        }
    }
}
