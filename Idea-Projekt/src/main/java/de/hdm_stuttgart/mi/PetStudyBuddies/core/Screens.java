package de.hdm_stuttgart.mi.PetStudyBuddies.core;

import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class Screens {
    private static final Logger log = LogManager.getLogger(Screens.class);
    private static Stage window = new Stage();
    private final HashMap<String, Stage> stages = new HashMap<>();

    public static void setStage(String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PetStudyBuddies.class.getResource(ScreensFramework.screens.get(title)));
            log.debug("title is " + title + " Filename is " + ScreensFramework.screens.get(title));
            Scene newScene = new Scene(loader.load());
            window.setScene(newScene);
            Screens.setStage(window, title);
            log.debug("Scene " + title + " was successfully loaded");
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
        // Set application icon
        // Windows
        window.getIcons().add(new Image("file:data/icon.png"));
        // Mac
        ImageIcon logo = new ImageIcon("data/icon.png");
        if (Taskbar.isTaskbarSupported())
            if (Taskbar.getTaskbar().isSupported(Taskbar.Feature.ICON_IMAGE))
                Taskbar.getTaskbar().setIconImage(logo.getImage());

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
