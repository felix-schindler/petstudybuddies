package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class SelectQuery extends Query {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(SelectQuery.class);

    /**
     * result of the executed query
     */
    private CachedRowSet result;

    /**
     * Hands over parts of the SQL-Query Select statement to the BuildQuery method and then calls SetQueryString
     *
     * @param table String containing the name of the table
     * @param field String containing the name of the field where values shall be outprinted
     * @param where String containing the "WHERE"-clause of the SQL-statement
     */
    public SelectQuery(String table, String field, String where) {
        SetQueryString(buildQuery(table, field, where, null, null));
        result = ReadData();
    }

    /**
     * Hands over parts of the SQL-Query Select statement to the BuildQuery method and then calls SetQueryString
     *
     * @param table   String containing the name of the table
     * @param field   String containing the name of the field where values shall be outprinted
     * @param where   String containing the "WHERE"-clause of the SQL-statement
     * @param orderBy String containing the "ORDER BY"-clause of the SQL-statement
     * @param groupBy String containing the "GROUP BY"-clause of the SQL-statement
     */
    public SelectQuery(String table, String field, String where, String orderBy, String groupBy) {
        SetQueryString(buildQuery(table, field, where, orderBy, groupBy));
        result = ReadData();
    }

    /**
     * Hands over parts of the SQL-Query Select statement to the BuildQuery method and then calls SetQueryString if run set true
     *
     * @param table   String containing the name of the table
     * @param field   String containing the name of the field where values shall be outprinted
     * @param where   String containing the "WHERE"-clause of the SQL-statement
     * @param orderBy String containing the "ORDER BY"-clause of the SQL-statement
     * @param groupBy String containing the "GROUP BY"-clause of the SQL-statement
     * @param run     boolean if true built Query is set with SetQueryString method
     */
    public SelectQuery(String table, String field, String where, String orderBy, String groupBy, boolean run) {
        SetQueryString(buildQuery(table, field, where, orderBy, groupBy));
        if (run)
            result = ReadData();
    }

    /**
     * Builds the SELECT-Query with the given parameters
     *
     * @param table   String containing the name of the table
     * @param field   String containing the name of the field where values shall be outprinted
     * @param where   String containing the "WHERE"-clause of the SQL-statement
     * @param orderBy String containing the "ORDER BY"-clause of the SQL-statement
     * @param groupBy String containing the "GROUP BY"-clause of the SQL-statement
     * @return SQL-Query
     */
    public String buildQuery(String table, String field, String where, String orderBy, String groupBy) {
        final StringBuilder query = new StringBuilder();

        if (table == null || field == null) {
            log.error("Table or field equals null");
            return "";
        }

        query.append("SELECT ").append(field).append(" FROM ").append(table);
        if (where != null) {
            query.append(" WHERE ").append(where);
        }
        if (groupBy != null) {
            query.append(" GROUP BY ").append(groupBy);
        }
        if (orderBy != null) {
            query.append(" ORDER BY ").append(orderBy);
        }
        query.append(";");

        log.debug("Query string was built");
        return query.toString();
    }

    /**
     * Returns result of SELECT-statement if several DB-results were found
     *
     * @return result of SELECT-statement
     * @see Query#ReadData()
     */
    public CachedRowSet fetchAll() {
        try {
            result.first();
        } catch (SQLException e) {
            log.catching(e);
            log.error("Could not move to the first row");
        }
        return result;
    }

    /**
     * Returns result of SELECT-statement if one DB-result was found
     *
     * @return result of SELECT-statement
     */
    public String fetch() {
        try {
            result.first();
            if (result.getRow() == 0)   // The is no result of select
                return null;
            return result.getString(1);
        } catch (SQLException e) {
            log.catching(e);
            log.error("Could not move to the first row");
            return null;
        }
    }

    /**
     * Counts selected rows
     * Moves to last row, sets count, moves to first again
     *
     * @return Count of selected rows
     */
    public int Count() {
        int count = 0;

        try {
            result.last();
            count = result.getRow();
            result.first();
        } catch (SQLException e) {
            log.catching(e);
            log.error("Failed to count rows of ResultSet");
        }

        return count;
    }
}
