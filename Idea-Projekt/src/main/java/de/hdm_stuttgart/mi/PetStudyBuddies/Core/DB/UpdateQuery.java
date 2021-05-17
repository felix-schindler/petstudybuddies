package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateQuery extends Query{
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(UpdateQuery.class);

    /**
     * Stores the Update SQL-Query string
     */
    private final StringBuilder query = new StringBuilder();

    /**
     * Count of affected rows
     */
    private int rows = -1;

    /**
     * Hands over parts of the SQL-Query Update statement to the BuildQuery method and then calls SetQueryString
     * @param table String containing the name of the table
     * @param fields String Array containing the names of the fields where values shall be updated
     * @param values String Array containing the  values which shall be updated
     */
    public UpdateQuery(String table, String[] fields, String[] values) {
        BuildQuery(table, fields, values, null);
        log.debug("buildQuery method was run");
        SetQueryString(query.toString());
        rows = WriteData();
        log.debug("setQueryString method was run");
    }

    /**
     * Hands over parts of the SQL-Query Update statement to the BuildQuery method and then calls SetQueryString
     * @param table String containing the name of the table
     * @param fields String Array containing the names of the fields where values shall be updated
     * @param values String Array containing the  values which shall be updated
     * @param where String containing the "WHERE"-clause of the SQL-statement
     */
    public UpdateQuery(String table, String[] fields, String[] values, String where) {
        BuildQuery(table, fields, values, where);
        log.debug("buildQuery method was run");
        SetQueryString(query.toString());
        rows = WriteData();
        log.debug("setQueryString method was run");
    }

    /**
     * Update a single field
     * @param table String containing the name of the table
     * @param field  String containing the name of the field where a given value shall be updated
     * @param value String containing the value which shall be updated
     * @param where String containing the "WHERE"-clause of the SQL-statement
     */
    public UpdateQuery(String table, String field, String value, String where) {
        BuildQuery(table, field, value, where);
        log.debug("buildQuery method was run");
        SetQueryString(query.toString());
        rows = WriteData();
        log.debug("setQueryString method was run");
    }

    /**
     * Update multiple fields and ask for run
     * @param table String containing the name of the table
     * @param fields String Array containing the names of the fields where values shall be updated
     * @param values String Array containing the  values which shall be updated
     * @param where String containing the "WHERE"-clause of the SQL-statement
     * @param run boolean if true built Query is set with SetQueryString method
     */
    public UpdateQuery(String table, String[] fields, String[] values, String where, boolean run) {
        BuildQuery(table, fields, values, where);
        log.debug("buildQuery method was run");
        SetQueryString(query.toString());
        if (run)
            rows = WriteData();
        log.debug("setQueryString method was run");
    }

    /**
     * Update single field and ask for run
     * @param table String containing the name of the table
     * @param field String containing the name of the field where a given value shall be updated
     * @param value String containing the value which shall be updated
     * @param where String containing the "WHERE"-clause of the SQL-statement
     * @param run boolean if true built Query is set with SetQueryString method
     */
    public UpdateQuery(String table, String field, String value, String where, boolean run) {
        BuildQuery(table, field, value, where);
        log.debug("buildQuery method was run");
        SetQueryString(query.toString());
        if (run)
            rows = WriteData();
        log.debug("setQueryString method was run");
    }

    /**
     * Builds the Update-Query string
     * @param table String containing the name of the table
     * @param fields String Array containing the names of the fields where values shall be updated
     * @param values String Array containing the  values which shall be updated
     * @param where String containing the "WHERE"-clause of the SQL-statement
     */
    public void BuildQuery(String table, String[] fields, String[] values, String where) {
        int lengthFields=fields.length; int lengthValues=values.length;
        query.append("UPDATE ").append(table).append(" SET ");

        for (;lengthValues>1 && lengthFields>1; lengthValues--,lengthFields-- ) {
            query.append(fields[lengthFields-1]).append(" = '").append(values[lengthValues-1]).append("' , ");
        }

        query.append(fields[0]).append(" = ").append(values[0]).append(" ");

        if (where != null) {
            query.append(" WHERE ").append(where);
        }

        query.append(";");
        log.debug("query object was built");
    }

    /**
     *
     * @param table String containing the name of the table
     * @param field String containing the name of the field where a given value shall be updated
     * @param value String containing the value which shall be updated
     * @param where String containing the "WHERE"-clause of the SQL-statement
     */
    public void BuildQuery(String table, String field, String value, String where) {
        query.append("UPDATE ").append(table).append(" SET ").append(field);
        if (value == null) {
            query.append(" = NULL");
        } else {
            query.append(" = '").append(value).append("'");
        }

        if (where != null) {
            query.append(" WHERE ").append(where);
        }

        query.append(";");
        log.debug("query object was built");
    }

    /**
     * @see Query#WriteData()
     */
    public int Count() {
        return rows;
    }
}
