package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

abstract class Controller implements ControlledScreen {
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

    public void logout() {
        Account.setUser(null);
        ScreensController.setStage(LoginID);
    }

    @FXML
    private void handleMenu(ActionEvent event) {
        log.debug("Setting new stage...");
        if (event.getSource() == ApplicationDashboard) {
            ScreensController.setStage(DashboardID);
        } else if (event.getSource() == NotesDashboard) {
            ScreensController.setStage(NoteID);
        } else if (event.getSource() == PetDashboard) {
            ScreensController.setStage(PetDashboardID);
        } else if (event.getSource() == ToDoDashboard) {
            ScreensController.setStage(ToDoListDashboardID);
        } else if (event.getSource() == SettingsView) {
            ScreensController.setStage(UserSettingsID);
        } else if (event.getSource() == LoginView) {
            ScreensController.setStage(LoginID);
        } else if (event.getSource() == RegisterView) {
            ScreensController.setStage(RegisterID);
        } else {
            log.error("No route specified");
        }
    }
}
