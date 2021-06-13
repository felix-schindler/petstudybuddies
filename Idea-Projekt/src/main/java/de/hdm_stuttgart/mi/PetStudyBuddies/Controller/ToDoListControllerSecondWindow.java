package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

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


public class ToDoListControllerSecondWindow implements Initializable {
    private static final Logger log = LogManager.getLogger(ToDoListControllerSecondWindow.class);
    @FXML
    Button ButtonCreateList,ButtonBack;
    @FXML
    TextField TextFieldAddNewList;
    @FXML
    Stage secondStage;
    @FXML
    Label LabelValidInput;

    public void buttonAction (ActionEvent actionEvent){
        if(actionEvent.getSource()==ButtonCreateList) {
            String eingabe = TextFieldAddNewList.getText();
            log.debug("neue");
            if (eingabe != null && !eingabe.isEmpty()) {
                new InsertQuery("ToDoList", new String[]{"UserID", "Title"}, new String[]{String.valueOf(Account.getLoggedUser().getID()), eingabe}, true);
                closeSecondScene(actionEvent);
                PetStudyBuddies.setStage("/fxml/ToDoListViewList.fxml");
            }else{
                LabelValidInput.setText("Please enter a new Title for your List!");
                log.debug("No New Title entered, Label set");
            }
        }
        if(actionEvent.getSource()==ButtonBack){
            closeSecondScene(actionEvent);
            PetStudyBuddies.setStage("/fxml/ToDoListDashboard2.fxml");

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
