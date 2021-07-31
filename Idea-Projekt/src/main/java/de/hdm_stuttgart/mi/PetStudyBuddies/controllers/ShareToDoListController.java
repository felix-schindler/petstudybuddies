package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.ControlledScreen;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.ToDoList;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * Controller for Sharing To Do Lists
 */
public class ShareToDoListController implements Initializable, ControlledScreen {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(ShareToDoListController.class);
    @FXML
    private Button ButtonBack, ButtonShareList;
    @FXML
    private TextField TextFieldUsernameShare;
    @FXML
    private Label LabelNameToDoList;
    @FXML
    public ToDoList selectedList;

    /**
     * Handles actionEvents coming from Buttons
     * @param actionEvent type of Button
     */
    public void buttonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonShareList) {
            log.debug("Open create new ToDoList dialog");
            String username = Utils.getInputString(TextFieldUsernameShare);
            if (username != null) {
                try {
                    // If share to username successfull
                    if (ToDoListController.getEditTodo().share(Integer.parseInt(new SelectQuery("User", "ID", "Username='" + username + "'").fetch()))) {
                        new InsertQuery("ToDoListShare", new String[]{"UserID", "ToDoListID"}, new String[]{String.valueOf(Account.getLoggedUser().getID()), String.valueOf(ToDoListController.getEditTodo().getID())});
                        Dialog.showInfo("Success", "User " + username + " added");
                        closeSecondScene(actionEvent);
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
        }
    }
    /**
     * Sets parameters needed to initialize scene
     * @param url URL location of the FXML file that was given to the FXMLLoader
     * @param resourceBundle ResourceBundle that was given to the FXMLLoader
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LabelNameToDoList.setText(ToDoListController.getEditTodo().getTitle());
    }
}
