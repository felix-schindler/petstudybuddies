package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.ControlledScreen;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.Screens;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Abstract class with shared ressources of fxml-Controllers
 */
abstract class Controller implements ControlledScreen {
    /**
     * Log object for error handling
     */
    private static final Logger log = LogManager.getLogger(Controller.class);
    @FXML
    protected Button ApplicationDashboard, NotesDashboard, PetDashboard, ToDoDashboard, SettingsView, LoginView, RegisterView;

    /**
     * Handles actionEvents coming from Buttons
     * @param event type of Button
     */
    @FXML
    private void handleMenu(ActionEvent event) {
        log.debug("Setting new stage to " + event.getSource().toString());
        if (event.getSource() == ApplicationDashboard) {
            Screens.setStage(DashboardID);
        } else if (event.getSource() == NotesDashboard) {
            Screens.setStage(NoteID);
        } else if (event.getSource() == PetDashboard) {
            Screens.setStage(PetDashboardID);
        } else if (event.getSource() == ToDoDashboard) {
            Screens.setStage(ToDoListDashboardID);
        } else if (event.getSource() == SettingsView) {
            Screens.setStage(UserSettingsID);
        } else if (event.getSource() == LoginView) {
            Screens.setStage(LoginID);
        } else if (event.getSource() == RegisterView) {
            Screens.setStage(RegisterID);
        } else {
            log.error("No route specified");
        }
    }

    /**
     * Handles User logout
     */
    public void logout() {
        log.debug("User " + Account.getLoggedUser().getUsername() + " is logging out and being redirected.");
        Account.setUser(null);
        Screens.setStage(LoginID);
    }
}
