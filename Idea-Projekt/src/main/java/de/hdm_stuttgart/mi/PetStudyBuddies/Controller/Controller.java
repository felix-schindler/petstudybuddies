package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Models.ToDoList;
import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    protected Button StudiesDashboard;
    @FXML
    protected Button ToDoDashboard;
    @FXML
    protected Button LoginView;
    @FXML
    protected Button RegisterView;

    @FXML
    public static ObservableList<ToDoList> selectedList= FXCollections.observableArrayList();

    public void setSelectedList(ObservableList<ToDoList> selectedList){
        this.selectedList=selectedList;
    }
    public ObservableList<ToDoList> getSelectedList(){
        return selectedList;
    }

    @FXML
    private void handleMenu(ActionEvent event) {
        log.debug("Setting new stage...");
        if (event.getSource() == ApplicationDashboard) {
            // TODO
        } else if (event.getSource() == NotesDashboard) {
            PetStudyBuddies.setStage("/fxml/Notes/Notes.fxml", "Notes");
        } else if (event.getSource() == StudiesDashboard) {
            // TODO
        } else if (event.getSource() == ToDoDashboard) {
            PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListDashboard2.fxml", "To Do");
        } else if (event.getSource() == LoginView) {
            PetStudyBuddies.setStage("/fxml/Login.fxml", "Login");
        } else if (event.getSource() == RegisterView) {
            PetStudyBuddies.setStage("/fxml/Register.fxml", "Register");
        } else {
            log.error("No route specified");
        }
    }
}
