package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import java.lang.reflect.Array;
import java.sql.ResultSet;

public class SelectQuery extends Query{
    private StringBuilder query = new StringBuilder();

    public SelectQuery(String table, String field, String where, String orderBy, String groupBy) {
        query.append("SELECT " + field + " FROM " + table);
        if(where != null){
            query.append(" WHERE " + where);
        }else if(groupBy != null){
            query.append(" GROUP BY" + groupBy);
        }else if(orderBy != null){
            query.append(" ORDER BY" + orderBy);
        }else query.append(";");

        super.SetQueryString(query.toString());
    }


    public ResultSet fetchAll() {
        return super.Fetch();
    }

    public String fetch() {
        return super.FetchSingleField();
    }

}
