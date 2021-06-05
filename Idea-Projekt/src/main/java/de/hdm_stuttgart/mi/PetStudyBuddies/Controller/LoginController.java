package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Auth;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.events.MouseEvent;

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
    @FXML public void doLogin() throws IOException, SQLException {
        //Stage stage;
        //Parent root;

        final String eMail = Utils.getInputString(emailField);
        final String password = Utils.getInputString(passwordField);

        final StringBuilder status = new StringBuilder();

        // TODO ask for better way

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
            if (user != null) {                 // Login erfolgreich
                log.debug("User " + user.getUsername() + " erfolgreich eingeloggt.");
                Account.setUser(user);
                this.loggedUser=Account.getLoggedUser();
                //stage = (Stage) statusLabel.getScene().getWindow();
                //root = FXMLLoader.load(getClass().getResource("/fxml/hello.fxml"));
                //Scene scene = new Scene(root);
                //stage.setScene(scene);
                //stage.show();
                changeToMainWindow();
            } else {
                log.warn(eMail + " tried to log in with wrong EMail / Password");
                status.append("eMail oder Passwort ist falsch.");
            }
        }

        statusLabel.setText(status.toString());
    }

    /*@FXML public void initData(User user) {
        loggedUser = user;
        labelUser.setText(selectedUser.getEmail());
    }*/

    public void changeToMainWindow() throws IOException, SQLException {
        log.debug("changetoMain was called");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/ToDoListDashboard2.fxml"));
        Parent root = loader.load();
        Scene mainScene = new Scene(root);
        log.debug("Scene was loaded");
        log.debug("loggedUser"+loggedUser.getUsername());

        // access the controller

        ToDoListDashboard mainWindowController = loader.getController();
        mainWindowController.initData(loggedUser);

        Stage primaryStage = (Stage) statusLabel.getScene().getWindow();
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
