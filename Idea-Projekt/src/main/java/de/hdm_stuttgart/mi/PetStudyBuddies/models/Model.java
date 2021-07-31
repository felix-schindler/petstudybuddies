package de.hdm_stuttgart.mi.PetStudyBuddies.models;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.UpdateQuery;

abstract class Model {
    /**
     * ID of the database entry
     */
    private final int ID;

    /**
     * Creates an object, linked to the database entry via the ID
     *
     * @param ID The ID of the database entry
     */
    protected Model(int ID) {
        this.ID = ID;
    }

    /**
     * @return Name of the table (has to be exact SAME as in the DB!!)
     */
    public abstract String getTable();

    /**
     * @return true if saved successfully, false otherwise
     * @throws Exception Throws exception when data is invalid
     */
    public abstract boolean save() throws Exception;

    /**
     * @return The ID of the database entry
     */
    public int getID() {
        return ID;
    }

    protected String getField(String field) {
        return new SelectQuery(getTable(), field, "ID=" + ID, null, null).fetch();
    }

    /**
     * Updates a single field in the database
     *
     * @param field    Field in database to be updated
     * @param newValue New value to be set in the database
     */
    protected void setField(String field, String newValue) {
        new UpdateQuery(getTable(), field, newValue, null);
    }
}
