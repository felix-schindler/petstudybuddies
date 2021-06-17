package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sqlite.SQLiteConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Database driver
 */
abstract class SQLiteJDBC {
    /**
     * Log object for error handling
     */
    private static final Logger log = LogManager.getLogger(SQLiteJDBC.class);

    /*
     * Holds the database connection
     */
    private Connection con = null;


    /**
     * Constructor - Doesn't do anything active right now
     */
    protected SQLiteJDBC() {
    }

    /**
     * Connect to the database if not connected
     *
     * @return The current database connection
     */
    protected Connection getConnection() {
        if (con == null)
            connect();
        return con;
    }

    /**
     * Connects to the database
     */
    private void connect() {
        if (con != null)
            return;

        boolean runCreate = !new File("data/psb.sqlite").exists();

        log.debug("Trying to connect to database.");
        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            con = DriverManager.getConnection("jdbc:sqlite:data/psb.sqlite", config.toProperties());
            con.setAutoCommit(true);    // Änderungen werden sofort in die Datenbank geschrieben & können nicht einfach rückgängig gemacht werden

            if (runCreate) {
                Statement query = getConnection().createStatement();
                String queryString = Files.readString(Path.of("data/psb.sql"));
                int rows = query.executeUpdate(queryString);
                query.close();
            }

            log.debug("Database connection opened successfully.");
        } catch (SQLException | ClassNotFoundException | IOException e) {
            log.catching(e);
            log.error("Connection not successful.");
            log.info("Tipp: Check if a .sqlite database file exists.");
        }
    }

    /**
     * Ends the connection to the database
     */
    protected void disconnect() {
        try {
            if (!con.isClosed())
                con.close();
            log.debug("Database connection is closed.");
        } catch (SQLException e) {
            log.catching(e);
            log.warn("Could not disconnect from database.");
        }
    }
}
