package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InsertQuery extends Query{
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(InsertQuery.class);

    /**
     * Stores the Insert SQL-Query string
     */
    private final StringBuilder query = new StringBuilder();

    /**
     * Count of effected rows
     */
    int rows = -1;


    /**
     * Hands over parts of the SQL-Query Insert statement to the BuildQuery method and then calls SetQueryString
     * @param table String containing the name of the table
     * @param fields String Array containing the names of the fields where values shall be inserted
     * @param values String Array containing the  values which shall be inserted
     */
    public InsertQuery(String table, String[] fields, String[] values) {
        BuildQuery(table, fields, values);
        SetQueryString(query.toString());
        rows = WriteData();
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
        SetQueryString(query.toString());
        if (run)
            rows = WriteData();
    }

    /**
     * Builds the INSERT-Query with the given parameters and saves SQL-Query in the StringBuilder query
     * @param table String containing the name of the table
     * @param fields String Array containing the names of the fields where values shall be inserted
     * @param values String Array containing the  values which shall be inserted
     */
    private void BuildQuery(String table, String[] fields, String[] values) {
        log.debug("BuildQuery method was run.");
        if (fields != null && values != null && fields.length == values.length) {
            int lengthValues = values.length;
            query.append("INSERT INTO ").append(table).append(" (");

            for (int i = 0; i < lengthValues-1; i++) {
                query.append(fields[i]).append(", ");
            }
            query.append(fields[lengthValues - 1]).append(")");

            query.append(" VALUES (");

            for (int i=0; i<lengthValues-1; i++) {
                query.append("'").append(values[i]).append("' , ");
            }

            query.append("'").append(values[lengthValues - 1]).append("')");
            query.append(";");
            log.debug("QueryString was built.");
        }
        log.error("Fields or values were null or not the same length.");
        if (fields != null && values != null)
            log.info("Lengths are: " + fields.length + " " + values.length);
        else
            log.info("Fields or values equals null.");
    }

    /**
     * @see Query#WriteData()
     */
    public int Count() {
        return rows;
    }
}
