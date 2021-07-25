package de.hdm_stuttgart.mi.PetStudyBuddies.models;

interface Shareable {
    /**
     * Shares a shareable model to another user
     * @param ID UserID to be shared to
     * @return True on success, false otherwise
     */
    boolean share(int ID);
}
