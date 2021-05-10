package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import java.sql.ResultSet;

public class SelectQuery extends Query{
    private final StringBuilder query = new StringBuilder();

    public SelectQuery(String table, String field, String where, String orderBy, String groupBy) {
        buildQuery(table, field, where, orderBy, groupBy);
        SetQueryString(query.toString());
    }

    public SelectQuery(String table, String field, String where, String orderBy, String groupBy, boolean run) {
        buildQuery(table, field, where, orderBy, groupBy);

        if (run) {
            SetQueryString(query.toString());
        }
    }


    public void buildQuery(String table, String field, String where, String orderBy, String groupBy) {
        query.append("SELECT " + field + " FROM " + table);
        if(where != null){
            query.append(" WHERE " + where);
        }
        if(groupBy != null){
            query.append(" GROUP BY" + groupBy);
        }
        if(orderBy != null){
            query.append(" ORDER BY" + orderBy);
        }
        query.append(";");
    }

    public ResultSet fetchAll() {
        return Fetch();
    }

    public String fetch() {
        return FetchSingleField();
    }
}
