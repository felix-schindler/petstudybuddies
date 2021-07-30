package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
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
 * Controller for Modifying To Do Lists
 */
public class ModifyToDoListController implements Initializable, ControlledScreen {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(ModifyToDoListController.class);
    @FXML
    private Button ButtonBack, ButtonChangeTitle;
    @FXML
    private TextField TextFieldNewTitle;
    @FXML
    private Label LabelCurrentTitle;
    /**
     * Sets parameters needed to initialize scene
     * @param url URL location of the FXML file that was given to the FXMLLoader
     * @param resourceBundle ResourceBundle that was given to the FXMLLoader
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LabelCurrentTitle.setText(ToDoListController.getEditTodo().getTitle());
    }
    /**
     * Handles actionEvents coming from Buttons
     * @param actionEvent type of Button
     */
    public void buttonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonChangeTitle) {
            log.debug("Open create new Task dialog");
            String title = Utils.getInputString(TextFieldNewTitle);
            if (title != null) {
                ToDoList todo = ToDoListController.getEditTodo();
                todo.setTitle(title);
                try {
                    todo.save();
                } catch (Exception e) {
                    log.catching(e);
                    log.error("Failed to save new ToDoList title");
                }
                closeSecondScene(actionEvent);
            } else {
                Dialog.showInfo("Please enter a new Title for your ToDoList!");
                log.debug("No New Title entered, Label set");
            }
        } else if (actionEvent.getSource() == ButtonBack) {
            closeSecondScene(actionEvent);
        }
    }

}
