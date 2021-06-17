package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A simple controller providing a callback method {@link #register()}
 */
public class RegisterController extends Controller {
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
            if (new InsertQuery("User", new String[]{"EMail", "Username", "Password"}, new String[]{eMail, username, Utils.sha1(password)}).Count() == 1) {
                status.append("Registrierung war erfolgreich, sie können Sich nun einloggen!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                PetStudyBuddies.setStage("/fxml/Login.fxml", "Login");
            } else {
                status.append("Registrierung war nicht erfolgreich, bitte versuch es später erneut.");
            }
        }

        statusLabel.setText(status.toString());
    }
}
