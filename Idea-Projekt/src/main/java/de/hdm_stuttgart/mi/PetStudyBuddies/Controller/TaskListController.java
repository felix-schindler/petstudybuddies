package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.Note;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.Task;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.ToDoList;
import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import de.hdm_stuttgart.mi.PetStudyBuddies.Views.Dialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

public class TaskListController extends Controller implements Initializable {
    private static final Logger log = LogManager.getLogger(TaskListController.class);
    @FXML
    Button ButtonSetFlag, ButtonShareList, ButtonChangeTitle, ButtonAddNewTask, ButtonModifyTask, ButtonAssignTask;
    @FXML
    TableColumn<Object, Object> colContent, colUntil, colAssignedTo;
    @FXML
    TableView<Task> TableViewSelectedList;
    @FXML
    Label LabelToDoListName;
    Stage anotherStage = new Stage();
    ToDoList ToDoListSelected;
    protected static int selectedListId;
    protected static ObservableList<Task> selectedTask;

    public void setSelectedTask(ObservableList<Task> selectedTask){
        this.selectedTask = selectedTask;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == ButtonSetFlag) {
            log.debug("ButtonSetFlag was clicked");
            ToDoListSelected.setFlagged(!ToDoListSelected.getFlagged());
            ToDoListSelected.save();
        } else if (event.getSource() == ButtonChangeTitle) {
            log.debug("ButtonChangeTitle was clicked");
            openSecondScene("/fxml/ToDoList/ToDoListModifyTitle.fxml");
        } else if (event.getSource() == ButtonAddNewTask) {
            log.debug("ButtonAddNewTask was clicked");
            openSecondScene("/fxml/ToDoList/ToDoListAddTask.fxml");
        } else if (event.getSource() == ButtonModifyTask) {
            log.debug("ButtonModifyTask was clicked");
            setSelectedTask();
            if (selectedTask!=null) {
                openSecondScene("/fxml/ToDoList/ToDoListModifyTask.fxml");
            }
        } else if (event.getSource() == ButtonShareList) {
            log.debug("ButtonShareList was clicked");
            openSecondScene("/fxml/ToDoList/ToDoListShare.fxml");
        }else if (event.getSource()==ButtonAssignTask){
            ObservableList<Task> selectedTask = TableViewSelectedList.getSelectionModel().getSelectedItems();
            log.debug("Observable List with selected Items was created");
            if (!selectedTask.isEmpty()) {
                log.debug("Items were selected");
                setSelectedTask(selectedTask);
                if (TaskListController.selectedTask.isEmpty()) {
                    log.debug("List is empty");
                }

            } else {
                // TODO maybe display error message / dialog(?)
                log.debug("Nothing selected");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();
    }

    public void openSecondScene(String filepath) {
        try {
            FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource(filepath));
            Parent secondPane = secondPageLoader.load();
            Scene secondScene = new Scene(secondPane);

            anotherStage.setScene(secondScene);
            anotherStage.show();
        } catch (Exception e) {
            log.catching(e);
            log.error("Failed to show dialog");
        }
    }

    public void closeSecondScene(ActionEvent actionEvent) {
        Stage secondStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        secondStage.close();
    }

    public void updateTableView(){
        ToDoListController.updateSelectedList();
        setTableView();
    }

    public void setTableView() {
        ObservableList<ToDoList> selectedList = ToDoListController.getSelectedList();
        log.debug("Selected List Size " + selectedList.size());
        if (ToDoListController.getSelectedList() != null) {
            for (ToDoList todolist : selectedList) {
                selectedListId = todolist.getID();
                ToDoListController.setSelectedListID(selectedListId);
                this.ToDoListSelected = todolist;
                LabelToDoListName.setText(ToDoListSelected.getTitle());
            }
            log.debug("ToDoList ID " + selectedListId);
            ObservableList<Task> tasks = FXCollections.observableArrayList();
            int numberOfTasks = new SelectQuery("Task", "*", "ToDoListID=" + selectedListId, "ID", null, true).Count();
            log.debug("Number of Tasks in List"+ numberOfTasks);
            if (numberOfTasks != 0) {
                CachedRowSet TasksInSelectedList = new SelectQuery("Task", "ID", "ToDoListID=" + selectedListId, "ID", null, true).fetchAll();
                try {
                    do {
                        tasks.add(new Task(TasksInSelectedList.getInt("ID")));
                        log.debug("Observable List Size " + tasks.size());
                    } while (TasksInSelectedList.next());
                } catch (SQLException e) {
                    log.debug("Could not resolve Tasks from CachedRowSet");
                }
            } else {
                tasks.clear();
            }

            TableViewSelectedList.setItems(tasks);
            colContent.setCellValueFactory(new PropertyValueFactory<>("Content"));
            colUntil.setCellValueFactory(new PropertyValueFactory<>("Until"));
            colAssignedTo.setCellValueFactory(new PropertyValueFactory<>("AssignedTo"));

            log.debug("TableView set");
        }
    }
    @FXML
    public void setSelectedTask() {
        ObservableList<Task> selectedTask = TableViewSelectedList.getSelectionModel().getSelectedItems();
        if (!selectedTask.isEmpty()) {
            this.selectedTask = (ObservableList<Task>) selectedTask.get(0);
        }else{
            log.error("No task was selected");
        }
    }

}
