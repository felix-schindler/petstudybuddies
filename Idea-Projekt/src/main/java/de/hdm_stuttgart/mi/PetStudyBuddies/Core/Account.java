package de.hdm_stuttgart.mi.PetStudyBuddies.Core;

import de.hdm_stuttgart.mi.PetStudyBuddies.Models.*;

/**
 * Account - This class holds all of the user specific data.
 */
public class Account {
    private User user;
    private Pet pet;
    private Calendar calendar;
    private Note[] notes;
    private ToDoList[] todolists;
    private Studies studies;

    public Account() {
        // TODO get pet, calendar, notes, todolists and studies out of some kind of storage (database, json, sqlite)
    }

    boolean deleteAccount() {
        // TODO Delete everything (Constrainteigenschaften -> DELETE)
        return false;
    }

    boolean createPet(String name) {
        // TODO Create pet and store pet
        return false;
    }

    boolean deletePet(String name) {
        // TODO Delete pet
        return false;
    }

    boolean createCalendar() {
        return false;
    }

    boolean deleteCalendar() {
        return false;
    }

    boolean createNote(String title, String content) {
        return false;
    }

    boolean deleteNote(int ID) {
        return false;
    }

    boolean createToDoList(String title) {
        return false;
    }

    boolean deleteToDoList(int ID) {
        return false;
    }

    boolean createStudies() {
        return false;
    }

    boolean deleteStudies(int ID) {
        return false;
    }
}
