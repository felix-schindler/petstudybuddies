package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.ControlledScreen;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.Task;
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

public class AssignTaskController implements Initializable, ControlledScreen {
    private static final Logger log = LogManager.getLogger(AssignTaskController.class);
    @FXML
    Button ButtonBack, ButtonAssignTask;
    @FXML
    TextField TextFieldUsernameShare;
    @FXML
    Label LabelNameTask;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log.debug("Open create new ToDoList dialog");
        LabelNameTask.setText(TaskListController.getEditTask().getContent());
    }

    @FXML
    public void buttonAction(ActionEvent actionEvent) {
        // Assign a task to a user
        if (actionEvent.getSource() == ButtonAssignTask) {
            String username = Utils.getInputString(TextFieldUsernameShare);
            if (username != null) {
                Task task = TaskListController.getEditTask();

                try {
                    String assigneeID = new SelectQuery("User", "ID", "Username = '" + username + "'").fetch();
                    if (assigneeID != null) {
                        task.setAssignedPerson(Integer.parseInt(assigneeID));
                        if (task.save()) {
                            Dialog.showInfo("Success", "Assigned " + username + " to this task.");
                            closeSecondScene(actionEvent);
                        }
                    } else {
                        Dialog.showError("User not found or you're sharing the Task with the same User. Please retry!");
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
}
