package de.hdm_stuttgart.mi.PetStudyBuddies.core;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface ControlledScreen {
    Logger log = LogManager.getLogger(ControlledScreen.class);

    String DashboardID = "Dashboard";

    String NoteID = "Note";
    String EditNoteID = "Edit Note";

    String PetDashboardID = "Pet";
    String AddPetID = "Add Pet";
    String ChangePetnameID = "Change Petname";
    String HappyPic = "Happy";
    String SadPic = "Sad";
    String ContentPic = "Content";
    String NoPetPic = "No Pet";

    String TaskListID = "Lists";
    String ToDoListAddTaskID = "Add Task";
    String ToDoListAssignTaskID = "Assign Task";
    String ToDoListModifyTaskID = "Modify Task";

    String ToDoListDashboardID = "ToDoList Dashboard";
    String ToDoListAddListID = "Add List";
    String ToDoListModifyTitleID = "Modify Title";
    String ToDoListShareListID = "Share To Do List";

    String LoginID = "Login";
    String RegisterID = "Register";

    String UserSettingsID = "User Settings";

    default void closeSecondScene(ActionEvent actionEvent) {
        log.debug("Closing second scene...");
        Stage secondStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        secondStage.close();
    }

    default void loadSecondScene(String title) {
        try {
            Stage anotherStage = new Stage();
            FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource(ScreensFramework.screens.get(title)));
            Parent secondPane = secondPageLoader.load();
            Scene secondScene = new Scene(secondPane);
            anotherStage.setScene(secondScene);
            anotherStage.requestFocus();
            anotherStage.showAndWait();
            log.error("Opened second screen");
        } catch (Exception e) {
            log.catching(e);
            log.error("Failed to load input dialog");
        }
    }
}
