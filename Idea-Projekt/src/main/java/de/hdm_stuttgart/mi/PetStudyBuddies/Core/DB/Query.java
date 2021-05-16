package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Statement;
import java.sql.*;

/*
 * Query class
 */
class Query extends SQLiteJDBC {
    /**
     * Log object for error handling
     */
    private static final Logger log = LogManager.getLogger(Query.class);

    /*
     * Stores the actualy query object
     */
    private Statement query = null;

    /**
     * Stores the provided query as a string
     */
    private String queryString;

    /*
     * Constructor - Doesn't do anything active right now
     */
    protected Query() {

    }

    /*
     * Constructor - Sets the query string
     * @param Query as a string
     */
    public Query(String queryString) {
        this.queryString = queryString;
    }

    /*
     * Sets the query string
     */
    public void SetQueryString(String queryString) {
        this.queryString = queryString;
    }

    /*
     * @return Query as a string
     */
    public String GetQueryString() {
        return queryString;
    }

    /*
     * Executes an update / insert query
     * @return Number of rows changed rows OR -1 if no rows are affected
     */
    public int WriteData() {
        int rows = -1;

        try {
            query = getConnection().createStatement();
            rows = query.executeUpdate(queryString);
            query.close();
        } catch (SQLException e) {
            log.catching(e);
            log.error("Could not execute INSERT or UPDATE Query.");
            log.info("Query was " + queryString);
        }

        return rows;
    }

    /*
     * Executes a select query
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
