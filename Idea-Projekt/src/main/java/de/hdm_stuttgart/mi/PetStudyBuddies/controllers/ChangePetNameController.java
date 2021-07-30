package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.ControlledScreen;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.Pet;
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

public class ChangePetNameController implements Initializable, ControlledScreen {
    private static final Logger log = LogManager.getLogger(ChangePetNameController.class);
    @FXML
    Button ButtonBack, ButtonChangeName;
    @FXML
    TextField TextFieldNewName;
    @FXML
    Label LabelCurrentName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LabelCurrentName.setText(PetController.getPet().getName());
    }

    public void buttonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonChangeName) {
            log.debug("Open Change Petname dialog");
            String petName = Utils.getInputString(TextFieldNewName);
            if (petName != null) {
                Pet myPet = PetController.getPet();
                myPet.setName(petName);
                try {
                    myPet.save();
                } catch (Exception e) {
                    log.catching(e);
                    log.error("Failed to save new Petname");
                }
                closeSecondScene(actionEvent);
            } else {
                Dialog.showInfo("Please enter a new Petname !");
                log.debug("No New Name entered, Label set");
            }
        } else if (actionEvent.getSource() == ButtonBack) {
            closeSecondScene(actionEvent);
        }
    }

}
