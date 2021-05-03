package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class SQLiteJDBC {
    private final static Logger log = LogManager.getLogger(SQLiteJDBC.class);

    /**
     * holds the current database connection
     */
    private static Connection con = null;

    public SQLiteJDBC() {
        connectIfNotConnected();
    }

    /**
     * @return the current database connection
     */
    public static Connection getConnection() {
        log.debug("Trying to connect");
        connectIfNotConnected();
        return con;
    }

    /**
     * Connect if not connected
     */
    public static void connectIfNotConnected() {
        // Bereits verbunden -> Muss nicht neu verbinden
        if (con!=null)
            return;
        connect();
    }

    /**
     * Connects to DB
     */
    private static void connect() {
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
