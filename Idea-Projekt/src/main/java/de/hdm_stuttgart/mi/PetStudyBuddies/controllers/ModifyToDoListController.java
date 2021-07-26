package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.UpdateQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.ToDoList;
import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
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

public class ModifyToDoListController implements Initializable {
    private static final Logger log = LogManager.getLogger(ModifyToDoListController.class);
    @FXML
    Button ButtonBack, ButtonChangeTitle;
    @FXML
    TextField TextFieldNewTitle;
    @FXML
    Label LabelCurrentTitle;
    ObservableList<ToDoList> selectedList;


    public void buttonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonChangeTitle) {
            log.debug("Open create new Task dialog");
            String eingabe = TextFieldNewTitle.getText();
            log.debug("O");
            if (eingabe != null && !eingabe.isEmpty()) {
                new UpdateQuery("ToDoList", new String[]{"Title"}, new String[]{eingabe}, "ID = " + ToDoListController.selectedListID, true);
                closeSecondScene(actionEvent);
                ToDoListController.updateSelectedList();
                PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListViewList2.fxml");
            } else {
                Dialog.showInfo("Please enter a new Title for your ToDList!");
                log.debug("No New Title entered, Label set");
            }
        } else if (actionEvent.getSource() == ButtonBack) {
            closeSecondScene(actionEvent);
            PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListViewList2.fxml");
        }
    }

    @FXML
    public void closeSecondScene(ActionEvent actionEvent) {
        Stage secondStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        secondStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.selectedList = ToDoListController.getSelectedList();
        for (ToDoList todolist : selectedList) {
            LabelCurrentTitle.setText(todolist.getTitle());
        }

    }

}
