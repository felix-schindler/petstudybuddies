package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;

public class ScreensController extends StackPane {
    private static final Logger log = LogManager.getLogger(ScreensController.class);
    public static Stage window = new Stage();
    private final HashMap<String, Stage> stages = new HashMap<>();

    public static void setStage(String fileName, String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PetStudyBuddies.class.getResource(fileName));
            Scene newScene = new Scene(loader.load());
            window.setScene(newScene);
            ScreensController.setStage(window, title);
            log.debug("Scene was successfully loaded");
        } catch (IOException e) {
            log.catching(e);
            log.error("Error occurred while loading scene");
        }
    }

    public static void setStage(Stage newStage) {
        setStage(newStage, "PetStudyBuddies");
    }

    public static void setStage(Stage newStage, String newTitle) {
        window = newStage;
        window.setTitle(newTitle);
        window.centerOnScreen();
        window.show();
    }

    public void addStage(String name, Stage screen) {
        stages.put(name, screen);
    }

    public Stage getStage(String name) {
        return stages.get(name);
    }
}
