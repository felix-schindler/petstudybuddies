package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

<<<<<<< HEAD:Idea-Projekt/src/main/java/de/hdm_stuttgart/mi/PetStudyBuddies/Controller/ShareToDoListController.java
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.ToDoList;
=======
>>>>>>> 6013476976fac4d355d853df1c462930f78c2777:Idea-Projekt/src/main/java/de/hdm_stuttgart/mi/PetStudyBuddies/controllers/ShareToDoListController.java
import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.ToDoList;
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

public class ShareToDoListController implements Initializable {
    private static final Logger log = LogManager.getLogger(ShareToDoListController.class);
    @FXML
<<<<<<< HEAD:Idea-Projekt/src/main/java/de/hdm_stuttgart/mi/PetStudyBuddies/Controller/ShareToDoListController.java
    Button ButtonBackShareList, ButtonShareList;
=======
    Button ButtonBack, ButtonShareList;
>>>>>>> 6013476976fac4d355d853df1c462930f78c2777:Idea-Projekt/src/main/java/de/hdm_stuttgart/mi/PetStudyBuddies/controllers/ShareToDoListController.java
    @FXML
    TextField TextFieldUsernameShare;
    @FXML
    Label LabelNameToDoList;
    ObservableList<ToDoList> selectedList;

    @FXML
    public void buttonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonShareList) {
            log.debug("Open create new ToDoList dialog");
            String eingabe = TextFieldUsernameShare.getText();
            log.debug("O");
            if (eingabe != null && !eingabe.isEmpty()) {
                // TODO
                try {
                    if (ToDoListController.selectedListAsObject.share(Integer.parseInt(new SelectQuery("User", "ID", "Username='" + TextFieldUsernameShare.getText() + "'").fetch()))) {
                        //if()
<<<<<<< HEAD:Idea-Projekt/src/main/java/de/hdm_stuttgart/mi/PetStudyBuddies/Controller/ShareToDoListController.java
                        new InsertQuery("ToDoListShare",new String[]{"UserID","ToDoListID"},new String[]{String.valueOf(Account.getLoggedUser().getID()), String.valueOf(TaskListController.selectedListId)});
=======
                        new InsertQuery("ToDoListShare", new String[]{"UserID", "ToDoListID"}, new String[]{String.valueOf(Account.getLoggedUser().getID()), String.valueOf(TaskListController.selectedListId)});
>>>>>>> 6013476976fac4d355d853df1c462930f78c2777:Idea-Projekt/src/main/java/de/hdm_stuttgart/mi/PetStudyBuddies/controllers/ShareToDoListController.java
                        Dialog.showInfo("Success", "User added");
                        closeSecondScene(actionEvent);
                        ToDoListController.updateSelectedList();
                        PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListViewList2.fxml");
<<<<<<< HEAD:Idea-Projekt/src/main/java/de/hdm_stuttgart/mi/PetStudyBuddies/Controller/ShareToDoListController.java
                    }else{
=======
                    } else {
>>>>>>> 6013476976fac4d355d853df1c462930f78c2777:Idea-Projekt/src/main/java/de/hdm_stuttgart/mi/PetStudyBuddies/controllers/ShareToDoListController.java
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
