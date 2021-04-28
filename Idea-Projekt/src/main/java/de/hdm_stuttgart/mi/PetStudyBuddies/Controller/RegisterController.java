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
     * Handle Register activity
     */
    @FXML public void register() {
        final String eMail = emailField.getText().trim();
        final String username = usernameField.getText().trim();
        final String password = passwordField.getText().trim();
        final String repeatPassword = repeatPasswordField.getText().trim();

        String status="";

        if (eMail.length() <= 0 || username.length() <= 0 ||
                password.length() <= 0 || repeatPassword.length() <= 0) {
            status = "Bitte fülle alle Felder aus.";
            return;
        }

        if (!password.equals(repeatPassword)) {
            status = "Die Passwörter stimmen nicht überein.";
            return;
        }

        User user = Auth.register(eMail, username, password);
        if (user != null) {                 // Registrierung erfolgreich
            Account.setUser(user);
        } else {
            status = "EMail oder Passwort ist falsch.";
        }

        statusLabel.setText(status);
    }
}
