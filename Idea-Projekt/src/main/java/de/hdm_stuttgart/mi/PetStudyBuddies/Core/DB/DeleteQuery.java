package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteQuery extends Query {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(DeleteQuery.class);

    /**
     * Stores the Delete SQL-Query statement
     */
    private final StringBuilder query = new StringBuilder();

    /**
     * Count of effected rows
     */
    private int rows = -1;

    /**
     * Hands over parts of the SQL-Query Delete statement to the BuildQuery method and then calls SetQueryString
     * @param table String containing the name of the table
     * @param where String containing the "WHERE"-clause of the SQL-statement
     */
    public DeleteQuery(String table, String where) {
        BuildQuery(table, where);
        log.debug("buildQuery method was run");
        SetQueryString(query.toString());
        rows = WriteData();
        log.debug("setQueryString method was run");
    }

    /**
     * Hands over parts of the SQL-Query Delete statement to the BuildQuery method and then calls SetQueryString if run set true
     * @param table String containing the name of the table
     * @param where String containing the "WHERE"-clause of the SQL-statement
     * @param run boolean if true built Query is set with SetQueryString method
     */
    public DeleteQuery(String table, String where, boolean run) {
        BuildQuery(table, where);
        log.debug("buildQuery method was run");
        SetQueryString(query.toString());
        if (run)
            rows = WriteData();
        log.debug("SetQueryString method was run");
    }

    /**
     * Builds the DELETE-Query with the given parameters and saves query in the Stringbuilder query object
     * @param table String containing the name of the table
     * @param where String containing the "WHERE"-clause of the SQL-statement
     */
    public void BuildQuery(String table, String where) {
        query.append("DELETE FROM ").append(table);
        if(where != null){
            query.append(" WHERE ").append(where);
        }
        query.append(";");
        log.debug("Query object was built");
    }

    public int Count() {
        return rows;
    }
}
