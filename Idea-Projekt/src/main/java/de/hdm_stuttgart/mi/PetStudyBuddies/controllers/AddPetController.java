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

public class AddPetController extends Controller implements Initializable, ControlledScreen {

    private static final Logger log = LogManager.getLogger(AddToDoListController.class);
    @FXML
    Button ButtonCreatePet, ButtonBack;
    @FXML
    TextField TextFieldAddPet;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void buttonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonCreatePet) {
            log.debug("Open create new Pet dialog");
            String content = Utils.getInputString(TextFieldAddPet);
            if (content == null) {
                log.error("No valid date or title entered.");
                Dialog.showError("Please enter a valid Petname!");
            } else {
                new InsertQuery(
                        "Pet",
                        new String[]{"Name", "Emotion", "UserID"},
                        new String[]{content,"content", String.valueOf(Account.getLoggedUser().getID())}
                );
                Pet mypet = PetController.getPet();
                mypet.setEmotion();
                closeSecondScene(actionEvent);
                ScreensController.setStage(PetDashboardID);
            }
        } else if (actionEvent.getSource() == ButtonBack) {
            //closeSecondScene(actionEvent);
            ScreensController.setStage(DashboardID);
        }
    }

}
