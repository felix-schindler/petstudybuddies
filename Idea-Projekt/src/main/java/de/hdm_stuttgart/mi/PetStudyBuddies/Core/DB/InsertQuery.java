package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

public class InsertQuery extends Query{
    private final StringBuilder query = new StringBuilder();

    public InsertQuery(String table, String[] fields, String[] values) {
        BuildQuery(table, fields, values);
        SetQueryString(query.toString());
    }

    public InsertQuery(String table, String[] fields, String[] values, boolean run) {
        BuildQuery(table, fields, values);
        SetQueryString(query.toString(), run);
    }

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
        }
    }
}
