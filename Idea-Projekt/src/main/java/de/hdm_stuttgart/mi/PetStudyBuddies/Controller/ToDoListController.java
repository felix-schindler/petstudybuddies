package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.Note;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.ToDoList;
import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ToDoListController implements Initializable {
    private static final Logger log = LogManager.getLogger(ToDoListController.class);
    @FXML
    Button ApplicationDashboard = new Button();
    @FXML
    Button NotesDashboard = new Button();
    @FXML
    Button StudiesDashboard = new Button();
    @FXML
    Button ToDoDashboard = new Button();
    @FXML
    Label LabelUsername;
    @FXML
    Label LabelCountToDoToday, LabelCountToDoScheduled, LabelCountToDoFlagged, LabelCountToDoAll;
    @FXML
    Button ListsToday, ListsScheduled, ListsFlagged, ListsAll;
    @FXML
    ScrollPane ScrollPaneAllLists;
    @FXML
    TableView TableViewTest;
    @FXML
    TableColumn colTitle;
    Scene SceneToDoDashboard, SceneToDoViewList;
    int NToday, NScheduled, NFlagged, NAll;
    private Stage Window;

    @FXML
    public void navigateApplicationDashboard(ActionEvent event) {
        // TODO Rufe Scene des Start Dashboards auf
    }

    @FXML
    public void navigateNotesDashboard(ActionEvent event) {
        // TODO Rufe Scene des Start Dashboards auf
    }

    @FXML
    public void navigateStudiesDashboard(ActionEvent event) {
        ToDoDashboard.setOnAction(actionEvent -> Window.setScene(SceneToDoDashboard));
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;

        log.debug("handleButtonAction called");
        if (event.getSource() == ApplicationDashboard) {
            // PetStudyBuddies.setStage("/fxml/hello.fxml");
            stage = (Stage) ApplicationDashboard.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/fxml/hello.fxml"));
            log.debug("ApplicationDashboard loaded");
        } else if (event.getSource() == NotesDashboard) {
            stage = (Stage) NotesDashboard.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/fxml/Notes.fxml"));
            log.debug("NotesDashboard loaded");
        } else if (event.getSource() == StudiesDashboard) {
            stage = (Stage) StudiesDashboard.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/fxml/hello.fxml"));
            log.debug("StudiesDashboard loaded");
        } else if (event.getSource() == ToDoDashboard) {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelUsername.setText(Account.getLoggedUser().getUsername());

        try {
            this.NAll = new SelectQuery("ToDoList", "*", "UserID = " + Account.getLoggedUser().getID()).Count();
            log.debug("Number of All To Do Lists " + NAll);
            LabelCountToDoAll.setText(String.valueOf(NAll));

            this.NFlagged = new SelectQuery("ToDoList", "*", "UserID = " + Account.getLoggedUser().getID() + " AND Flagged = '1'").Count();
            log.debug("Number of Flagged To Do Lists " + NFlagged);
            LabelCountToDoFlagged.setText(String.valueOf(NFlagged));

            this.NScheduled = new SelectQuery("ToDoList, Task", "*", "UserID = " + Account.getLoggedUser().getID() + " AND date(datetime(Task.Until / 1000 , 'unixepoch')) IS NOT NULL").Count();
            log.debug("Number of To Do Lists " + NScheduled);
            LabelCountToDoScheduled.setText(String.valueOf(NScheduled));

            this.NToday = new SelectQuery("ToDoList, Task", "*", "UserID = " + Account.getLoggedUser().getID() + " AND date(datetime(Task.Until / 1000 , 'unixepoch')) = date('now')").Count();
            log.debug("Number of To Do Lists " + NToday);
            LabelCountToDoToday.setText(String.valueOf(NToday));

            ObservableList<ToDoList> data = FXCollections.observableArrayList();
            CachedRowSet AllUserLists = new SelectQuery("ToDoList", "ID", "UserID = " + Account.getLoggedUser().getID(), "ID", null).fetchAll();
            while (AllUserLists.next()) {
                data.add(new ToDoList(AllUserLists.getInt("ID")));
                log.debug("ToDo List " + AllUserLists.getInt("ID") + " added");
            }

            TableViewTest.setItems(data);
            colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
            log.debug("Table View Data set");
        } catch (SQLException e) {
            log.catching(e);
            log.error("Beim Laden der ToDo Lists ist ein Fehler aufgetreten");
        }
    }
}
