package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

public class DeleteQuery extends Query{
    private final StringBuilder query = new StringBuilder();

    public DeleteQuery(String table, String where) {
        BuildQuery(table, where);
        SetQueryString(query.toString());
    }
    public DeleteQuery(String table, String where, boolean run) {
        BuildQuery(table, where);
        if (run) {
            SetQueryString(query.toString());
        }
    }

    public void BuildQuery(String table, String where) {
        query.append("DELETE FROM " + table);
        if(where != null){
            query.append(" WHERE " + where);
        }
        query.append(";");
    }

}
