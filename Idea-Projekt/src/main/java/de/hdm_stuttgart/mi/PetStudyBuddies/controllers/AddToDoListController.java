package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.ControlledScreen;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Controller for Adding To Do Lists
 */
public class AddToDoListController extends Controller implements ControlledScreen {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(AddToDoListController.class);
    @FXML
    private Button ButtonCreateList, ButtonBack;
    @FXML
    private TextField TextFieldAddNewList;

    /**
     * Handles actionEvents coming from Buttons
     * @param actionEvent type of Button
     */
    @FXML
    public void buttonAction(ActionEvent actionEvent) {
        // Create new ToDoList
        if (actionEvent.getSource() == ButtonCreateList) {
            log.debug("Open create new ToDoList dialog");
            String title = Utils.getInputString(TextFieldAddNewList);
            if (title != null && new SelectQuery("ToDoList", "ID", "Title='" + title + "' ").Count() == 0) {
                new InsertQuery("ToDoList", new String[]{"UserID", "Title"}, new String[]{String.valueOf(Account.getLoggedUser().getID()), title});

                closeSecondScene(actionEvent);
            } else {
                Dialog.showError("Please enter a new Title for your List!");
                log.debug("No New Title entered, Dialog shown");
            }
        } else if (actionEvent.getSource() == ButtonBack) {     // Close dialog
            closeSecondScene(actionEvent);
        }
    }
}
