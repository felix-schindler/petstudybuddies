package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.ControlledScreen;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.Pet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController extends Controller implements Initializable, ControlledScreen {
    public Label LabelUsername;
    public Label LabelAllNotes;
    public Button ButtonNotes;
    public Label LabelAllToDoLists;
    public Button ButtonToDoLists;
    public Label LabelStatusPet;
    public Button ButtonPet;
    @FXML
    private ImageView PetPicture;

    @FXML
    private Image ImageObject;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LabelUsername.setText(Account.getLoggedUser().getUsername());

        SelectQuery queryToDoLists = new SelectQuery("ToDoList", "ID", "UserID = " + Account.getLoggedUser().getID(), "ID", null);
        SelectQuery querySharedToDoLists = new SelectQuery("ToDoListShare", "ToDoListID", "UserID=" + Account.getLoggedUser().getID());
        SelectQuery queryNotes = new SelectQuery("Note", "ID", "UserID=" + Account.getLoggedUser().getID(), "DATETIME(LastEditedOn)", null);
        SelectQuery querySharedNotes = new SelectQuery("NoteShare", "NoteID", "UserID=" + Account.getLoggedUser().getID());

        LabelAllNotes.setText(String.valueOf(queryNotes.Count() + querySharedNotes.Count()));
        LabelAllToDoLists.setText(String.valueOf(queryToDoLists.Count() + querySharedToDoLists.Count()));

        Pet myPet = PetController.getPet();
        if (myPet != null) {
            LabelStatusPet.setText(myPet.getName());
            myPet.setEmotion();
            if (myPet.getEmotion() == "Happy") {
                ImageObject = new Image(PetController.getImage(HappyPic));
                log.debug("Happy Pet");
            } else if (myPet.getEmotion() == "Content") {
                ImageObject = new Image(PetController.getImage(ContentPic));
                log.debug("Content Pet");
            } else if (myPet.getEmotion() == "Sad") {
                ImageObject = new Image(PetController.getImage(SadPic));
                log.debug("Sad Pet");
            } else {
                ImageObject = new Image(PetController.getImage(NoPetPic));
                log.debug("No Pet");
            }
            log.debug("Filepath=" + ImageObject.getUrl());
            log.debug(ImageObject.getWidth());
            PetPicture.setImage(ImageObject);
            /*PetPicture.setFitHeight(400);
            PetPicture.setFitWidth(400);*/
        }
    }

    @FXML
    public void handleButton(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonPet) {
            ScreensController.setStage(PetDashboardID);
        } else if (actionEvent.getSource() == ButtonToDoLists) {
            ScreensController.setStage(ToDoListDashboardID);
        } else if (actionEvent.getSource() == ButtonNotes) {
            ScreensController.setStage(NoteID);
        }
    }
}
