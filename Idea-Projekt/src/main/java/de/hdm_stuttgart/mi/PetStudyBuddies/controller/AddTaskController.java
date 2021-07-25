package de.hdm_stuttgart.mi.PetStudyBuddies.controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    Label LabelValidInputAddTask;
    @FXML
    DatePicker DatePickerAddNewTask;

    public void buttonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonCreateNewTask) {
            log.debug("Open create new ToDoList dialog");
            String eingabe = TextFieldAddNewTask.getText();
            log.debug("O");
            if (eingabe != null && !eingabe.isEmpty() && DatePickerAddNewTask.getValue() != null) {
                // TODO
                new InsertQuery("Task", new String[]{"ToDoListID", "Content", "Until"}, new String[]{String.valueOf(ToDoListController.getSelectedListID()), eingabe, DatePickerAddNewTask.getValue().toString()}, true);
                closeSecondScene(actionEvent);
                ToDoListController.updateSelectedList();
                PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListViewList2.fxml");
            } else {
                LabelValidInputAddTask.setText("Please enter a new Title for your List!");
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
