package de.hdm_stuttgart.mi.PetStudyBuddies.Core;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.UpdateQuery;

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
        return new SelectQuery(getTable(), field, "ID=" + ID, null, null).fetch();
    }

    public boolean setField(String field, String newValue){
        return new UpdateQuery(getTable(),field,newValue,null).Success();
    }
}
