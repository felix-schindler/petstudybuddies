package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.InsertQuery;
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

public class AddTaskController {
    private static final Logger log = LogManager.getLogger(AddTaskController.class);
    @FXML
    Button ButtonBack, ButtonCreateNewTask;
    @FXML
    TextField TextFieldAddNewTask;
    @FXML
    DatePicker DatePickerAddNewTask;

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

    @FXML
    public void closeSecondScene(ActionEvent actionEvent) {
        Stage secondStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        secondStage.close();
    }
}
