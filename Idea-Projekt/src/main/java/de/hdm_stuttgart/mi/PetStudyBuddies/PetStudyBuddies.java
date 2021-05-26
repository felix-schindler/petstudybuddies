package de.hdm_stuttgart.mi.PetStudyBuddies;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PetStudyBuddies extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 854, 480);
        Button btn = new Button("Hello world!");
        root.getChildren().add(btn);
        stage.setTitle("PetStudyBuddies");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void setStage(Stage newStage) {
        newStage.show();
    }
}
