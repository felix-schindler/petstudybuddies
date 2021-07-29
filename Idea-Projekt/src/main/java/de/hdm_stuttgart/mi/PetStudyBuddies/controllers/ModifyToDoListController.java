package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
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

public class ModifyToDoListController implements Initializable {
    private static final Logger log = LogManager.getLogger(ModifyToDoListController.class);
    @FXML
    Button ButtonBack, ButtonChangeTitle;
    @FXML
    TextField TextFieldNewTitle;
    @FXML
    Label LabelCurrentTitle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LabelCurrentTitle.setText(ToDoListController.getEditTodo().getTitle());
    }

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

    public void closeSecondScene(ActionEvent actionEvent) {
        PetStudyBuddies.setStage("/fxml/ToDoList/TaskList.fxml");
        Stage secondStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        secondStage.close();
    }
}
