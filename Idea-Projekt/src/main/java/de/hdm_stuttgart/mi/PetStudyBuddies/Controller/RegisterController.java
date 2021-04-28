package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Auth;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A simple controller providing a callback method {@link #register()}
 *
 */
public class RegisterController {
    private final static Logger log = LogManager.getLogger(RegisterController.class);

    @FXML private TextField emailField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField repeatPasswordField;
    @FXML private Label statusLabel;

    /**
     * Handle Login activity
     */
    @FXML public void register() {
        final String eMail = emailField.getText();
        final String username = usernameField.getText();
        final String password = passwordField.getText();
        final String repeatPassword = repeatPasswordField.getText();

        final String status;

        if (eMail.trim().length() <= 0 || username.trim().length() <= 0 ||
                password.trim().length() <= 0 || repeatPassword.trim().length() <= 0) {
            status = "Bitte fülle alle Felder aus.";
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
