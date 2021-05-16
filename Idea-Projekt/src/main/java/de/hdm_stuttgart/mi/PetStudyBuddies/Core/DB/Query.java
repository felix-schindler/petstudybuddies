package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query extends SQLiteJDBC {
    /**
     * Logger
     */
    private final static Logger log = LogManager.getLogger(Query.class);

    /**
     * Stores the actual Query Object
     */
    private Statement query;

    /**
     * Stores the Querystring that was provided
     */
    private String queryString;

    /**
     * ResultSet result
     */
    private ResultSet result = null;

    /**
     * execution of query success (true) or not (false)
     */
    private boolean success = false;

    /**
     * Effected rows
     */
    private int rows = -1;

    /**
     * Sets and executes a given Database Query
     */
    public Query(String queryString)
    {
        try {
            query = getConnection().createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        SetQueryString(queryString);
    }

    public Query() {
        try {
            query = getConnection().createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        queryString = null;
    }

    /**
     * Takes the String as is and does a Query with it. So DO ESCAPE THE INPUT QUERY AT ALL COST!!! Also executes the Query immediately.
     * @param query query string
     */
    public void SetQueryString(String query) {
        queryString = query;
        connectIfNotConnected();
        Execute();
    }

    public void SetQueryString(String query, boolean run) {
        queryString = query;

        if (run) {
            connectIfNotConnected();
            Execute();
        }
    }

    /**
     * Returns the current Query String
     */
    public String GetQueryString() {
        return queryString;
    }

    /**
     * This executes the Query and stores the Resource Object in $query
     * @see Query ::$query
     */
    private void Execute() {
        try {
            if (query != null) {
                if (queryString.toLowerCase().contains("update"))
                    rows = query.executeUpdate(queryString);
                else if (queryString.toLowerCase().contains("select")) {
                    result = query.executeQuery(queryString);
                } else {
                    query.execute(queryString);
                }
                success = true;
            }
        } catch (SQLException ignored) {
            success = false;
        }
    }

    /**
     * Returns if the Insert or Update Query was successful
     * @return boolean - If the Query was a success
     */
    public boolean Success() {
        return (result != null) || (rows != 0) || (success);
    }

    /**
     * Returns the Number of Rows that this Query returned
     * @return effected rows
     */
    public int Count() {
        if (rows == -1) {
            int count = 0;
            try {
                while (result.next())
                    count++;
            } catch (SQLException ignored) {}
            rows = count;
        }

        return rows;
    }

    /**
     * Returns the Result Set with all the Database Values
     * @return the result of the query
     */
    public ResultSet Fetch() {
        return result;
    }

    /**
     * Retuns a SINGLE string with the database value
     * @return fieldValue String
     */
    public String FetchSingleField() {
        try {
            return result.getString(1);
        } catch (SQLException ignored) {}

        return null;
    }
}
