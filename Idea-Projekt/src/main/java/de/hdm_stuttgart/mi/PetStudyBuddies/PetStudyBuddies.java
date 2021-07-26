package de.hdm_stuttgart.mi.PetStudyBuddies;

import de.hdm_stuttgart.mi.PetStudyBuddies.controllers.ToDoListController;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
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
    private static final Logger log = LogManager.getLogger(ToDoListController.class);
    private static Stage window;

    /**
     * The absolute main function
     * Starts the application
     *
     * @param args Command line arguments - gets passed to launch
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Sets a new stage and shows it in the main window
     * @param newStage New stage
     */
    public static void setStage(Stage newStage) {
        window = newStage;
        window.show();
    }

    /**
     * Sets a new stage and title and shows it in the main window
     * @param newStage New stage
     * @param newTitle New title
     */
    public static void setStage(Stage newStage, String newTitle) {
        window = newStage;
        window.setTitle(newTitle);
        window.centerOnScreen();
        window.show();
    }

    /**
     * Sets a new stage with the title "PetStudyBuddies" and shows it in the main window
     * @param fileName Filename of the FXML file
     */
    public static void setStage(String fileName) {
        setStage(fileName, "PetStudyBuddies");
    }

    /**
     * Sets a new stage and title and shows it in the main window
     * @param fileName Filename of the FXML file
     * @param title New title
     */
    public static void setStage(String fileName, String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PetStudyBuddies.class.getResource(fileName));
            Scene newScene = new Scene(loader.load());
            window.setScene(newScene);
            PetStudyBuddies.setStage(window, title);
            log.debug("Scene was successfully loaded");
        } catch (IOException e) {
            log.catching(e);
            log.error("Error occurred while loading scene");
        }
    }

    /**
     * Gets called at the start of the application
     * @param stage First stage to be set
     */
    @Override
    public void start(Stage stage) {
        window = stage;

        // Set application icon
        // Windows
        stage.getIcons().add(new Image("file:data/icon.png"));
        // Mac
        ImageIcon logo = new ImageIcon("data/icon.png");
        if (Taskbar.isTaskbarSupported())
            if (Taskbar.getTaskbar().isSupported(Taskbar.Feature.ICON_IMAGE))
                Taskbar.getTaskbar().setIconImage(logo.getImage());

        window.setMinWidth(854);
        window.setMinHeight(480);
        window.setResizable(false);
        window.setFullScreen(false);
        if (Account.getLoggedUser() == null) {
            setStage("/fxml/User/Login.fxml", "Login");
        } else {
            setStage("/fxml/User/Login.fxml", "Login");
        }
        log.debug("PetStudyBuddies gestartet");
    }
}
