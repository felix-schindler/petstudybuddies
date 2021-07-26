package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.UpdateQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModifyTaskController {
    private static final Logger log = LogManager.getLogger(ModifyTaskController.class);
    @FXML
    Button ButtonBack, ButtonCreateModifiedTask;
    @FXML
    TextField TextFieldModifyTask;
    @FXML
    DatePicker DatePickerModifyTask;

    public void buttonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonCreateModifiedTask) {
            log.debug("Open create new Task dialog");
            String eingabe = TextFieldModifyTask.getText();
            log.debug("O");
            if (eingabe != null && !eingabe.isEmpty() && DatePickerModifyTask.getValue() != null) {
                // TODO
                new UpdateQuery("Task", new String[]{"Content", "Until"}, new String[]{eingabe, DatePickerModifyTask.getValue().toString()}, "ID = " + TaskListController.selectedTaskAsObject.getID());
                closeSecondScene(actionEvent);
                ToDoListController.updateSelectedList();
                TaskListController.selectedTask=null;
                PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListViewList2.fxml");
            } else {
                Dialog.showInfo("Please enter a valid Title and Date for your Task!");
                log.debug("No New Title entered, Label set");
            }
        } else if (actionEvent.getSource() == ButtonBack) {
            closeSecondScene(actionEvent);
            PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListViewList2.fxml");
        }
    }

    @FXML
    public void closeSecondScene(ActionEvent actionEvent) {
        Stage secondStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        secondStage.close();
    }

}
