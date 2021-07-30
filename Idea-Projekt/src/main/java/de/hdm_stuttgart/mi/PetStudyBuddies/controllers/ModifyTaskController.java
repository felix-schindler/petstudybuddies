package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.Task;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ModifyTaskController implements Initializable, ControlledScreen {
    private static final Logger log = LogManager.getLogger(ModifyTaskController.class);
    Task edit = TaskListController.getEditTask();
    @FXML
    Button ButtonBack, ButtonCreateModifiedTask;
    @FXML
    TextField TextFieldModifyTask;
    @FXML
    DatePicker DatePickerModifyTask;

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL location, ResourceBundle resources) {
        TextFieldModifyTask.setText(edit.getContent());
        DatePickerModifyTask.setValue(edit.getUntil());
    }

    @FXML
    public void buttonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonCreateModifiedTask) {
            String content = Utils.getInputString(TextFieldModifyTask);
            LocalDate until = DatePickerModifyTask.getValue();
            boolean bothMissing = DatePickerModifyTask.getValue() == null && content == null;

            // Nothing was entered
            if (bothMissing) {
                Dialog.showInfo("Please enter a valid Title and Date for your Task!");
                log.debug("No New Title entered, Label set");
            }

            // Change content
            if (content != null) {
                edit.setContent(content);
                closeSecondScene(actionEvent);
            }

            // Change date
            if (until != null) {
                edit.setUntil(until);
            }

            // Save note if something was changed
            if (!bothMissing) {
                edit.save();
            }
        } else if (actionEvent.getSource() == ButtonBack) {
            closeSecondScene(actionEvent);
        }
    }

}
