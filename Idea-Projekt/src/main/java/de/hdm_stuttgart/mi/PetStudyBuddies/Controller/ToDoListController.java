package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Controller;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.ToDoList;
import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ToDoListController extends Controller {
    /**
     * Log object for error handling
     */
    private static final Logger log = LogManager.getLogger(ToDoListController.class);

    @FXML
    Label LabelUsername;
    @FXML
    Label LabelCountToDoToday, LabelCountToDoScheduled, LabelCountToDoFlagged, LabelCountToDoAll;
    @FXML
    Button ButtonToDoToday, ButtonToDoScheduled, ButtonToDoFlagged, ButtonToDoAll;
    @FXML
    ScrollPane ScrollPaneAllLists;
    @FXML
    TableView<ToDoList> TableViewTest;
    @FXML
    TableColumn<Object, Object> colTitle;
    ObservableList<ToDoList> data = FXCollections.observableArrayList();
    @FXML
    CachedRowSet AllUserLists = new SelectQuery("ToDoList", "ID", "UserID = " + Account.getLoggedUser().getID(), "ID", null).fetchAll();
    @FXML
    CachedRowSet TodayUserLists = new SelectQuery("ToDoList, Task", "*", "UserID = " + Account.getLoggedUser().getID() + " AND date(datetime(Task.Until / 1000 , 'unixepoch')) = date('now')").fetchAll();
    @FXML
    CachedRowSet ScheduledUserLists = new SelectQuery("ToDoList, Task", "*", "UserID = " + Account.getLoggedUser().getID() + " AND date(datetime(Task.Until / 1000 , 'unixepoch')) IS NOT NULL").fetchAll();
    @FXML
    CachedRowSet FlaggedUserLists = new SelectQuery("ToDoList", "*", "UserID = " + Account.getLoggedUser().getID() + " AND Flagged = '1'").fetchAll();

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
        ToDoDashboard.setOnAction(actionEvent -> PetStudyBuddies.setStage("/fxml/ToDoListDashboard.fxml"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelUsername.setText(Account.getLoggedUser().getUsername());

        try {
            int NToday, NScheduled, NFlagged, NAll;

            do {
                data.add(new ToDoList(AllUserLists.getInt("ID")));
                log.debug("ToDo List " + AllUserLists.getInt("ID") + " added");
            } while (AllUserLists.next());

            TableViewTest.setItems(data);
            colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
            log.debug("Table View Data set");

            NAll=AllUserLists.size();
            log.debug("Number of All To Do Lists " + NAll);
            LabelCountToDoAll.setText(String.valueOf(NAll));

            NFlagged = FlaggedUserLists.size();
            log.debug("Number of Flagged To Do Lists " + NFlagged);
            LabelCountToDoFlagged.setText(String.valueOf(NFlagged));

            NScheduled = ScheduledUserLists.size();
            log.debug("Number of To Do Lists " + NScheduled);
            LabelCountToDoScheduled.setText(String.valueOf(NScheduled));

            NToday = TodayUserLists.size();
            log.debug("Number of To Do Lists " + NToday);
            LabelCountToDoToday.setText(String.valueOf(NToday));

            ButtonToDoAll.setOnAction(this::filterLists);
            ButtonToDoFlagged.setOnAction(this::filterLists);
        } catch (SQLException e) {
            log.catching(e);
            log.error("Beim Laden der ToDo Lists ist ein Fehler aufgetreten");
        }
    }

    @FXML
    public void filterLists(ActionEvent event){
        log.debug("Filtering Lists");
        ObservableList<ToDoList> filteredData = FXCollections.observableArrayList();
        if (event.getSource() == ButtonToDoAll) {
            filteredData= data;
            log.debug("Button Filtering All Lists was clicked");
            log.debug("Filtered List Size"+ filteredData.size());
        } else if (event.getSource() == ButtonToDoFlagged) {
                for (ToDoList l:data) {
                    if (l.getFlagged()) {
                        filteredData.add(l);
                    }
                }
            log.debug("Button Filtering Flagged Lists was clicked");
            log.debug("Filtered List Size"+ filteredData.size());
        }
        TableViewTest.setItems(filteredData);
        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
    }

    // TODO
    @FXML void setTableViewTest(ObservableList<ToDoList> data){

    }
}
