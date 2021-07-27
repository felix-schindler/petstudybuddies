package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

abstract class Controller {
    /**
     * Log object for error handling
     */
    private static final Logger log = LogManager.getLogger(Controller.class);
    @FXML
    protected Button ApplicationDashboard;
    @FXML
    protected Button NotesDashboard;
    @FXML
    protected Button PetDashboard;
    @FXML
    protected Button ToDoDashboard;
    @FXML
    protected Button SettingsView;
    @FXML
    protected Button LoginView;
    @FXML
    protected Button RegisterView;

    @FXML
    private void handleMenu(ActionEvent event) {
        log.debug("Setting new stage...");
        if (event.getSource() == ApplicationDashboard) {
            PetStudyBuddies.setStage("/fxml/Dashboard/Dashboard.fxml", "Dashboard");
        } else if (event.getSource() == NotesDashboard) {
            PetStudyBuddies.setStage("/fxml/Notes/Notes.fxml", "Notes");
        } else if (event.getSource() == PetDashboard) {
            PetStudyBuddies.setStage("/fxml/Pet/PetDashboard.fxml", "Pet");
        } else if (event.getSource() == ToDoDashboard) {
            PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListDashboard2.fxml", "To Do");
        } else if (event.getSource() == SettingsView) {
            PetStudyBuddies.setStage("/fxml/User/UserSettings.fxml", "Settings");
        } else if (event.getSource() == LoginView) {
            PetStudyBuddies.setStage("/fxml/User/Login.fxml", "Login");
        } else if (event.getSource() == RegisterView) {
            PetStudyBuddies.setStage("/fxml/User/Register.fxml", "Register");
        } else {
            log.error("No route specified");
        }
    }
}
