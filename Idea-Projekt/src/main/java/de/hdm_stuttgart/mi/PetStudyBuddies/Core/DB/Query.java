package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Statement;
import java.sql.*;

/**
 * Query class
 */
public class Query extends SQLiteJDBC {
    /**
     * Log object for error handling
     */
    private static final Logger log = LogManager.getLogger(Query.class);

    /**
     * Stores the actual query object
     */
    private Statement query = null;

    /**
     * Stores the provided query as a string
     */
    private String queryString;

    /**
     * Constructor - Doesn't do anything active
     */
    protected Query() {

    }

    /**
     * Constructor - Sets the query string
     * @param queryString Query as a string
     */
    public Query(String queryString) {
        this.queryString = queryString;
    }

    /**
     * Sets the query string
     */
    public void SetQueryString(String queryString) {
        this.queryString = queryString;
        log.debug("QueryString was set.");
    }

    /*
     * @return Query as a string
     */
    public String GetQueryString() {
        return queryString;
    }

    /**
     * Executes an update / insert / delete query
     * @return Number of changed rows OR -1 if no rows are affected (1 if insert is successful)
     */
    public int WriteData() {
        int rows = -1;

        try {
            query = getConnection().createStatement();
            rows = query.executeUpdate(queryString);
            query.close();
        } catch (SQLException e) {
            log.catching(e);
            log.error("Could not execute INSERT or UPDATE or DELETE Query.");
            log.info("Query was " + queryString);
        }

        return rows;
    }

    /**
     * Executes an select query
     * @return Result set of the selected rows
     */
    public ResultSet ReadData() {
        ResultSet result = null;

        try {
            query = getConnection().createStatement();
            result = query.executeQuery(queryString);
            query.close();
        } catch (SQLException e) {
            log.catching(e);
            log.error("Could not execute SELECT Query.");
            log.info("Query was " + queryString);
        }

        return result;
    }
}
