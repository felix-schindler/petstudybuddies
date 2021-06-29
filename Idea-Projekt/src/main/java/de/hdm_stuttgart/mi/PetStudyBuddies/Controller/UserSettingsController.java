package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class UserSettingsController extends Controller implements Initializable{

    private static final Logger log = LogManager.getLogger(UserSettingsController.class);

    @FXML
    Label LabelUsername;
    @FXML
    TextField NewEmailAddress;
    @FXML
    TextField NewPassword;
    @FXML
    TextField ConfirmNewPassword;
    @FXML
    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelUsername.setText(Account.getLoggedUser().getUsername());
    }

    public void save() {
        //if NewPassword and ConfirmNewPassword are equal
        //the new Password and the new Email Address will be saved
        if (NewPassword == ConfirmNewPassword) {
            user.setPassword(Utils.getInputString(NewPassword));
            user.setEMail(Utils.getInputString(NewEmailAddress));
            log.info("Email and password changed successfully");
        }
        //if NewPassword and ConfirmNewPassword does not equal
        //the log.error will trigger
        else{
            log.error("Failed to save (new Password and confirm new Password are different)");
        }
    }
}
