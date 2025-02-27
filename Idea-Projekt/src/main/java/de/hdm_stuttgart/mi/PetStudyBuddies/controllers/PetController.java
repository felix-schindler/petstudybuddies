package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.PictureFramework;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.Screens;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.DeleteQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.UpdateQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.Pet;
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

import java.net.URL;
import java.util.ResourceBundle;
/**
 * Controller for Pet
 */
public class PetController extends Controller implements Initializable {
    /**
     * log object for error handling
     */
    private final static Logger log = LogManager.getLogger(PetController.class);
    @FXML
    private Image ImageObject = null;
    @FXML
    private Pane ImagePane;
    @FXML
    private Label LabelPetname, LabelPetname2, LabelEmotion;
    @FXML
    private Button ButtonTakeCare, ButtonChangeName, ButtonDeletePet, ButtonEasterEgg;
    @FXML
    private ImageView PetPicture;
    /**
     * User Pet
     */
    private Pet myPet;

    /**
     * Returns Pet of user
     * @return Pet Object
     */
    public static Pet getPet() {
        String id = new SelectQuery("Pet", "ID", "UserID=" + Account.getLoggedUser().getID()).fetch();
        log.debug("Getting pet of user...");
        if (id == null || id.isEmpty() || id.equalsIgnoreCase("null"))
            return null;
        return new Pet(Integer.parseInt(id));
    }

    public static String getImage(String emotion) {
        return PictureFramework.pictures.get(emotion);
    }
    /**
     * Sets parameters needed to initialize scene
     * @param url URL location of the FXML file that was given to the FXMLLoader
     * @param resourceBundle ResourceBundle that was given to the FXMLLoader
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (getPet() == null) {
            loadSecondScene(AddPetID);  // Create pet

            if (getPet() == null) {     // No pet created
                Screens.setStage(DashboardID);
            }
        }

        myPet = getPet();
        if (myPet != null) {
            myPet.setEmotion();
            LabelPetname.setText(myPet.getName());
            LabelPetname2.setText(myPet.getName());
            LabelEmotion.setText(myPet.getEmotion());
            setPicture();
        }
    }
    /**
     * Handles actionEvents coming from Buttons
     * @param actionEvent type of Button
     */
    @FXML
    public void filterButtons(ActionEvent actionEvent) {
        log.debug("Button Event Handler called");
        if (actionEvent.getSource() == ButtonTakeCare) {
            Screens.setStage(ToDoListDashboardID);
        } else if (actionEvent.getSource() == ButtonChangeName) {
            loadSecondScene(ChangePetnameID);
            setFields();
        } else if (actionEvent.getSource() == ButtonDeletePet) {
            new DeleteQuery("Pet", "ID=" + myPet.getID());
            log.debug("Pet deleted");
            Screens.setStage(DashboardID);
        } else if (actionEvent.getSource() == ButtonEasterEgg) {
            new UpdateQuery("Pet", "Name", "Professor Kriha", "ID=" + myPet.getID());
            setFields();
        }
    }

    /**
     * Sets Picture of Pet according to current Emotion
     */
    public void setPicture() {
        switch (myPet.getEmotion()) {
            case "Happy" -> ImageObject = new Image(getImage(HappyPic));
            case "Content" -> ImageObject = new Image(getImage(ContentPic));
            case "Sad" -> ImageObject = new Image(getImage(SadPic));
            default -> ImageObject = new Image(getImage(NoPetPic));
        }

        log.debug("Filepath: " + ImageObject.getUrl());
        log.debug(ImageObject.getWidth());
        PetPicture.setImage(ImageObject);
        PetPicture.setFitHeight(400);
        PetPicture.setFitWidth(400);
    }

    /**
     * Sets Text fxml-fields
     */
    public void setFields() {
        myPet = getPet();
        if (myPet != null) {
            myPet.setEmotion();
            LabelPetname.setText(myPet.getName());
            LabelPetname2.setText(myPet.getName());
            LabelEmotion.setText(myPet.getEmotion());
        }
    }
}
