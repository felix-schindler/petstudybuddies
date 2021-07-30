package de.hdm_stuttgart.mi.PetStudyBuddies.core.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteQuery extends Query {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(DeleteQuery.class);

    /**
     * Count of effected rows
     */
    private int rows = -1;

    /**
     * Hands over parts of the SQL-Query Delete statement to the BuildQuery method and then calls SetQueryString
     *
     * @param table String containing the name of the table
     * @param where String containing the "WHERE"-clause of the SQL-statement
     */
    public DeleteQuery(String table, String where) {
        SetQueryString(BuildQuery(table, where));
        rows = WriteData();
    }

    /**
     * Hands over parts of the SQL-Query Delete statement to the BuildQuery method and then calls SetQueryString if run set true
     *
     * @param table String containing the name of the table
     * @param where String containing the "WHERE"-clause of the SQL-statement
     * @param run   boolean if true built Query is set with SetQueryString method
     */
    public DeleteQuery(String table, String where, boolean run) {
        SetQueryString(BuildQuery(table, where));
        if (run)
            rows = WriteData();
    }

    /**
     * Builds the DELETE-Query with the given parameters and saves query in the Stringbuilder query object
     *
     * @param table String containing the name of the table
     * @param where String containing the "WHERE"-clause of the SQL-statement
     */
    private String BuildQuery(String table, String where) {
        final StringBuilder query = new StringBuilder();

        query.append("DELETE FROM ").append(table);
        if (where != null && !where.equals("")) {
            query.append(" WHERE ").append(where);
        }
        query.append(";");

        log.debug("Query string was built");
        return query.toString();
    }

    /**
     * @return Number of changed rows OR -1 if no rows are affected (1 if insert is successful)
     * @see Query#WriteData()
     */
    public int Count() {
        return rows;
    }
}
