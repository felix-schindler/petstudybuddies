package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PetController extends Controller implements Initializable {
    private final static Logger log = LogManager.getLogger(PetController.class);

    @FXML
    private Label LabelPetname, LabelPetname2, LabelEmotion;

    @FXML
    private Button  ButtonTakeCare, ButtonChangeName, ButtonDeletePet, ButtonEasterEgg;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*if(setPet()){
            PetStudyBuddies.setStage("/fxml/Pet/PetDashboard.fxml");
        }else{
            PetStudyBuddies.setStage("/fxml/Pet/AddPet.fxml");
            //loadSecondScene("/fxml/Pet/AddPet.fxml");
        }*/

    }
    @FXML
    public boolean setPet(){
        log.debug("Setting Pet");
        CachedRowSet pet = new SelectQuery("Pet","*","UserID="+ Account.getLoggedUser().getID()+" ").fetchAll();
        log.debug("Query successful");
        /*try {
            if(pet.first()) {
                LabelPetname.setText(pet.getString("Name"));
                LabelPetname2.setText(pet.getString("Name"));
                LabelEmotion.setText(pet.getString("Emotion"));
                return true;
            }else return false;
        } catch (SQLException throwables) {
            log.error("No pet existing");
            return false;
        }*/
        try {
            if (pet.first() ) {
                log.debug("Pet exists");
                return true;
            }else {
                log.debug("Pet does not exist");
                return false;}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            log.debug("Pet does not exist");
            return false;
        }

    }
    @FXML
    public void loadSecondScene(String filename){
        try {
            Stage anotherStage = new Stage();
            FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource(filename));
            Parent secondPane = secondPageLoader.load();
            Scene secondScene = new Scene(secondPane);
            anotherStage.setScene(secondScene);
            anotherStage.show();
        } catch (Exception e) {
            log.catching(e);
            log.error("Failed to load input dialog");
        }
    }
}