package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class SQLiteJDBC {
    private final static Logger log = LogManager.getLogger(SQLiteJDBC.class);

    public static Connection con = null;

    public SQLiteJDBC() {
        connect();
    }

    public static Connection getConnection() {
        log.debug("Trying to connect");

        // Bereits verbunden -> Muss nicht neu verbinden
        if (con!=null)
            return con;
        else
            connect();

        return con;
    }

    public static void connect() {
        try {
            log.debug("Opened database successfully");
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:psb.sqlite");
            con.setAutoCommit(true);
        } catch(Exception e) {
            log.catching(e);
            log.error("Connection not successful");
        }
    }
}
