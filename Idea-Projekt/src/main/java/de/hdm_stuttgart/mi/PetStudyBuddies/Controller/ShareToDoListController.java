package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
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

public class ShareToDoListController implements Initializable {
    private static final Logger log = LogManager.getLogger(ShareToDoListController.class);
    @FXML
    Button ButtonBackShareList, ButtonShareList, ButtonCheckforUser;
    @FXML
    TextField TextFieldEMailShare;
    @FXML
    Label LabelNameToDoList;
    ObservableList<ToDoList> selectedList;

    @FXML
    public void buttonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonShareList) {
            log.debug("Open create new ToDoList dialog");
            String eingabe = TextFieldEMailShare.getText();
            log.debug("O");
            if (eingabe != null && !eingabe.isEmpty()) {
                // TODO
                try {
                    if (ToDoListController.selectedListAsObject.share(Integer.parseInt(new SelectQuery("User", "ID", "Username='" + TextFieldEMailShare.getText() + "'").fetch()))) {
                        Dialog.showInfo("Success", "User added");
                    }
                } catch (NumberFormatException e) {
                    log.catching(e);
                    log.error("User not found");
                    Dialog.showError("Failed to add user", "User does not exists");
                }
                closeSecondScene(actionEvent);
                ToDoListController.updateSelectedList();
                PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListViewList2.fxml");
            } else {
                Dialog.showError("Failed to add user", "User does not exists");

            }
        } else if (actionEvent.getSource() == ButtonBackShareList) {
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
        this.selectedList = ToDoListController.getSelectedList();
        for (ToDoList todolist : selectedList) {
            LabelNameToDoList.setText(todolist.getTitle());
        }

    }

}
