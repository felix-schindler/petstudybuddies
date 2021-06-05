package de.hdm_stuttgart.mi.PetStudyBuddies;

import de.hdm_stuttgart.mi.PetStudyBuddies.Controller.ToDoListDashboard;
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

public class PetStudyBuddies extends Application {
    private static Logger log = LogManager.getLogger(ToDoListDashboard.class);
    private Stage Window;
    Scene SceneLogin;

    @Override
    public void start(Stage stage) throws Exception {
        Window = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        SceneLogin = new Scene(root);
        log.debug("FXML geladen");

        Window.setScene(SceneLogin);
        Window.setTitle("Login");
        Window.setResizable(false);

        // Set application icon
        // Windows
        stage.getIcons().add(new Image("file:data/icon.png"));
        // Mac
        ImageIcon logo = new ImageIcon("data/icon.png");
        if (Taskbar.isTaskbarSupported())
            if (Taskbar.getTaskbar().isSupported(Taskbar.Feature.ICON_IMAGE))
                Taskbar.getTaskbar().setIconImage(logo.getImage());

        Window.show();
        log.debug("Login gestartet");

        /*StackPane root = new StackPane();
        Scene scene = new Scene(root, 854, 480);
        Button btn = new Button("Hello world!");
        root.getChildren().add(btn);
        stage.setTitle("PetStudyBuddies");
        stage.setScene(scene);
        stage.show();*/
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setStage(Stage newStage) {
        newStage.show();
    }
}
