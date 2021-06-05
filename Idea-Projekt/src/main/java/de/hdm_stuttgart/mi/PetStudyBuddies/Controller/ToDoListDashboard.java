package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.ToDoList;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.User;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.events.Event;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    @FXML TableView TableViewTest;
    @FXML ObservableList<ObservableList> data;
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
    @FXML public void initData(User user) throws SQLException {
        loggedUser = user;
        LabelUsername.setText(loggedUser.getUsername());

        this.NAll = new SelectQuery("ToDoList", "*", "UserID = " + loggedUser.getID(), null, null, true).Count();
        log.debug("Number of All To Do Lists " + NAll);
        LabelCountToDoAll.setText(String.valueOf(NAll));

        this.NFlagged = new SelectQuery("ToDoList", "*", "UserID = " + loggedUser.getID() + " AND Flagged = 'y'", null, null, true).Count();
        log.debug("Number of Flagged To Do Lists " + NFlagged);
        LabelCountToDoFlagged.setText(String.valueOf(NFlagged));

        this.NScheduled = new SelectQuery("ToDoList, Task", "*", "UserID = " + loggedUser.getID() + " AND date(datetime(Task.Until / 1000 , 'unixepoch')) IS NOT NULL", null, null, true).Count();
        log.debug("Number of To Do Lists " + NScheduled);
        LabelCountToDoScheduled.setText(String.valueOf(NScheduled));

        this.NToday = new SelectQuery("ToDoList, Task", "*", "UserID = " + loggedUser.getID() + " AND date(datetime(Task.Until / 1000 , 'unixepoch')) = date('now')", null, null, true).Count();
        log.debug("Number of To Do Lists " + NToday);
        LabelCountToDoToday.setText(String.valueOf(NToday));

        ResultSet AllUserLists = new SelectQuery("ToDoList", "Title", "UserID = " + loggedUser.getID(), "ID", null, true).fetchAll();
        String result = new SelectQuery("ToDoList", "Title", "UserID = " + loggedUser.getID(), "ID", null, true).fetch();
        log.debug(result+"result");
        ArrayList<String> ListsArray= new ArrayList<>();
        ListsArray.add(result);
        while(AllUserLists.next()){
            ListsArray.add(AllUserLists.getString("Title"));
            log.debug("ListsArray equals" + ListsArray);
        }
        //ObservableList data = FXCollections.observableArrayList(dataBaseArrayList(AllUserLists));
        //this.data=data;

        log.debug("Chosen Lists aka ResultSet: " + AllUserLists.toString());
        for (int i = 0; i < AllUserLists.getMetaData().getColumnCount(); i++) {
            //We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn(AllUserLists.getMetaData().getColumnName(i + 1));
            log.debug("New TableColumn created");
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });
            TableViewTest.getColumns().addAll(col);
            log.debug("TableColumn added to TableView");
            log.debug("Column [" + i + "] ");
        }


        /*while(AllUserLists.next()){
            log.debug("I'm in while");
            //Iterate Row
            /*ObservableList<ObservableList> data = FXCollections.observableArrayList();
            log.debug("List created");
            this.data=data;
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int i=1 ; i<=AllUserLists.getMetaData().getColumnCount(); i++){
                //Iterate Column
                row.add(AllUserLists.getString(i));
            }
            log.debug("Row [1] added "+row );
            this.data.add(row);
            log.debug("Row added to data");
        }*/
        log.debug("Data is"+data);
        TableViewTest.setItems(data);
        log.debug("Table View Data set");
    }

   public static void main(String[] args) {
        launch(args);
    }

   private ArrayList dataBaseArrayList(ResultSet resultSet) throws SQLException {
        ArrayList<String> data =  new ArrayList<>();
        while (resultSet.next()) {
            //ToDoList ListsUser = new ToDoList(loggedUser.getID());
            //   String ListTitle = resultSet.getString("Title");
            /*ListsUser..id.set(resultSet.getInt("id"));
            person.name.set(resultSet.getString("name"));
            person.married.set(resultSet.getBoolean("married"));*/
            data.add(resultSet.getString("Title"));
        }
        log.debug("ArrayList content"+data);
        return data;

    }


}
