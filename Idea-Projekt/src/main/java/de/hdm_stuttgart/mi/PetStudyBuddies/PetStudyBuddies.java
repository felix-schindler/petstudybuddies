package de.hdm_stuttgart.mi.PetStudyBuddies;

import de.hdm_stuttgart.mi.PetStudyBuddies.controllers.ControlledScreen;
import de.hdm_stuttgart.mi.PetStudyBuddies.controllers.ScreensController;
import de.hdm_stuttgart.mi.PetStudyBuddies.controllers.ScreensFramework;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.User;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class PetStudyBuddies extends Application implements ControlledScreen {
    /**
     * Logger for smart (error) logging
     */
    private static final Logger log = LogManager.getLogger(PetStudyBuddies.class);

    /**
     * The absolute main function
     * Starts the application
     *
     * @param args Command line arguments - gets passed to launch
     */
    public static void main(String[] args) {
        new Thread(ScreensFramework.init).start();
        //Account.setUser(new User(101));     // TODO REMOVE auto login!!!!
        launch(args);
    }

    /**
     * Gets called at the start of the application
     *
     * @param stage First stage to be set
     */
    @Override
    public void start(Stage stage) {
        stage.setMinWidth(854);
        stage.setMinHeight(480);
        stage.setResizable(false);
        stage.setFullScreen(false);
        if (Account.getLoggedUser() == null) {
            ScreensController.setStage(LoginID);
        } else {
            ScreensController.setStage(DashboardID);
        }
        log.debug("PetStudyBuddies gestartet");
    }
}
