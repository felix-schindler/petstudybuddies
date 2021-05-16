package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;

public class SelectQuery extends Query{
    /**
     * log object for error handling
     */
    private static Logger log = LogManager.getLogger(SelectQuery.class);
    /**
     * Stores the Select SQL-Query statement
     */
    private final StringBuilder query = new StringBuilder();

    /**
     * Hands over parts of the SQL-Query Select statement to the BuildQuery method and then calls SetQueryString
     * @param table String containing the name of the table
     * @param field String containing the name of the field where values shall be outprinted
     * @param where String containing the "WHERE"-clause of the SQL-statement
     * @param orderBy String containing the "ORDER BY"-clause of the SQL-statement
     * @param groupBy String containing the "GROUP BY"-clause of the SQL-statement
     */
    public SelectQuery(String table, String field, String where, String orderBy, String groupBy) {
        buildQuery(table, field, where, orderBy, groupBy);
        log.debug("buildQuery method was run");
        SetQueryString(query.toString());
        log.debug("setQueryString method was run");
    }

    /**
     * Hands over parts of the SQL-Query Select statement to the BuildQuery method and then calls SetQueryString if run set true
     * @param table String containing the name of the table
     * @param field String containing the name of the field where values shall be outprinted
     * @param where String containing the "WHERE"-clause of the SQL-statement
     * @param orderBy String containing the "ORDER BY"-clause of the SQL-statement
     * @param groupBy String containing the "GROUP BY"-clause of the SQL-statement
     * @param run boolean if true built Query is set with SetQueryString method
     */
    public SelectQuery(String table, String field, String where, String orderBy, String groupBy, boolean run) {
        buildQuery(table, field, where, orderBy, groupBy);
        log.debug("buildQuery method was run");
        SetQueryString(query.toString(),run);
        log.debug("setQueryString method was run");
    }

    /**
     * Builds the SELECT-Query with the given parameters and saves query in the Stringbuilder query object
     * @param table String containing the name of the table
     * @param field String containing the name of the field where values shall be outprinted
     * @param where String containing the "WHERE"-clause of the SQL-statement
     * @param orderBy String containing the "ORDER BY"-clause of the SQL-statement
     * @param groupBy String containing the "GROUP BY"-clause of the SQL-statement
     */
    public void buildQuery(String table, String field, String where, String orderBy, String groupBy) {
        if(field== null || table== null){
            log.debug("table or field equals null");
        }else {
            query.append("SELECT " + field + " FROM " + table);
            if (where != null) {
                query.append(" WHERE " + where);
            }
            if (groupBy != null) {
                query.append(" GROUP BY " + groupBy);
            }
            if (orderBy != null) {
                query.append(" ORDER BY " + orderBy);
            }
            query.append(";");
            log.debug("query object was built");
        }
    }

    /**
     * Returns result of SELECT-statement if several DB-reuslts were found
     * @return result of SELECT-statement
     */
    public ResultSet fetchAll() {
        return Fetch();
    }

    /**
     * Returns result of SELECT-statement if one DB-reuslt was found
     * @return result of SELECT-statement
     */
    public String fetch() {
        return FetchSingleField();
    }
}
