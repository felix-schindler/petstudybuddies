package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Auth;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A simple controller providing a callback method {@link #register()}
 */
public class RegisterController {
    private final static Logger log = LogManager.getLogger(RegisterController.class);

    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField repeatPasswordField;
    @FXML
    private Label statusLabel;

    /**
     * Handle Register activity
     */
    @FXML
    public void register() {
        final String eMail = Utils.getInputString(emailField);
        final String username = Utils.getInputString(usernameField);
        final String password = Utils.getInputString(passwordField);
        final String repeatPassword = Utils.getInputString(repeatPasswordField);

        StringBuilder status = new StringBuilder();

        if (eMail == null || username == null || password == null || repeatPassword == null) {
            status.append("Bitte fülle alle Felder aus.");
        } else if (!Utils.verifyMail(eMail)) {
            status.append("Bitte gebe eine gültige e-Mail Adresse ein.");
        } else if (!password.equals(repeatPassword)) {
            status.append("Die eingegebenen Passwörter stimmen nicht überein.");
        } else {
            // Registrierung erfolgreich
            if (Auth.register(eMail, username, password)) {
                status.append("Registrierung war erfolgreich, sie können Sich nun einloggen!");
            } else {
                status.append("Registrierung war nicht erfolgreich, bitte versuch es später erneut.");
            }
        }

        statusLabel.setText(status.toString());
    }
}
