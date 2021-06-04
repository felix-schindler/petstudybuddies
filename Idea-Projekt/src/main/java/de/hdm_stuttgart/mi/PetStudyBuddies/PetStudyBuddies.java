package de.hdm_stuttgart.mi.PetStudyBuddies;

import de.hdm_stuttgart.mi.PetStudyBuddies.Controller.ToDoListDashboard;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PetStudyBuddies extends Application {

    private static Logger log = LogManager.getLogger(ToDoListDashboard.class);
    private Stage Window;
    Scene SceneLogin,SceneToDoViewList;
    User loggedUser;

    @Override
    public void start(Stage stage) throws Exception {

        Window = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Scene scene = new Scene(root);
        SceneLogin = scene;
        log.debug("FXML geladen");

        Window.setScene(SceneLogin);
        Window.setTitle("Login");
        Window.setResizable(false);
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
