package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.Task;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.ToDoList;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
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
    protected static Task selectedTask;
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
    protected static ObservableList<Task> selectedTask = FXCollections.observableArrayList();
    protected static Task selectedTaskAsObject;

    public void setSelectedTask(ObservableList<Task> selectedTask){
        this.selectedTask = selectedTask;
        setSelectedListAsObject();
    }
    public void setSelectedTask(Task selectedTask){
        this.selectedTask.add(selectedTask);
        setSelectedListAsObject();
    }

    public static void setSelectedListAsObject(){
        selectedTaskAsObject= selectedTask.get(0);
        log.debug("selectedTaskAsObject getID:" + selectedTaskAsObject.getID());
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == ButtonSetFlag) {
            log.debug("ButtonSetFlag was clicked");
            ToDoListSelected.setFlagged(!ToDoListSelected.getFlagged());
            try {
                ToDoListSelected.save();
            } catch (Exception ignored) {
                Dialog.showError("Failed to save task, please try again.");
            }
            setButtonFlagged();
            Dialog.showInfo("Taskflag was changed");
        } else if (event.getSource() == ButtonChangeTitle) {
            log.debug("ButtonChangeTitle was clicked");
            openSecondScene("/fxml/ToDoList/ToDoListModifyTitle.fxml");
        } else if (event.getSource() == ButtonAddNewTask) {
            log.debug("ButtonAddNewTask was clicked");
            openSecondScene("/fxml/ToDoList/ToDoListAddTask.fxml");
        } else if (event.getSource() == ButtonModifyTask) {
            log.debug("ButtonModifyTask was clicked");
            if (setSelectedTask()) {
                openSecondScene("/fxml/ToDoList/ToDoListModifyTask.fxml");
            } else {
                Dialog.showInfo("Please select a Task from your To Do List");
            }
        } else if (event.getSource() == ButtonShareList) {
            log.debug("ButtonShareList was clicked");
            openSecondScene("/fxml/ToDoList/ToDoListShareToDoList.fxml");
        }else if (event.getSource()==ButtonAssignTask){
            log.debug("ButtonAssignTask was clicked");

            if (setSelectedTask()) {
                log.debug("Task was selected");
                openSecondScene("/fxml/ToDoList/ToDoListAssignTask.fxml");
            } else {
                // TODO maybe display error message / dialog(?)
                Dialog.showInfo("Please select a Task from your To Do List");
                log.debug("Selected Task was null");

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTableView();
        setButtonFlagged();
    }
    public void setButtonFlagged(){
        if(ToDoListSelected.getFlagged()){
            ButtonSetFlag.setStyle("-fx-background-color: #8c78e3; ");
        }else ButtonSetFlag.setStyle("-fx-background-color: #bc8abb;");
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

    public void updateTableView() {
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
            log.debug("Number of Tasks in List" + numberOfTasks);
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
    public boolean setSelectedTask() {
        ObservableList<Task> selectedTask = TableViewSelectedList.getSelectionModel().getSelectedItems();
        if (!selectedTask.isEmpty()) {
            this.selectedTaskAsObject = selectedTask.get(0);
            setSelectedTask(selectedTaskAsObject);
            return true;
        } else {
            log.error("No task was selected");
            return false;
        }
    }

}
