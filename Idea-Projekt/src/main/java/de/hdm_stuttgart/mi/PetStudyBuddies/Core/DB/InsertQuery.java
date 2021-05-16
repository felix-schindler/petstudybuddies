package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InsertQuery extends Query{
    /**
     * log object for error handling
     */
    private static Logger log = LogManager.getLogger(InsertQuery.class);

    /**
     * Stores the Insert SQL-Query statement
     */
    private final StringBuilder query = new StringBuilder();


    /**
     * Hands over parts of the SQL-Query Insert statement to the BuildQuery method and then calls SetQueryString
     * @param table String containing the name of the table
     * @param fields String Array containing the names of the fields where values shall be inserted
     * @param values String Array containing the  values which shall be inserted
     */
    public InsertQuery(String table, String[] fields, String[] values) {
        BuildQuery(table, fields, values);
        log.debug("buildQuery method was run");
        SetQueryString(query.toString());
        log.debug("setQueryString method was run");
    }

    /**
     * Hands over parts of the SQL-Query Insert statement to the BuildQuery method and then calls SetQueryString if run set true
     * @param table String containing the name of the table
     * @param fields String Array containing the names of the fields where values shall be inserted
     * @param values String Array containing the  values which shall be inserted
     * @param run boolean if true built Query is set with SetQueryString method
     */
    public InsertQuery(String table, String[] fields, String[] values, boolean run) {
        BuildQuery(table, fields, values);
        log.debug("buildQuery method was run");
        SetQueryString(query.toString(),run);
        log.debug("setQueryString method was run");



    }

    /**
     * Builds the INSERT-Query with the given parameters and saves query in the Stringbuilder query object
     * @param table String containing the name of the table
     * @param fields String Array containing the names of the fields where values shall be inserted
     * @param values String Array containing the  values which shall be inserted
     */
    private void BuildQuery(String table, String[] fields, String[] values) {
        if (fields != null && values != null && fields.length == values.length) {
            int lengthValues = values.length;
            query.append("INSERT INTO " + table + " (");

            for (int i = 0; i < lengthValues-1; i++) {
                query.append(fields[i] + ", ");
            }
            query.append(fields[lengthValues-1] + ")");

            query.append(" VALUES (");

            for (int i=0; i<lengthValues-1; i++) {
                query.append("'" + values[i] + "' , ");
            }

            query.append("'" + values[lengthValues-1] + "')");
            query.append(";");
            log.debug("query object was built");
        }
        log.debug("fields, values were null or not the same length");
    }

    /**
     * calls parent class and checks if Update-statement was correct
     * @return boolean true if statement was handled correctly by the DB
     */
    @Override
    public boolean Success() {
        return super.Success();
    }
}
