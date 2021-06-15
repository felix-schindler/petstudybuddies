package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;


import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.InsertQuery;
        import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
        import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.Node;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;
        import org.apache.logging.log4j.LogManager;
        import org.apache.logging.log4j.Logger;

        import java.net.URL;
        import java.util.ResourceBundle;

public class ToDoListControllerSecondWindowList implements Initializable {
    private static final Logger log = LogManager.getLogger(ToDoListControllerSecondWindowList.class);
    @FXML
    Button ButtonBackAddTask,ButtonCreateNewTask;
    @FXML
    TextField TextFieldAddNewTask;
    @FXML
    Stage secondStage;
    @FXML
    Label LabelValidInputAddTask;
    @FXML
    DatePicker DatePickerAddNewTask;

    public void buttonAction (ActionEvent actionEvent){
        if(actionEvent.getSource()==ButtonCreateNewTask) {
            String eingabe = TextFieldAddNewTask.getText();
            log.debug("neue");
            if (eingabe != null && !eingabe.isEmpty() && DatePickerAddNewTask.getValue()!=null) {
                //TODO
                new InsertQuery("Task", new String[]{"ToDoListID", "Content","Until"}, new String[]{String.valueOf(Account.getLoggedUser().getID()), eingabe,DatePickerAddNewTask.getValue().toString()}, true);
                closeSecondScene(actionEvent);
                PetStudyBuddies.setStage("/fxml/ToDoListViewList2.fxml");
            }else{
                LabelValidInputAddTask.setText("Please enter a new Title for your List!");
                log.debug("No New Title entered, Label set");
            }
        }
        if(actionEvent.getSource()==ButtonBackAddTask){
            closeSecondScene(actionEvent);
            PetStudyBuddies.setStage("/fxml/ToDoListViewList2.fxml");

        }
    }
    @FXML
    public void closeSecondScene(ActionEvent actionEvent) {
        Stage secondStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        secondStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}