package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.DeleteQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.ToDoList;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ToDoListController extends Controller implements Initializable {
    /**
     * Log object for error handling
     */
    private static final Logger log = LogManager.getLogger(ToDoListController.class);
    /**
     * Selected ToDoList for viewing tasks, ...
     */
    private static ToDoList editTodo = null;
    @FXML
    Label LabelUsername;
    @FXML
    Label LabelCountToDoToday, LabelCountToDoScheduled, LabelCountToDoFlagged, LabelCountToDoAll;
    @FXML
    Button ButtonToDoToday, ButtonToDoScheduled, ButtonToDoFlagged, ButtonToDoAll, ButtonAddNewList, ButtonViewList, ButtonDeleteList;
    @FXML
    TableView<ToDoList> TodoTable;
    @FXML
    TableColumn<Object, Object> colTitle;

    SelectQuery queryToDoLists = new SelectQuery("ToDoList", "ID", "UserID = " + Account.getLoggedUser().getID(), "ID", null);
    SelectQuery querySharedToDoLists = new SelectQuery("ToDoListShare", "ToDoListID", "UserID=" + Account.getLoggedUser().getID());
    SelectQuery queryTodayUserLists = new SelectQuery("ToDoList, Task", "DISTINCT(ToDoList.ID), ToDoList.UserID, ToDoList.Title", "UserID = " + Account.getLoggedUser().getID() + " AND Task.Until = CURRENT_DATE");
    SelectQuery queryScheduledUserLists = new SelectQuery("ToDoList, Task", "DISTINCT(ToDoList.ID), ToDoList.UserID, ToDoList.Title", "ToDoList.UserID = " + Account.getLoggedUser().getID() + " AND date(datetime(Task.Until / 1000 , 'unixepoch')) AND Task.ToDoListID=ToDoList.ID");
    SelectQuery queryFlaggedUserLists = new SelectQuery("ToDoList", "*", "UserID = " + Account.getLoggedUser().getID() + " AND Flagged = '1'");

    public ObservableList<ToDoList> getUserTodoLists() {
        ObservableList<ToDoList> todosList = FXCollections.observableArrayList();
        CachedRowSet todosSet = queryToDoLists.fetchAll();
        CachedRowSet sharedTodosSet = querySharedToDoLists.fetchAll();
        Utils.printResultSet(todosSet);
        try {
            if (queryToDoLists.Count() > 0) {
                do {
                    todosList.add(new ToDoList(todosSet.getInt("ID")));
                } while (todosSet.next());
            }

            if (querySharedToDoLists.Count() > 0) {
                do {
                    todosList.add(new ToDoList(sharedTodosSet.getInt("ToDoListID")));
                } while (sharedTodosSet.next());
            }
        } catch (SQLException | ArrayIndexOutOfBoundsException e) {
            log.catching(e);
            log.error("Failed to add ToDoLists");
        }

        return todosList;
    }

    Runnable updateUserToDoLists = () -> {
        log.debug("Updating ToDoList table...");
        TodoTable.setItems(getUserTodoLists());
        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelUsername.setText(Account.getLoggedUser().getUsername());

        new Thread(updateUserToDoLists).start();

        LabelCountToDoAll.setText(String.valueOf(queryToDoLists.Count()));
        LabelCountToDoFlagged.setText(String.valueOf(queryFlaggedUserLists.Count()));
        LabelCountToDoScheduled.setText(String.valueOf(queryScheduledUserLists.Count()));
        LabelCountToDoToday.setText(String.valueOf(queryTodayUserLists.Count()));
    }

    /**
     * @return The currently selected ToDoList as Object
     */
    private ToDoList getSelectedTodo() {
        ObservableList<ToDoList> selectedList = TodoTable.getSelectionModel().getSelectedItems();
        log.debug("Observable List with selected Items was created");
        if (!selectedList.isEmpty()) {
            editTodo = selectedList.get(0);
            return editTodo;
        }
        return null;
    }

    /**
     * @return The ToDoList to be edited (add tasks, ...)
     */
    public static ToDoList getEditTodo() {
        return editTodo;
    }

    @FXML
    public void setTableData(CachedRowSet queryResult) {
        ObservableList<ToDoList> data = FXCollections.observableArrayList();

        try {
            if (!queryResult.first()) {
                Dialog.showError("List is empty", "The chosen list is empty, please choose another one.");
                return;
            }
        } catch (SQLException e) {
            log.catching(e);
            Dialog.showError("An unknown error occurred, please try again.");
            return;
        }

        try {
            do {
                data.add(new ToDoList(queryResult.getInt("ID")));
                log.debug("ToDo List " + queryResult.getInt("ID") + " added");
            } while (queryResult.next());

            TodoTable.setItems(data);
            colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
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
            setTableData(queryToDoLists.fetchAll());
        } else if (actionEvent.getSource() == ButtonToDoScheduled) {
            setTableData(queryScheduledUserLists.fetchAll());
        } else if (actionEvent.getSource() == ButtonToDoFlagged) {
            setTableData(queryFlaggedUserLists.fetchAll());
        } else if (actionEvent.getSource() == ButtonToDoToday) {
            setTableData(queryTodayUserLists.fetchAll());
        } else if (actionEvent.getSource() == ButtonViewList) {
            editTodo = getSelectedTodo();
            if (editTodo == null) {
                log.error("No list selected");
                Dialog.showError("Please select a ToDo List.");
            } else PetStudyBuddies.setStage("/fxml/ToDoList/TaskList.fxml");
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
        } else if (actionEvent.getSource() == ButtonDeleteList) {
            ToDoList selected = getSelectedTodo();
            if (selected != null) {
                DeleteQuery qTodo = new DeleteQuery("ToDoList", "ID=" + selected.getID());
                if (qTodo.Count() <= 0) {
                    log.error("Failed to delete ToDoList");
                    Dialog.showError("Failed to delete selected ToDoList, please try again.");
                } else PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListDashboard2.fxml");      // TODO reloading in a new thread doesn't work
            } else {
                log.error("Nothing selected");
                Dialog.showError("Please select a list.");
            }
        }
    }
}
