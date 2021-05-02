package de.hdm_stuttgart.mi.PetStudyBuddies.Core;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.Query;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;

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
        return new SelectQuery(getTable(), field, "ID=" + ID).fetch();
    }
}
