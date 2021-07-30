package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Controller for Adding Tasks
 */
public class AddTaskController implements ControlledScreen {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(AddTaskController.class);
    @FXML
    private Button ButtonBack, ButtonCreateNewTask;
    @FXML
    private TextField TextFieldAddNewTask;
    @FXML
    private DatePicker DatePickerAddNewTask;


    /**
     * Handles actionEvents coming from Buttons
     * @param actionEvent type of Button
     */
    public void buttonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonCreateNewTask) {
            log.debug("Open create new ToDoList dialog");
            String content = Utils.getInputString(TextFieldAddNewTask);
            if (content == null || DatePickerAddNewTask.getValue() == null) {
                log.error("No valid date or title entered.");
                Dialog.showError("Please enter a valid Title and Date!");
            } else {
                new InsertQuery(
                        "Task",
                        new String[]{"ToDoListID", "Content", "Until"},
                        new String[]{String.valueOf(ToDoListController.getEditTodo().getID()), content, DatePickerAddNewTask.getValue().toString()}
                );
                closeSecondScene(actionEvent);
            }
        } else if (actionEvent.getSource() == ButtonBack) {
            closeSecondScene(actionEvent);
        }
    }
}
