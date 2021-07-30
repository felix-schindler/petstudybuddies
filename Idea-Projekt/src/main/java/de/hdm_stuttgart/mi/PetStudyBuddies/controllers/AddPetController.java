package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.ControlledScreen;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.Pet;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Controller for Adding a Pet
 */
public class AddPetController extends Controller implements ControlledScreen {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(AddPetController.class);

    @FXML
    private Button ButtonCreatePet, ButtonBack;
    @FXML
    private TextField TextFieldAddPet;


    /**
     * Handles actionEvents coming from Buttons
     * @param actionEvent type of Button
     */
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
                        new String[]{petName, "Content", String.valueOf(Account.getLoggedUser().getID())}
                );

                if (q.Count() == 1) {
                    Pet myPet = PetController.getPet();
                    log.debug("Pet existing? " + myPet.getName());
                    myPet.setEmotion();
                    closeSecondScene(actionEvent);
                    ScreensController.setStage(PetDashboardID);
                }else{
                    closeSecondScene(actionEvent);
                    ScreensController.setStage(DashboardID);
                }
            }
        } else if (actionEvent.getSource() == ButtonBack) {
            closeSecondScene(actionEvent);

        }
    }
}
