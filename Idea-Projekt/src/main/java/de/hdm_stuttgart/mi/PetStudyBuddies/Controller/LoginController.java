package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Auth;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.User;
import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

/**
 * A simple controller providing a callback method {@link #doLogin()}
 *
 */
public class LoginController {
    private final static Logger log = LogManager.getLogger(LoginController.class);

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;
    User loggedUser;

    /**
     * Handle Login activity
     */
    @FXML
    public void doLogin() throws IOException, SQLException {
        final String eMail = Utils.getInputString(emailField);
        final String password = Utils.getInputString(passwordField);

        final StringBuilder status = new StringBuilder();

        final boolean passwordMissing = (password == null);
        final boolean eMailMissing = (eMail == null);
        final boolean bothMissing = (passwordMissing && eMailMissing);

        if (bothMissing) {
            status.append("Bitte gebe e-Mail und Passwort ein.");
        } else if (eMailMissing) {
            status.append("Bitte gebe eine e-Mail ein.");
        } else if (passwordMissing) {
            status.append("Bitte gebe ein Passwort ein");
        } else {
            User user = Auth.login(eMail, password);
            // Login erfolgreich
            if (user != null) {
                log.debug("User " + user.getUsername() + " erfolgreich eingeloggt.");
                Account.setUser(user);
                this.loggedUser = Account.getLoggedUser();

                // Redirect to Dashboard
                PetStudyBuddies.setStage("/fxml/ToDoListDashboard2.fxml", "To Do List");
            } else {
                log.warn(eMail + " tried to log in with wrong EMail / Password");
                status.append("eMail oder Passwort ist falsch.");
            }
        }

        statusLabel.setText(status.toString());
    }
}
