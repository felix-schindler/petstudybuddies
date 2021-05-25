package de.hdm_stuttgart.mi.PetStudyBuddies.Core;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.UpdateQuery;

public abstract class Model {
    /**
     * @return Name of the table (has to be exact SAME as in the DB!!)
     */
    public abstract String getTable();

    /**
     * @return true if saved successfully, false otherwise
     */
    public abstract boolean save();

    /**
     * ID of the database entry
     */
    protected final int ID;

    /**
     * Creates an object, linked to the database entry via the ID
     * @param ID The ID of the database entry
     */
    protected Model(int ID) {
        this.ID = ID;
    }

    /**
     * @return The ID of the database entry
     */
    public int getID() {
        return ID;
    }

    public String getField(String field) {
        return new SelectQuery(getTable(), field, "ID=" + ID, null, null).fetch();
    }

    /**
     * Updates a single field in the database
     * @param field Field in database to be updated
     * @param newValue New value to be set in the database
     */
    public void setField(String field, String newValue){
        new UpdateQuery(getTable(), field, newValue, null);
    }
}
