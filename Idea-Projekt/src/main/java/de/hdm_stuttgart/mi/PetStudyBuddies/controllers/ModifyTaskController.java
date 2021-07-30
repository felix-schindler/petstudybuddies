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
/**
 * Controller for Modifying Tasks
 */
public class ModifyTaskController implements Initializable, ControlledScreen {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(ModifyTaskController.class);
    Task edit = TaskListController.getEditTask();
    @FXML
    private Button ButtonBack, ButtonCreateModifiedTask;
    @FXML
    private TextField TextFieldModifyTask;
    @FXML
    private DatePicker DatePickerModifyTask;


    /**
     * Sets parameters needed to initialize scene
     * @param url URL location of the FXML file that was given to the FXMLLoader
     * @param resourceBundle ResourceBundle that was given to the FXMLLoader
     */
    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFieldModifyTask.setText(edit.getContent());
        DatePickerModifyTask.setValue(edit.getUntil());
    }
    /**
     * Handles actionEvents coming from Buttons
     * @param actionEvent type of Button
     */
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
