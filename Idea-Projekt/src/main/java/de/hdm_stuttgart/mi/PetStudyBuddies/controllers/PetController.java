package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.Pet;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.Task;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.ToDoList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import java.net.URL;
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
        }else{
            loadSecondScene(AddPetID);
        }
    }
}
