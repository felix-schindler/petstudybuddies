package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

public class UpdateQuery extends Query{
    private final StringBuilder query = new StringBuilder();

    public UpdateQuery(String table, String[] fields, String[] values, String where){
        BuildQuery(table, fields, values, where);
        SetQueryString(query.toString());
    }
    public UpdateQuery(String table, String field, String value, String where){
        BuildQuery(table, field, value, where);
        SetQueryString(query.toString());

    }
    public UpdateQuery(String table, String[] fields, String[] values, String where, boolean run){
        BuildQuery(table, fields, values, where);
        SetQueryString(query.toString());

        if (run) {
            SetQueryString(query.toString());
        }
    }
    public UpdateQuery(String table, String field, String value, String where, boolean run){
        BuildQuery(table, field, value, where);
        SetQueryString(query.toString());

        if (run) {
            SetQueryString(query.toString());
        }
    }
    public void BuildQuery(String table, String[] fields, String[] values, String where) {
        int lengthFields=fields.length; int lengthValues=values.length;
        query.append("UPDATE " + table + " SET ");
        for (;lengthValues>1 && lengthFields>1; lengthValues--,lengthFields-- ) {
            query.append(fields[lengthFields] + " = '" +values[lengthValues] + "' , ");
        }
        query.append(fields[0] + " = " +values[0] + " ");

        if (where != null) {
            query.append(" WHERE " + where);
        }

        query.append(";");

        super.SetQueryString(query.toString());
    }

    public void BuildQuery(String table, String field, String value, String where) {
        query.append("UPDATE " + table + " SET " + field);
        if (value == null) {
            query.append(" = NULL");
        } else {
            query.append(" = '" + value +"'");
        }

        if (where != null) {
            query.append(" WHERE " + where);
        }

        query.append(";");

        super.SetQueryString(query.toString());
    }

    @Override
    public boolean Success() {
        return super.Success();
    }
}
