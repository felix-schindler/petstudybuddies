package de.hdm_stuttgart.mi.PetStudyBuddies.Core;

public class Model {
    private int ID;

    public int getID() {
        return ID;
    }

    public String getField(String field) {
        String query = "SELECT " + field + " WHERE 1";
        return "";
    }
}
