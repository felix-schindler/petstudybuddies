package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface ControlledScreen {
    Logger log = LogManager.getLogger(ControlledScreen.class);

    @FXML
    default void closeSecondScene(ActionEvent actionEvent) {
        Stage secondStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        secondStage.close();
    }

    @FXML
    default void loadSecondScene(String filename) {
        try {
            Stage anotherStage = new Stage();
            FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource(filename));
            Parent secondPane = secondPageLoader.load();
            Scene secondScene = new Scene(secondPane);
            anotherStage.setScene(secondScene);
            anotherStage.requestFocus();
            anotherStage.showAndWait();
        } catch (Exception e) {
            log.catching(e);
            log.error("Failed to load input dialog");
        }
    }
}
