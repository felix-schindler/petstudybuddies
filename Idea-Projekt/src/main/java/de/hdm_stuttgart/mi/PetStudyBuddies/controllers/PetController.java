package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.DeleteQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.UpdateQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.Pet;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.Task;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.ToDoList;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.sql.rowset.CachedRowSet;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PetController extends Controller implements Initializable, ControlledScreen {
    private final static Logger log = LogManager.getLogger(PetController.class);

    @FXML
    private Label LabelPetname, LabelPetname2, LabelEmotion;

    @FXML
    private Button ButtonTakeCare, ButtonChangeName, ButtonDeletePet, ButtonEasterEgg;

    @FXML
    private ImageView PetPicture;

    @FXML
    Image ImageObject = null;

    @FXML
    Pane ImagePane;

    private Pet myPet;

    public static Pet getPet() {
        log.debug("Getting pet of user...");

        String id = new SelectQuery("Pet", "ID", "UserID=" + Account.getLoggedUser().getID()).fetch();
        if (id == null) {
            return null;
        }
        return new Pet(Integer.parseInt(id));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Pet myPet = getPet();

        if (myPet != null) {
            myPet.setEmotion();
            LabelPetname.setText(myPet.getName());
            LabelPetname2.setText(myPet.getName());
            LabelEmotion.setText(myPet.getEmotion());
            this.myPet=myPet;
                setPicture();
        }else{
            loadSecondScene(AddPetID);
            myPet=getPet();
            if (myPet != null) {
                myPet.setEmotion();
                LabelPetname.setText(myPet.getName());
                LabelPetname2.setText(myPet.getName());
                LabelEmotion.setText(myPet.getEmotion());
                this.myPet=myPet;
                    setPicture();

            }
        }

    }

    @FXML
    void filterButtons(ActionEvent actionEvent) {
        log.debug("Button Event Handler called");
        if (actionEvent.getSource() == ButtonTakeCare) {
            ScreensController.setStage(ToDoListDashboardID);
        } else if (actionEvent.getSource() == ButtonChangeName) {
            loadSecondScene(ChangePetnameID);
            setFields();
        } else if (actionEvent.getSource() == ButtonDeletePet) {
            new DeleteQuery("Pet", "ID=" + myPet.getID());
            log.debug("Pet deleted");
            ScreensController.setStage(ToDoListDashboardID);
        } else if (actionEvent.getSource() == ButtonEasterEgg) {
            new UpdateQuery("Pet", "Name", "Professor Kriha", "ID=" + myPet.getID());
            setFields();
        }
    }

    public static String getImage(String emotion){
        return PictureFramework.pictures.get(emotion);
    }

    public void setPicture() {
            if (myPet.getEmotion() == "Happy") {
                ImageObject = new Image(getImage(HappyPic));
            } else if (myPet.getEmotion() == "Content") {
                ImageObject = new Image(getImage(ContentPic));
            } else if (myPet.getEmotion() == "Sad") {
                ImageObject = new Image(getImage(SadPic));
            } else {
                ImageObject = new Image(getImage(NoPetPic));
            }
        log.debug("Filepath=" + ImageObject.getUrl());
        log.debug(ImageObject.getWidth());
        PetPicture.setImage(ImageObject);
        PetPicture.setFitHeight(400);
        PetPicture.setFitWidth(400);
    }


        public void setFields(){
            myPet=getPet();
            if (myPet != null) {
                myPet.setEmotion();
                LabelPetname.setText(myPet.getName());
                LabelPetname2.setText(myPet.getName());
                LabelEmotion.setText(myPet.getEmotion());
            }
        }
}
