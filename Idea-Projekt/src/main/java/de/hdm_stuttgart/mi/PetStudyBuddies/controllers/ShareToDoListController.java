package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.ToDoList;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
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

public class ShareToDoListController implements Initializable {
    private static final Logger log = LogManager.getLogger(ShareToDoListController.class);
    @FXML
    Button ButtonBack, ButtonShareList;
    @FXML
    TextField TextFieldUsernameShare;
    @FXML
    Label LabelNameToDoList;
    ToDoList selectedList;

    public void buttonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonShareList) {
            log.debug("Open create new ToDoList dialog");
            String username = Utils.getInputString(TextFieldUsernameShare);
            if (username != null) {
                try {
                    // If share to username successfull
                    if (ToDoListController.getEditTodo().share(Integer.parseInt(new SelectQuery("User", "ID", "Username='" + username + "'").fetch()))) {
                        new InsertQuery("ToDoListShare", new String[]{"UserID", "ToDoListID"}, new String[]{String.valueOf(Account.getLoggedUser().getID()), String.valueOf(ToDoListController.getEditTodo().getID())});
                        Dialog.showInfo("Success", "User added");
                        closeSecondScene(actionEvent);
                        // ToDoListController.updateSelectedList();
                        PetStudyBuddies.setStage("/fxml/ToDoList/TaskList.fxml");
                    } else {
                        Dialog.showError("Your sharing your To Do List with the same User. Please retry!");
                    }
                } catch (NumberFormatException e) {
                    log.catching(e);
                    log.error("User not found");
                    Dialog.showError("Failed to add user", "User does not exists");
                }

            } else {
                Dialog.showError("Failed to add user", "User does not exists");

            }
        } else if (actionEvent.getSource() == ButtonBack) {
            closeSecondScene(actionEvent);
            PetStudyBuddies.setStage("/fxml/ToDoList/TaskList.fxml");

        }
    }

    public void closeSecondScene(ActionEvent actionEvent) {
        Stage secondStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        secondStage.close();
        log.debug("Second Scene closed");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedList = ToDoListController.getEditTodo();
        LabelNameToDoList.setText(selectedList.getTitle());
    }
}
