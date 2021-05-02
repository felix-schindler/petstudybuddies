package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Query extends SQLiteJDBC /* implements Iterator */
{
    /**
     * Stores the actual Query Object
     * @var Statement
     */
    private Statement query;

    /**
     * Stores the Querystring that was provided
     * @var String
     */
    private String queryString;

    /**
     * ResultSet result
     * @var ArrayList<HashMap<String,Object>>()
     */
     private ResultSet result;

    /**
     * Assembled a Database Query
     */
    public Query(String queryString)
    {
        SetQueryString(queryString);
        try {
            query = getConnection().createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        result = null;
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
            result = query.executeQuery(queryString);
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
        try {
            if (result.last()) {
                result.getRow();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

    /**
     * Returns an assosiative Array with all the Database Values
     * @return HashMap<string,string>|null - Associative Array
     */
    public ResultSet Fetch()
    {
        return result;
    }

    /**
     * Retuns a SINGLE string with the database value
     * @param field column name in database table
     * @return fieldValue String
     */
    public String Fetch(String field) {
        try {
            return result.getString(field);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    /**
     * Executes the actual Query and returns an Array with all the Rows
     */
    public ArrayList FetchAll()
    {
        ArrayList completeResult = new ArrayList<HashMap<String,Object>>();

        try {
            while (result.next()) {
                completeResult.add(result);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return completeResult;
    }
}
