package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.ToDoList;
import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import de.hdm_stuttgart.mi.PetStudyBuddies.Views.Dialog;
import javafx.collections.ObservableList;
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

public class AssignTaskController implements Initializable{
    private static final Logger log = LogManager.getLogger(AssignTaskController.class);
    @FXML
    Button ButtonBackAssignTask, ButtonAssignTask;
    @FXML
    TextField TextFieldUsernameShare;
    @FXML
    Label LabelNameTask, AssignTaskInvalidInput;

    @FXML
    public void buttonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonAssignTask) {
            LabelNameTask.setText(TaskListController.selectedTaskAsObject.getContent());
            log.debug("Open create new ToDoList dialog");
            String eingabe = TextFieldUsernameShare.getText();
            if (eingabe != null && !eingabe.isEmpty()) {
                // TODO
                try {
                    if (TaskListController.selectedTaskAsObject.assignPerson(TaskListController.selectedTaskAsObject.getID(),Account.getLoggedUser().getID())){

                        Dialog.showInfo("Success", "User added");
                        closeSecondScene(actionEvent);
                        //TODO
                        //ToDoListController.updateSelectedList();
                        PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListViewList2.fxml");
                    }else{
                        AssignTaskInvalidInput.setText("User not found or your sharing your Task with the same User. Please retry!");
                    }
                } catch (NumberFormatException e) {
                    log.catching(e);
                    log.error("User not found");
                    Dialog.showError("Failed to add user", "User does not exists");
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
