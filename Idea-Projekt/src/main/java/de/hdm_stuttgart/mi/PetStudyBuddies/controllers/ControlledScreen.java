package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;

public interface ControlledScreen {
    Logger log = LogManager.getLogger(ControlledScreen.class);


    String DashboardID = "Dashboard";

     String EditNoteID = "Edit Note";

    String NoteID = "Note";
    String AddPetID = "Add Pet";
    String PetDashboardID = "Pet";

    String ChangePetnameID = "Change Petname";

    String ToDoListAddListID ="Add List";

    String ToDoListAddTaskID ="Add Task";

    String ToDoListAssignTaskID ="Assign Task";

    String ToDoListDashboardID ="ToDoList Dashboard";

    String ToDoListModifyTaskID ="Modify Task";

    String ToDoListModifyTitleID ="Modify Title";

    String ToDoListShareListID ="Share To Do List";

    String TaskListID ="Lists";

    String LoginID = "Login";

    String RegisterID= "Register";

    String UserSettingsID = "User Settings";

    String HappyPic = "Happy";

    String SadPic = "Sad";

    String ContentPic = "Content";

    String NoPetPic = "No Pet";






    @FXML
    default void closeSecondScene(ActionEvent actionEvent) {
        Stage secondStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        secondStage.close();
    }

    @FXML
    default void loadSecondScene(String title){
        try {
            Stage anotherStage = new Stage();
            FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource(ScreensFramework.screens.get(title)));
            Parent secondPane = secondPageLoader.load();
            Scene secondScene = new Scene(secondPane);
            anotherStage.setScene(secondScene);
            anotherStage.requestFocus();
            anotherStage.showAndWait();
        } catch (Exception e) {
            log.catching(e);
            log.error("Failed to load input dialog");
        }
    }


}
