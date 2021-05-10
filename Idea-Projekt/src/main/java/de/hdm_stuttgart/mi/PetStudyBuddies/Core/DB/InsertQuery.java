package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;


public class InsertQuery extends Query{

    private StringBuilder query = new StringBuilder();

    public InsertQuery(String table, String[] values) {
        BuildQuery(table, values);
        SetQueryString(query.toString());
    }

    public InsertQuery(String table, String[] values, boolean run) {
        BuildQuery(table, values);
        if (run) {
            SetQueryString(query.toString());
        }
    }

    public void BuildQuery(String table, String[] values) {
        int lengthValues = values.length;
        query.append("INSERT INTO " + table + " VALUES (");

        for (;lengthValues>1 ; lengthValues-- ) {
            query.append("'" + values[lengthValues] + "' , ");
        }

        query.append("'" + values[0] + "')");
    }

}
