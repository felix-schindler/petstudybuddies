package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

public class DeleteQuery {
    private StringBuilder query = new StringBuilder();

    public DeleteQuery(String table, String where) {
        query.append("DELETE FROM " + table);
        if(where != null){
            query.append(" WHERE " + where);
        }
        query.append(";");
    }
}
