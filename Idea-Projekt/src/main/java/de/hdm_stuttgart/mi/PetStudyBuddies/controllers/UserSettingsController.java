package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.User;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class UserSettingsController extends Controller implements Initializable {
    /**
     * Logger object for smart (error) logging
     */
    private static final Logger log = LogManager.getLogger(UserSettingsController.class);
    /**
     * Logged-in user
     */
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
        labelUsername.setText(user.getUsername());
    }

    /**
     * Saves the new password / email
     */
    public void save() {
        String newPassStr = Utils.getInputString(newPassword),
                confirmPassStr = Utils.getInputString(confirmNewPassword),
                newMailStr = Utils.getInputString(newEmailAddress);

        // Change password
        if (newPassStr != null) {
            if (newPassStr.equals(confirmPassStr)) {
                user.setPassword(newPassStr);
                log.debug("New password was set for user " + user.getUsername());
            } else
                Dialog.showError("Password do not match", "The given passwords do not match, please check and try again.");
        }

        // Change email
        if (newMailStr != null) {
            user.setEMail(Utils.getInputString(newEmailAddress));
            if (Utils.verifyMail(newMailStr)) {
                user.setEMail(newMailStr);
                log.debug("New email was set for user " + user.getUsername());
            } else Dialog.showError("Invalid mail", "'" + newMailStr + "' is not a valid email address.");
        }

        try {
            user.save();
        } catch (Exception e) {
            log.catching(e);
            Dialog.showError("Failed to save changes, please try again.");
        }
    }
}
