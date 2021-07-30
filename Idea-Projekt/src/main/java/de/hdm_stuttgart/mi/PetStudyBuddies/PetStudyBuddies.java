package de.hdm_stuttgart.mi.PetStudyBuddies;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.ControlledScreen;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.PictureFramework;
import de.hdm_stuttgart.mi.PetStudyBuddies.controllers.ScreensController;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.ScreensFramework;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        // Initialize HashMaps of frameworks
        new Thread(ScreensFramework.init).start();
        new Thread(PictureFramework.init).start();
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
        if (Account.getLoggedUser() == null)
            ScreensController.setStage(LoginID);
        else
            ScreensController.setStage(DashboardID);
        log.debug("PetStudyBuddies gestartet");
    }
}
