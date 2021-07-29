package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PetController extends Controller implements Initializable, ControlledScreen {
    private final static Logger log = LogManager.getLogger(PetController.class);

    @FXML
    private Label LabelPetname, LabelPetname2, LabelEmotion;

    @FXML
    private Button  ButtonTakeCare, ButtonChangeName, ButtonDeletePet, ButtonEasterEgg;

    private CachedRowSet pet;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if(!pet.first()){
                loadSecondScene("/fxml/Pet/AddPet.fxml");
            }else{
                LabelPetname.setText(pet.getString("Name"));
                LabelPetname2.setText(pet.getString("Name"));
                LabelEmotion.setText(pet.getString("Emotion"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
