package de.hdm_stuttgart.mi.PetStudyBuddies.Views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Driver class for a simple JavaFX demonstration.
 *
 */
public class RegisterView extends Application {
    private static final Logger log = LogManager.getLogger(RegisterView.class);

    /**
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        log.info("Starting register screen");

        final String fxmlFile = "/fxml/register.fxml";
        log.debug("Loading FXML for register view from: {}", fxmlFile);
        final FXMLLoader loader = new FXMLLoader();
        final Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        log.debug("Showing JFX scene");
        final Scene scene = new Scene(rootNode, 400, 200);
        //scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }
}
