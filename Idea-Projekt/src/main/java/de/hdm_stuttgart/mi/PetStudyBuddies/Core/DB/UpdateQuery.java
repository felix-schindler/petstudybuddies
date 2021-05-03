package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

import java.sql.ResultSet;

public class UpdateQuery extends Query{

    private StringBuilder query = new StringBuilder();

    public UpdateQuery(String table, String field, String value, String where) {
        query.append("UPDATE " + field + " SET " + table);
        if(value != null){
            query.append(" = " + value);
        }else if(where != null){
            query.append(" WHERE" + where);
        }else query.append(";");

        super.SetQueryString(query.toString());
    }

    @Override
    public boolean Success() {
        return super.Success();
    }
}
