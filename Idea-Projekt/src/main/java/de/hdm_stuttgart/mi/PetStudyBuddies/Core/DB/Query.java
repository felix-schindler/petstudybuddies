package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Query extends SQLiteJDBC
{
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
     private ResultSet result;

    /**
     * execution of query success (true) or not (false)
     */
    public boolean success = false;

    /**
     * Assembled a Database Query
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
        result = null;
    }

    /**
     * Takes the String as is and does a Query with it. So DO ESCAPE THE INPUT QUERY AT ALL COST!!! Also executes the Query immediately.
     * @param query query string
     */
    public void SetQueryString(String query) {
        super.connectIfNotConnected();
        queryString=query;
        Execute();
    }

    /**
     * Returns the current Query String
     */
    public String GetQueryString()
    {
        return queryString;
    }

    /**
     * This executes the Query and stores the Resource Object in $query
     * @see de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.Query ::$query
     */
    private void Execute() {
        try {
            /*  same as
            *   if (query.execute(queryString)
            *       result =  query.getResultSet();
            *   else QUERY FAILED
            * */
            if (query!=null) {
                success = true;
                result = query.executeQuery(queryString);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Returns if the Insert or Update Query was successful
     * @return boolean - If the Query was a success
     */
    public boolean Success()
    {
        if (result!=null) {
            return true;
        }
        return false;
    }

    /**
     * Returns the Number of Rows that this Query returned
     * @return int - Number of Rows
     */
    public int Count()
    {
        int count = 0;
        try {
            while (result.next())
                count++;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
        /* TODO DIDN'T WORK
        try {
            if (result.last()) {
                return result.getRow();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1; */
    }

    /**
     * Returns the Result Set with all the Database Values
     * @return the result of the query
     */
    public ResultSet Fetch()
    {
        return result;
    }

    /**
     * Retuns a SINGLE string with the database value
     * @return fieldValue String
     */
    public String FetchSingleField() {
        try {
            return result.getString(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
