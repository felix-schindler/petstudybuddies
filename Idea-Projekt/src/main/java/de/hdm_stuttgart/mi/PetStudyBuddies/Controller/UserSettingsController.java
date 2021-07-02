package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class UserSettingsController extends Controller implements Initializable {
    private static final Logger log = LogManager.getLogger(UserSettingsController.class);
    private final User user = Account.getLoggedUser();
    @FXML
    Label labelUsername;
    @FXML
    TextField newEmailAddress;
    @FXML
    TextField newPassword;
    @FXML
    TextField confirmNewPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelUsername.setText(Account.getLoggedUser().getUsername());
    }

    public void save() {
        //if NewPassword and ConfirmNewPassword are equal
        //the new Password and the new Email Address will be saved
        // TODO Du vergleichst hier die TextFields, NICHT den Inhalt.
        //  zuerst Utils.getInputString, dann abprüfen ob != null, dann festlegen.
        // TODO Außerdem wird nirgendwo die save funktion vom User aufgerufen,
        //  daher werden die Änderung nie gespeichert.
        if (newPassword == confirmNewPassword) {
            user.setPassword(Utils.getInputString(newPassword));
            user.setEMail(Utils.getInputString(newEmailAddress));
            log.info("Email and password changed successfully");
        }
        //if NewPassword and ConfirmNewPassword does not equal
        //the log.error will trigger
        else {
            log.error("Failed to save (new Password and confirm new Password are different)");
        }
    }
}
