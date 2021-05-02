package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

public class SelectQuery {
    private StringBuilder query = new StringBuilder();

    public SelectQuery(String table, String field, String where) {
        query.append("SELECT " + field + " FROM " + table + " WHERE " + where);
    }

    public String fetch() {
        return "";
    }
}
