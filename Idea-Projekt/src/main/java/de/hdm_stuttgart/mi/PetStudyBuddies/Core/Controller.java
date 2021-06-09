package de.hdm_stuttgart.mi.PetStudyBuddies.Core;

import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

abstract public class Controller {
    /**
     * Log object for error handling
     */
    private static final Logger log = LogManager.getLogger(Controller.class);

    @FXML
    protected Button ApplicationDashboard = new Button();
    @FXML
    protected Button NotesDashboard = new Button();
    @FXML
    protected Button StudiesDashboard = new Button();
    @FXML
    protected Button ToDoDashboard = new Button();

    @FXML
    private void handleMenu(ActionEvent event) {
        log.debug("handleButtonAction called");
        if (event.getSource() == ApplicationDashboard) {
            PetStudyBuddies.setStage("/fxml/hello.fxml");
            log.debug("ApplicationDashboard loaded");
        } else if (event.getSource() == NotesDashboard) {
            PetStudyBuddies.setStage("/fxml/Notes/Notes.fxml");
            log.debug("NotesDashboard loaded");
        } else if (event.getSource() == StudiesDashboard) {
            PetStudyBuddies.setStage("/fxml/hello.fxml");
            log.debug("StudiesDashboard loaded");
        } else if (event.getSource() == ToDoDashboard) {
            PetStudyBuddies.setStage("/fxml/ToDoListDashboard2.fxml");
            log.debug("ToDoListDashboard loaded");
        } else {
            log.error("No route specified");
        }
    }
}
