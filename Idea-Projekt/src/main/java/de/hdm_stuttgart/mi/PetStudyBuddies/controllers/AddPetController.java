package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.Pet;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPetController extends Controller implements ControlledScreen {
    private static final Logger log = LogManager.getLogger(AddToDoListController.class);
    @FXML
    Button ButtonCreatePet, ButtonBack;
    @FXML
    TextField TextFieldAddPet;

    public void buttonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonCreatePet) {
            log.debug("Open create create pet dialog");
            String petName = Utils.getInputString(TextFieldAddPet);

            if (petName == null) {
                log.error("No valid pet name entered.");
                Dialog.showError("Please enter a valid Petname!");
            } else {
                InsertQuery q = new InsertQuery(
                    "Pet",
                    new String[]{"Name", "Emotion", "UserID"},
                    new String[]{petName, "content", String.valueOf(Account.getLoggedUser().getID())}
                );

                if (q.Count() == 1) {
                    Pet myPet = PetController.getPet();
                    assert myPet != null;
                    log.debug("Pet existing? " + myPet.getName());
                    myPet.setEmotion();
                    closeSecondScene(actionEvent);
                    ScreensController.setStage(PetDashboardID);
                }
            }
        } else if (actionEvent.getSource() == ButtonBack) {
            closeSecondScene(actionEvent);
        }
    }
}
