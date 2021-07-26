package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AssignTaskController implements Initializable{
    private static final Logger log = LogManager.getLogger(AssignTaskController.class);
    @FXML
    Button ButtonBackAssignTask, ButtonAssignTask;
    @FXML
    TextField TextFieldUsernameShare;
    @FXML
    Label LabelNameTask;

    @FXML
    public void buttonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonAssignTask) {
            LabelNameTask.setText(TaskListController.selectedTaskAsObject.getContent());
            log.debug("Open create new ToDoList dialog");
            String eingabe = TextFieldUsernameShare.getText();
            if (eingabe != null && !eingabe.isEmpty()) {
                // TODO
                try {
                    ResultSet assigneeID = new SelectQuery("User","ID","Username = '"+eingabe+"'").fetchAll();
                    log.debug("Assignee ID = " + assigneeID);
                    if (assigneeID.first()) {
                        if (TaskListController.selectedTaskAsObject.assignPerson(TaskListController.selectedTaskAsObject.getID(),assigneeID.getInt("ID"))){

                            Dialog.showInfo("Success", "User added");
                            closeSecondScene(actionEvent);
                            //TODO
                            //ToDoListController.updateSelectedList();
                            PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListViewList2.fxml");
                        }else{
                            Dialog.showError("User not found or your sharing your Task with the same User. Please retry!");
                        }
                    } else {
                        log.error("User in Database not found");
                        Dialog.showError("User not found or your sharing your Task with the same User. Please retry!");
                    }
                } catch (NumberFormatException e) {
                    log.catching(e);
                    log.error("User not found");
                    Dialog.showError("Failed to add user", "User does not exists");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            } else {
                Dialog.showError("Failed to add user", "User does not exists");

            }
        } else if (actionEvent.getSource() == ButtonBackAssignTask) {
            closeSecondScene(actionEvent);
            PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListViewList2.fxml");

        }
    }

    @FXML
    public void closeSecondScene(ActionEvent actionEvent) {
        Stage secondStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        secondStage.close();
        log.debug("Second Scene closed");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LabelNameTask.setText(TaskListController.selectedTaskAsObject.getContent());

    }
}
