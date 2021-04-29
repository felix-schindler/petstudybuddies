package de.hdm_stuttgart.mi.PetStudyBuddies.Core;

public class Model extends SQLiteJDBC {
    private int ID;

    public int getID() {
        return ID;
    }

    public String getField(String field) {
        String query = "SELECT " + field + " WHERE ID=" + ID;
        return "";
    }
}
