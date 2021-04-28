package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Auth;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A simple controller providing a callback method {@link #doLogin()}
 *
 */
public class LoginController {
    private final static Logger log = LogManager.getLogger(LoginController.class);

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

    /**
     * Handle Login activity
     */
    @FXML public void doLogin() {
        final String eMail = emailField.getText();
        final String password = passwordField.getText();

        final StringBuilder status = new StringBuilder();

        boolean usernameEntered;
        if ((usernameEntered = eMail.trim().length() <= 0) || password.trim().length() <= 0) {
            if (usernameEntered) {
                status.append("Bitte geben Sie ein Passwort ein.");
            } else {
                status.append("Bitte geben Sie eine EMail ein.");
            }
        } else {
            User user = Auth.login(eMail, password);
            if (user != null) {                 // Login erfolgreich
                Account.setUser(user);
            } else {
                status.append("EMail oder Passwort ist falsch.");
            }
        }

        statusLabel.setText(status.toString());
    }
}