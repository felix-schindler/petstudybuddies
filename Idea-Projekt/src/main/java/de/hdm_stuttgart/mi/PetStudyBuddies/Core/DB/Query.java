package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
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
        log.debug("QueryString was set");
        log.info("New query string: " + queryString);
    }

    /**
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
            // disconnect();

            log.info(rows + " are affected");
            log.debug("Query successfully executed");
        } catch (SQLTimeoutException e) {
            log.catching(e);
            log.error("INSERT or UPDATE or DELETE Query timed out.");
            log.info("Query was " + queryString);
        }
        catch (SQLException e) {
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
    public CachedRowSet ReadData() {
        CachedRowSet rowset = null;

        try {
            rowset = RowSetProvider.newFactory().createCachedRowSet();
            query = getConnection().createStatement();
            ResultSet result = query.executeQuery(queryString);

            rowset.populate(result);
            query.close();

            rowset.first();
            // disconnect();
            log.debug("Query successfully executed");
        } catch (SQLException e) {
            log.catching(e);
            log.error("Could not execute SELECT Query");
            log.info("Query was " + queryString);
        }

        return rowset;
    }
}
