package de.hdm_stuttgart.mi.PetStudyBuddies;

import de.hdm_stuttgart.mi.PetStudyBuddies.Controller.ToDoListController;
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
        setStage(fileName, "PetStudyBuddies");
    }

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
        window.setFullScreen(false);
        setStage("/fxml/login.fxml", "Login");
        window.setResizable(false);
        window.setTitle("Login");
        window.show();
        log.debug("Login gestartet");
    }

    public static String getPrimaryStage() {
        return "/fxml/ToDoList/ToDoListDashboard2.fxml";
    }
}
