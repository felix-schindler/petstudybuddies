package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.ToDoList;
import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class AddToDoListController {
    private static final Logger log = LogManager.getLogger(AddToDoListController.class);
    @FXML
    Button ButtonCreateList, ButtonBack;
    @FXML
    TextField TextFieldAddNewList;
    @FXML
    Label LabelValidInput;

    @FXML
    public void buttonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonCreateList) {
            log.debug("Open create new ToDoList dialog");
            String eingabe = TextFieldAddNewList.getText();
            if (eingabe != null && !eingabe.isEmpty()) {
                new InsertQuery("ToDoList", new String[]{"UserID", "Title"}, new String[]{String.valueOf(Account.getLoggedUser().getID()), eingabe}, true);
                closeSecondScene(actionEvent);
                ObservableList<ToDoList> newList = FXCollections.observableArrayList();
                CachedRowSet newToDo = new SelectQuery("ToDoList","*","UserID = "+ Account.getLoggedUser().getID() + " AND Title= '"+eingabe + "'",null,null,true).fetchAll();
                try {
                    newList.add(new ToDoList(newToDo.getInt("ID")));
                    ToDoListController.setSelectedList(newList);
                    PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListViewList2.fxml");
                } catch (SQLException throwables) {
                    log.catching(throwables);
                    log.debug(("Created List could not be set as selected List"));
                }
            } else {
                LabelValidInput.setText("Please enter a new Title for your List!");
                log.debug("No New Title entered, Label set");
            }
        } else if (actionEvent.getSource() == ButtonBack) {
            closeSecondScene(actionEvent);
            PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListDashboard2.fxml");
        }
    }

    public void closeSecondScene(ActionEvent actionEvent) {
        Stage secondStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        secondStage.close();
    }
}
