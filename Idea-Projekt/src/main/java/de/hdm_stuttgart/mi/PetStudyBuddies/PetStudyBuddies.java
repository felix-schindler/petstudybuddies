package de.hdm_stuttgart.mi.PetStudyBuddies;

import de.hdm_stuttgart.mi.PetStudyBuddies.Controller.ToDoListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class PetStudyBuddies extends Application {
    private static Logger log = LogManager.getLogger(ToDoListController.class);
    private static Stage window;

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/login.fxml")));
        Scene loginScene = new Scene(root, 854, 480);
        log.debug("FXML geladen");

        window.setScene(loginScene);
        window.setTitle("Login");
        window.setResizable(false);

        // Set application icon
        // Windows
        stage.getIcons().add(new Image("file:data/icon.png"));
        // Mac
        ImageIcon logo = new ImageIcon("data/icon.png");
        if (Taskbar.isTaskbarSupported())
            if (Taskbar.getTaskbar().isSupported(Taskbar.Feature.ICON_IMAGE))
                Taskbar.getTaskbar().setIconImage(logo.getImage());

        window.show();
        log.debug("Login gestartet");
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setStage(Stage newStage) {
        window = newStage;
        window.show();
    }

    public static void setStage(Stage newStage, String newTitle) {
        window = newStage;
        window.setTitle(newTitle);
        window.show();
    }

    public static void setStage(String fileName) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PetStudyBuddies.class.getResource(fileName));
            Scene mainScene = new Scene(loader.load());
            window.setScene(mainScene);
            PetStudyBuddies.setStage(window, "Dashboard");
            log.debug("Scene was successfully loaded");
        } catch (IOException e) {
            log.catching(e);
            log.error("Error occurred while loading scene");
        }
    }
}
