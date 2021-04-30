package de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB;

public abstract class Model {
    private int ID;

    protected Model(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public abstract String getTable();

    public String getField(String field) {
        return new Query("SELECT " + field + " FROM " + getTable() + " WHERE ID=" + ID).fetch();
    }
}
