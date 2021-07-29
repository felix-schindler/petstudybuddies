package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.User;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A simple controller providing a callback method {@link #login()}
 */
public class LoginController extends Controller implements ControlledScreen {
    private final static Logger log = LogManager.getLogger(LoginController.class);
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label statusLabel;

    /**
     * Handle Login activity
     */
    @FXML
    public void login() {
        final String eMail = Utils.getInputString(emailField);
        final String password = Utils.getInputString(passwordField);

        final StringBuilder status = new StringBuilder();

        final boolean passwordMissing = (password == null);
        final boolean eMailMissing = (eMail == null);
        final boolean bothMissing = (passwordMissing && eMailMissing);

        if (bothMissing) {
            status.append("Bitte gebe e-Mail und Passwort ein.");
            log.error("All fields are required");
        } else if (eMailMissing) {
            status.append("Bitte gebe eine e-Mail ein.");
            log.error("EMail is empty");
        } else if (passwordMissing) {
            status.append("Bitte gebe ein Passwort ein");
            log.error("Password is empty");
        } else {
            User user = null;
            String userID = new SelectQuery("User", "ID", "EMail='" + eMail + "' AND Password='" + Utils.sha1(password) + "'").fetch();
            if (userID != null)
                user = new User(Integer.parseInt(userID));
            // Login erfolgreich
            if (user != null) {
                log.debug("User " + user.getUsername() + " erfolgreich eingeloggt.");
                Account.setUser(user);

                // Redirect to Dashboard
                ScreensController.setStage(DashboardID);
            } else {
                Dialog.showError("EMail or Password is incorrect.");
                log.error("EMail or Password is incorrect.");
                log.warn(eMail + " tried to log in with wrong EMail / Password");
            }
        }

        statusLabel.setText(status.toString());
    }
}
