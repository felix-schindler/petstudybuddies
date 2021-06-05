package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Controller;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ToDoListController extends Controller {
    private static Logger log = LogManager.getLogger(ToDoListController.class);
    @FXML Button ApplicationDashboard = new Button();
    @FXML Button NotesDashboard = new Button();
    @FXML Button StudiesDashboard = new Button();
    @FXML Button ToDoDashboard = new Button();
    @FXML Label LabelUsername;
    @FXML Label LabelCountToDoToday, LabelCountToDoScheduled, LabelCountToDoFlagged, LabelCountToDoAll;
    @FXML Button ListsToday, ListsScheduled, ListsFlagged, ListsAll;
    @FXML ScrollPane ScrollPaneAllLists;
    @FXML TableView TableViewTest;
    @FXML ObservableList<ObservableList> data;
    private Stage Window;
    Scene SceneToDoDashboard,SceneToDoViewList;
    int NToday, NScheduled,NFlagged,NAll;

    @FXML
    public void navigateApplicationDashboard(ActionEvent event){
        // TODO Rufe Scene des Start Dashboards auf
    }

    @FXML
    public void navigateNotesDashboard(ActionEvent event){
        // TODO Rufe Scene des Start Dashboards auf
    }

    @FXML
    public void navigateStudiesDashboard(ActionEvent event){
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
        if(event.getSource() == ApplicationDashboard){
            stage = (Stage) ApplicationDashboard.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/fxml/hello.fxml"));
            log.debug("ApplicationDashboard loaded");
        } else if(event.getSource() == NotesDashboard){
            stage = (Stage) NotesDashboard.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/fxml/Notes.fxml"));
            log.debug("NotesDashboard loaded");
        } else if(event.getSource() == StudiesDashboard){
            stage = (Stage) StudiesDashboard.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/fxml/hello.fxml"));
            log.debug("StudiesDashboard loaded");
        } else if(event.getSource() == ToDoDashboard){
            stage = (Stage) ToDoDashboard.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/fxml/ToDoListDashboard2.fxml"));
            log.debug("ToDoListDashboard loaded");
        } else {
            stage = (Stage) ToDoDashboard.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/fxml/ToDoListDashboard2.fxml"));
            log.debug("Alternative loaded");
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        LabelUsername.setText(Account.getLoggedUser().getUsername());

        try {
            this.NAll = new SelectQuery("ToDoList", "*", "UserID = " + Account.getLoggedUser().getID()).Count();
            log.debug("Number of All To Do Lists " + NAll);
            LabelCountToDoAll.setText(String.valueOf(NAll));

            this.NFlagged = new SelectQuery("ToDoList", "*", "UserID = " + Account.getLoggedUser().getID() + " AND Flagged = 'y'").Count();
            log.debug("Number of Flagged To Do Lists " + NFlagged);
            LabelCountToDoFlagged.setText(String.valueOf(NFlagged));

            this.NScheduled = new SelectQuery("ToDoList, Task", "*", "UserID = " + Account.getLoggedUser().getID() + " AND date(datetime(Task.Until / 1000 , 'unixepoch')) IS NOT NULL").Count();
            log.debug("Number of To Do Lists " + NScheduled);
            LabelCountToDoScheduled.setText(String.valueOf(NScheduled));

            this.NToday = new SelectQuery("ToDoList, Task", "*", "UserID = " + Account.getLoggedUser().getID() + " AND date(datetime(Task.Until / 1000 , 'unixepoch')) = date('now')").Count();
            log.debug("Number of To Do Lists " + NToday);
            LabelCountToDoToday.setText(String.valueOf(NToday));

            ResultSet AllUserLists = new SelectQuery("ToDoList", "Title", "UserID = " + Account.getLoggedUser().getID(), "ID", null).fetchAll();
            String result = new SelectQuery("ToDoList", "Title", "UserID = " + Account.getLoggedUser().getID(), "ID", null).fetch();
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
        } catch (SQLException e) {
            log.catching(e);
            log.error("Es ist ein Fehler aufgetreten");         // TODO was ist der fehler??
            log.info("");                                       // TODO provide additional information
        }
    }
    /*
    public static void main(String[] args) {
        launch(args);
    }
    */
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
