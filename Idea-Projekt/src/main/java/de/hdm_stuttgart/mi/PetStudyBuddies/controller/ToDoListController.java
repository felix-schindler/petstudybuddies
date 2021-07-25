package de.hdm_stuttgart.mi.PetStudyBuddies.controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.ToDoList;
import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ToDoListController extends Controller implements Initializable {
    /**
     * Log object for error handling
     */
    private static final Logger log = LogManager.getLogger(ToDoListController.class);
    protected static ObservableList<ToDoList> selectedList = FXCollections.observableArrayList();
    protected static int selectedListID;
    protected static ToDoList selectedListAsObject;
    @FXML
    Label LabelUsername;
    @FXML
    Label LabelCountToDoToday, LabelCountToDoScheduled, LabelCountToDoFlagged, LabelCountToDoAll;
    @FXML
    Button ButtonToDoToday, ButtonToDoScheduled, ButtonToDoFlagged, ButtonToDoAll, ButtonAddNewList, ButtonViewList;
    @FXML
    TableView<ToDoList> TableViewTest;
    @FXML
    TableColumn<Object, Object> colTitle;
    int NToday, NScheduled, NFlagged, NAll;
    CachedRowSet AllUserLists = new SelectQuery("ToDoList", "ID", "UserID = " + Account.getLoggedUser().getID(), "ID", null).fetchAll();
    CachedRowSet TodayUserLists = new SelectQuery("ToDoList, Task", "DISTINCT(ToDoList.ID), ToDoList.UserID, ToDoList.Title", "UserID = " + Account.getLoggedUser().getID() + " AND Task.Until = CURRENT_DATE").fetchAll();
    CachedRowSet ScheduledUserLists = new SelectQuery("ToDoList, Task", "DISTINCT(ToDoList.ID), ToDoList.UserID, ToDoList.Title", "UserID = " + Account.getLoggedUser().getID() + " AND date(datetime(Task.Until / 1000 , 'unixepoch')) IS NOT NULL").fetchAll();
    CachedRowSet FlaggedUserLists = new SelectQuery("ToDoList", "*", "UserID = " + Account.getLoggedUser().getID() + " AND Flagged = '1'").fetchAll();

    public static ObservableList<ToDoList> getSelectedList() {
        return selectedList;
    }

    public static void setSelectedList(ObservableList<ToDoList> selectedList) {
        ToDoListController.selectedList = selectedList;
        setSelectedListAsObject();
    }

    public static void setSelectedListAsObject() {
        selectedListAsObject = selectedList.get(0);
        log.debug("selectedListAsObject getID:" + selectedListAsObject.getID());

    }

    public static int getSelectedListID() {
        return selectedListID;
    }

    public static void setSelectedListID(int ID) {
        ToDoListController.selectedListID = ID;
    }

    public static void updateSelectedList() {
        CachedRowSet updatedList = new SelectQuery("ToDoList", "*", "ID=" + ToDoListController.getSelectedListID(), null, null, true).fetchAll();
        ObservableList<ToDoList> selectedList = FXCollections.observableArrayList();
        try {
            if (!updatedList.first()) {
                log.debug("Updated List is empty");
                selectedList.clear();

            } else {
                updatedList.first();
                do {
                    selectedList.add(new ToDoList(updatedList.getInt("ID")));
                    log.debug("ToDo List " + updatedList.getInt("ID") + " added");
                } while (updatedList.next());
            }
            log.debug("Updated List is set");
        } catch (SQLException e) {
            log.catching(e);
            log.error("Failed to update selected List");
        }
        log.debug("Selected List Size " + selectedList.size());
        setSelectedList(selectedList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelUsername.setText(Account.getLoggedUser().getUsername());
        ObservableList<ToDoList> data = FXCollections.observableArrayList();
        //updateUserToDoLists();
        try {
            do {
                data.add(new ToDoList(AllUserLists.getInt("ID")));
                log.debug("ToDo List " + AllUserLists.getInt("ID") + " added");
            } while (AllUserLists.next());

            TableViewTest.setItems(data);
            colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
            log.debug("Table View Data set");

            this.NAll = AllUserLists.size();
            log.debug("Number of All To Do Lists " + NAll);
            LabelCountToDoAll.setText(String.valueOf(NAll));

            this.NFlagged = FlaggedUserLists.size();
            log.debug("Number of Flagged To Do Lists " + NFlagged);
            LabelCountToDoFlagged.setText(String.valueOf(NFlagged));

            this.NScheduled = ScheduledUserLists.size();
            log.debug("Number of To Do Lists " + NScheduled);
            LabelCountToDoScheduled.setText(String.valueOf(NScheduled));

            this.NToday = TodayUserLists.size();
            log.debug("Number of To Do Lists " + NToday);
            LabelCountToDoToday.setText(String.valueOf(NToday));

        } catch (SQLException e) {
            log.catching(e);
            log.error("Beim Hinzufügen von ToDoLists zum TableView  ist ein Fehler aufgetreten");
        }
    }


    @FXML
    public void setTableViewTest(ResultSet QueryResult) {
        ObservableList<ToDoList> data = FXCollections.observableArrayList();
        try {
            if (!QueryResult.first()) {
                log.debug("Data is empty");
                data.clear();
                TableViewTest.setItems(data);
            } else {
                QueryResult.first();
                do {
                    data.add(new ToDoList(QueryResult.getInt("ID")));
                    log.debug("ToDo List " + QueryResult.getInt("ID") + " added");
                } while (QueryResult.next());

                TableViewTest.setItems(data);
                colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
            }
            log.debug("Table view data set");
        } catch (SQLException e) {
            log.catching(e);
            log.error("Failed to set table view data");
        }

    }

    @FXML
    void filterButtons(ActionEvent actionEvent) {
        log.debug("Button Event Handler called");
        if (actionEvent.getSource() == ButtonToDoAll) {
            setTableViewTest(AllUserLists);
        } else if (actionEvent.getSource() == ButtonToDoScheduled) {
            setTableViewTest(ScheduledUserLists);
        } else if (actionEvent.getSource() == ButtonToDoFlagged) {
            setTableViewTest(FlaggedUserLists);
        } else if (actionEvent.getSource() == ButtonToDoToday) {
            setTableViewTest(TodayUserLists);
        } else if (actionEvent.getSource() == ButtonViewList) {
            log.debug("ButtonViewList was clicked on");
            ObservableList<ToDoList> selectedList = TableViewTest.getSelectionModel().getSelectedItems();
            log.debug("Observable List with selected Items was created");
            if (!selectedList.isEmpty()) {
                log.debug("Items were selected");
                ToDoListController.setSelectedList(selectedList);
                if (ToDoListController.selectedList.isEmpty()) {
                    log.debug("List is empty");
                }
                PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListViewList2.fxml");
            } else {
                // TODO maybe display error message / dialog(?)
                log.debug("Nothing selected");
            }
        } else if (actionEvent.getSource() == ButtonAddNewList) {
            Stage anotherStage = new Stage();
            log.debug("ButtonAddList was clicked");
            try {
                // Load dialog for input
                FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource("/fxml/ToDoList/ToDoListAddList.fxml"));
                Parent secondPane = secondPageLoader.load();
                Scene secondScene = new Scene(secondPane);
                anotherStage.setScene(secondScene);
                anotherStage.show();
            } catch (Exception e) {
                log.catching(e);
                log.error("Failed to load input dialog");
            }
        }
    }

    public void updateUserToDoLists() {
        AllUserLists = new SelectQuery("ToDoList", "ID", "UserID = " + Account.getLoggedUser().getID(), "ID", null).fetchAll();
        TodayUserLists = new SelectQuery("ToDoList, Task", "*", "UserID = " + Account.getLoggedUser().getID() + " AND date(datetime(Task.Until / 1000 , 'unixepoch')) = date('now')").fetchAll();
        ScheduledUserLists = new SelectQuery("ToDoList, Task", "DISTINCT(ToDoList.ID), ToDoList.UserID, ToDoList.Title", "UserID = " + Account.getLoggedUser().getID() + " AND date(datetime(Task.Until / 1000 , 'unixepoch')) IS NOT NULL").fetchAll();
        FlaggedUserLists = new SelectQuery("ToDoList", "*", "UserID = " + Account.getLoggedUser().getID() + " AND Flagged = '1'").fetchAll();

    }
}
