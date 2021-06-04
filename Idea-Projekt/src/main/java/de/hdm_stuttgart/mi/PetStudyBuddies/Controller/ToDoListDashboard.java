package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.events.Event;

import java.io.IOException;
import java.sql.ResultSet;

public class ToDoListDashboard extends Application {

    private static Logger log = LogManager.getLogger(ToDoListDashboard.class);
    @FXML Button ApplicationDashboard = new Button();
    @FXML Button NotesDashboard = new Button();
    @FXML Button StudiesDashboard = new Button();
    @FXML Button ToDoDashboard = new Button();
    @FXML Label LabelUsername;
    @FXML Label LabelCountToDoToday, LabelCountToDoScheduled, LabelCountToDoFlagged, LabelCountToDoAll;
    @FXML Button ListsToday, ListsScheduled,ListsFlagged,ListsAll;
    @FXML ScrollPane ScrollPaneAllLists;

    private Stage Window;
    Scene SceneToDoDashboard,SceneToDoViewList;
    User loggedUser;
    int NToday, NScheduled,NFlagged,NAll;

    public void setLoggedUser(User loggedUser) {
        this.loggedUser=loggedUser;
    }

    @FXML public void navigateApplicationDashboard(ActionEvent event){
        // TODO Rufe Scene des Start Dashboards auf
    }

    @FXML public void navigateNotesDashboard(ActionEvent event){
        // TODO Rufe Scene des Start Dashboards auf
    }

    @FXML public void navigateStudiesDashboard(ActionEvent event){
        ToDoDashboard.setOnAction(actionEvent -> Window.setScene(SceneToDoDashboard));
    }

   /* @FXML public void navigateToDoDashboard(ActionEvent event) throws IOException {
        Window.setScene(SceneToDoDashboard);
        Window.setTitle("To-Do List Dashboard");
        Window.setResizable(false);
        Window.show();
        log.debug("Scene set back to Dashboard");
    }*/

   @FXML
   private void handleButtonAction (ActionEvent event) throws Exception {
       Stage stage;
       Parent root;

        log.debug("handleButtonAction called");
       if(event.getSource()==ApplicationDashboard){
           stage = (Stage) ApplicationDashboard.getScene().getWindow();
           root = FXMLLoader.load(getClass().getResource("/fxml/hello.fxml"));
           log.debug("ApplicationDashboard loaded");
       }
       else if(event.getSource()==NotesDashboard){
           stage = (Stage) NotesDashboard.getScene().getWindow();
           root = FXMLLoader.load(getClass().getResource("/fxml/hello.fxml"));
           log.debug("NotesDashboard loaded");
       }
       else if(event.getSource()==StudiesDashboard){
           stage = (Stage) StudiesDashboard.getScene().getWindow();
           root = FXMLLoader.load(getClass().getResource("/fxml/hello.fxml"));
           log.debug("StudiesDashboard loaded");
       }
       else if(event.getSource()==ToDoDashboard){
           stage = (Stage) ToDoDashboard.getScene().getWindow();
           root = FXMLLoader.load(getClass().getResource("/fxml/ToDoListDashboard2.fxml"));
           log.debug("ToDoListDashboard loaded");
       }else {
           stage = (Stage) ToDoDashboard.getScene().getWindow();
           root = FXMLLoader.load(getClass().getResource("/fxml/ToDoListDashboard2.fxml"));
           log.debug("Alternative loaded");
       }
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
   }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ToDoListDashboard2.fxml"));
        Scene scene = new Scene(root);
        SceneToDoDashboard = scene;
        log.debug("FXML geladen");

        Window.setScene(SceneToDoDashboard);
        Window.setTitle("To-Do List Dashboard");
        Window.setResizable(false);
        Window.show();
        log.debug("To-Do List Dashboard gestartet");

        LabelUsername.setText(loggedUser.getUsername());

   //     ToDoDashboard.setOnAction(actionEvent -> Window.setScene(SceneToDoDashboard));

    }
    @FXML public void initData(User user) {
        loggedUser = user;
        LabelUsername.setText(loggedUser.getUsername());

        this.NAll=new SelectQuery("ToDoList","*","UserID = " + loggedUser.getID(),null,null,true).Count();
        log.debug("Number of All To Do Lists "+NAll);
        LabelCountToDoAll.setText(String.valueOf(NAll));

        this.NFlagged=new SelectQuery("ToDoList","*","UserID = " + loggedUser.getID() + " AND Flagged = 'y'",null,null,true).Count();
        log.debug("Number of Flagged To Do Lists "+NFlagged);
        LabelCountToDoFlagged.setText(String.valueOf(NFlagged));

        this.NScheduled=new SelectQuery("ToDoList, Task","*","UserID = " + loggedUser.getID() + " AND date(datetime(Task.Until / 1000 , 'unixepoch')) IS NOT NULL",null,null,true).Count();
        log.debug("Number of To Do Lists "+NScheduled);
        LabelCountToDoScheduled.setText(String.valueOf(NScheduled));

        this.NToday=new SelectQuery("ToDoList, Task","*","UserID = " + loggedUser.getID() + " AND date(datetime(Task.Until / 1000 , 'unixepoch')) = date('now')",null,null,true).Count();
        log.debug("Number of To Do Lists "+NToday);
        LabelCountToDoToday.setText(String.valueOf(NToday));

        ResultSet AllUserLists = new SelectQuery("ToDoList","*","UserID = " + loggedUser.getID(),"ID",null,true).fetchAll();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
