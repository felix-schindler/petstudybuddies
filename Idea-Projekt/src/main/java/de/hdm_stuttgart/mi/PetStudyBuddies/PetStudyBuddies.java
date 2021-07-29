package de.hdm_stuttgart.mi.PetStudyBuddies;

import de.hdm_stuttgart.mi.PetStudyBuddies.controllers.ScreensController;
import de.hdm_stuttgart.mi.PetStudyBuddies.controllers.ScreensFramework;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PetStudyBuddies extends Application {
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
        Account.setUser(new User(101));     // TODO REMOVE auto login!!!!
        launch(args);
    }

    /**
     * Gets called at the start of the application
     *
     * @param stage First stage to be set
     */
    @Override
    public void start(Stage stage) {
        // Set application icon
        // Windows
        stage.getIcons().add(new Image("file:data/icon.png"));
        // Mac
        ImageIcon logo = new ImageIcon("data/icon.png");
        if (Taskbar.isTaskbarSupported())
            if (Taskbar.getTaskbar().isSupported(Taskbar.Feature.ICON_IMAGE))
                Taskbar.getTaskbar().setIconImage(logo.getImage());

        stage.setMinWidth(854);
        stage.setMinHeight(480);
        stage.setResizable(false);
        stage.setFullScreen(false);
        if (Account.getLoggedUser() == null) {
            ScreensController.setStage(ScreensFramework.LoginFilename, ScreensFramework.LoginID);
        } else {
            ScreensController.setStage(ScreensFramework.DashboardFilename, ScreensFramework.DashboardID);
        }
        log.debug("PetStudyBuddies gestartet");
    }
}
