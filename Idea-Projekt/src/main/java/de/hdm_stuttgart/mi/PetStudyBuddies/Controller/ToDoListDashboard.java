package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToDoListDashboard extends Application{

    private static Logger log = LogManager.getLogger(ToDoListDashboard.class);




    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ToDoListDashboard2.fxml"));
        Scene scene = new Scene(root);
        log.debug("FXML geladen");

        stage.setScene(scene);
        stage.setTitle("To-Do List Dashboard");
        stage.setResizable(false);
        stage.show();
        log.debug("To-Do List Dashboard gestartet");

    }

    public static void main(String[] args) {
        launch(args);
    }

}
