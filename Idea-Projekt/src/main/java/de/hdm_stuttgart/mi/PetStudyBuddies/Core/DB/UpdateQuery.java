package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

public class UpdateQuery extends Query{
    private StringBuilder query = new StringBuilder();

    /* TODO Update Query
    public UpdateQuery(String table, String[] fields, String[] values, String where) {

    }*/

    public UpdateQuery(String table, String field, String value, String where) {
        query.append("UPDATE " + table + " SET " + field);
        if (value == null) {
            query.append(" = NULL");
        } else {
            query.append(" = " + value);
        }

        if (where != null) {
            query.append(" WHERE" + where);
        }

        query.append(";");

        super.SetQueryString(query.toString());
    }

    @Override
    public boolean Success() {
        return super.Success();
    }
}
