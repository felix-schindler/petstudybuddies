package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

public abstract class Model extends SQLiteJDBC {
    private int ID;

    public int getID() {
        return ID;
    }

    public abstract String getTable();

    public String getField(String field) {
        return new Query("SELECT " + field + " FROM " + getTable() + " WHERE ID=" + ID).fetch();
    }
}
