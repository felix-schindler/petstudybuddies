package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.models.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface UserRessources {
    int selectedListId = -1;
    ObservableList<Task> selectedTaskList = FXCollections.observableArrayList();
    Task selectedTaskAsObject = null;
}
