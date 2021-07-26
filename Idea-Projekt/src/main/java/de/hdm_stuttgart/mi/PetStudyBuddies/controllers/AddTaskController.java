package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
<<<<<<< HEAD:Idea-Projekt/src/main/java/de/hdm_stuttgart/mi/PetStudyBuddies/Controller/AddTaskController.java
import de.hdm_stuttgart.mi.PetStudyBuddies.Views.Dialog;
=======
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
>>>>>>> 6013476976fac4d355d853df1c462930f78c2777:Idea-Projekt/src/main/java/de/hdm_stuttgart/mi/PetStudyBuddies/controllers/AddTaskController.java
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
<<<<<<< HEAD:Idea-Projekt/src/main/java/de/hdm_stuttgart/mi/PetStudyBuddies/Controller/AddTaskController.java

=======
>>>>>>> 6013476976fac4d355d853df1c462930f78c2777:Idea-Projekt/src/main/java/de/hdm_stuttgart/mi/PetStudyBuddies/controllers/AddTaskController.java
    @FXML
    DatePicker DatePickerAddNewTask;

    public void buttonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonCreateNewTask) {
            log.debug("Open create new ToDoList dialog");
            String eingabe = TextFieldAddNewTask.getText();
            if (eingabe != null && !eingabe.isEmpty() && DatePickerAddNewTask.getValue() != null) {
                // TODO
                new InsertQuery("Task", new String[]{"ToDoListID", "Content", "Until"}, new String[]{String.valueOf(ToDoListController.getSelectedListID()), eingabe, DatePickerAddNewTask.getValue().toString()}, true);
                closeSecondScene(actionEvent);
                ToDoListController.updateSelectedList();
                PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListViewList2.fxml");
            } else {
                Dialog.showError("Please enter a valid Title and Date!");
                log.debug("No New Valid Title or Date, Dialog shown");
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
