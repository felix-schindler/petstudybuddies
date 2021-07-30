package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.DeleteQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.Task;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.ToDoList;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 * Controller for Tasks Dashboard
 */
public class TaskListController extends Controller implements Initializable, ControlledScreen {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(TaskListController.class);
    /**
     * Task currently edited
     */
    protected static Task editTask;

    @FXML
    private Button ButtonSetFlag, ButtonShareList, ButtonChangeTitle, ButtonAddNewTask, ButtonModifyTask, ButtonAssignTask, ButtonDeleteTask;
    @FXML
    private TableColumn<Object, Object> colContent, colUntil, colAssignedTo;
    @FXML
    private TableView<Task> TaskTable;
    @FXML
    private Label LabelToDoListName;
    /**
     * Thread updating TableView
     */
    Runnable updateTable = () -> {
        LabelToDoListName.setText(ToDoListController.getEditTodo().getTitle());

        TaskTable.setItems(getTasks());
        colContent.setCellValueFactory(new PropertyValueFactory<>("Content"));
        colUntil.setCellValueFactory(new PropertyValueFactory<>("Until"));
        colAssignedTo.setCellValueFactory(new PropertyValueFactory<>("AssignedTo"));

        log.debug("TaskList table updated");
    };

    /**
     * Returns currently edited Task
     * @return edited Task
     */
    public static Task getEditTask() {
        return editTask;
    }
    /**
     * Sets parameters needed to initialize scene
     * @param url URL location of the FXML file that was given to the FXMLLoader
     * @param resourceBundle ResourceBundle that was given to the FXMLLoader
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread(updateTable).start();
        setButtonFlagged();
    }

    /**
     * @return List of all tasks for the selected ToDoList
     */
    private ObservableList<Task> getTasks() {
        ToDoList selectedList = ToDoListController.getEditTodo();
        ObservableList<Task> tasks = FXCollections.observableArrayList();

        if (selectedList != null) {
            SelectQuery qTasks = new SelectQuery("Task", "ID", "ToDoListID=" + selectedList.getID());
            int nTasks = qTasks.Count();
            if (nTasks > 0) {
                log.debug("Number of tasks in list " + qTasks.Count());
                CachedRowSet taskSet = qTasks.fetchAll();
                try {
                    do {
                        tasks.add(new Task(taskSet.getInt("ID")));
                    } while (taskSet.next());
                    log.debug("Observable list size " + tasks.size());
                } catch (SQLException e) {
                    log.debug("Could not resolve Tasks from CachedRowSet");
                }
            } else {
                log.debug("No tasks in this ToDoList.");
            }
        }
        return tasks;
    }

    /**
     * Handles all button actions (Right side menu)
     *
     * @param event Button click
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        // Flag parent ToDoList
        if (event.getSource() == ButtonSetFlag) {
            log.debug("ButtonSetFlag was clicked");
            ToDoList td = ToDoListController.getEditTodo();
            td.setFlagged();
            try {
                td.save();
                setButtonFlagged();
                if (td.getFlagged()) Dialog.showInfo("ToDoList was flagged");
                else Dialog.showInfo("Flag was removed from ToDoList");
                return;     // No reload
            } catch (Exception ignored) {
                Dialog.showError("The owner of a task can't be changed.");
            }
        }

        // Change ToDoList title (dialog)
        else if (event.getSource() == ButtonChangeTitle) {
            log.debug("ButtonChangeTitle was clicked");
            loadSecondScene(ToDoListModifyTitleID);
            return;     // No reload
        }

        // Add task (dialog)
        else if (event.getSource() == ButtonAddNewTask) {
            log.debug("ButtonAddNewTask was clicked");
            loadSecondScene(ToDoListAddTaskID);
        }

        // Modify Task (dialog)
        else if (event.getSource() == ButtonModifyTask) {
            log.debug("ButtonModifyTask was clicked");
            if (getSelectedTask() != null) {
                loadSecondScene(ToDoListModifyTaskID);
                ScreensController.setStage(TaskListID);
                return;     // Hard reload
            }
        }

        // Share Task (dialog)
        else if (event.getSource() == ButtonShareList) {
            log.debug("ButtonShareList was clicked");
            loadSecondScene(ToDoListShareListID);
        }

        // Assign Task (dialog)
        else if (event.getSource() == ButtonAssignTask) {
            log.debug("ButtonAssignTask was clicked");
            if (getSelectedTask() != null) {
                loadSecondScene(ToDoListAssignTaskID);
            }
        }

        // Delete Task
        else if (event.getSource() == ButtonDeleteTask) {
            // Update table after delete, display error if none selected
            if (getSelectedTask() != null) {
                DeleteQuery q = new DeleteQuery("Task", "ID=" + editTask.getID());
                if (q.Count() <= -1) {
                    Dialog.showError("Failed to delete selected Task, please try again.");
                }
            }
        }

        // Bc sth changed refresh the task table
        new Thread(updateTable).start();
    }

    /**
     * Sets Colour of Button when (not) flagged
     */
    public void setButtonFlagged() {
        if (ToDoListController.getEditTodo().getFlagged())
            ButtonSetFlag.setStyle("-fx-background-color: #8c78e3;");
        else
            ButtonSetFlag.setStyle("-fx-background-color: #bc8abb;");
    }

    /**
     * Returns Selected Task as Taskobject from Observable List
     * @return Task currently selected
     */
    public Task getSelectedTask() {
        ObservableList<Task> selectedTask = TaskTable.getSelectionModel().getSelectedItems();
        if (!selectedTask.isEmpty()) {
            editTask = selectedTask.get(0);
            return editTask;
        } else {
            log.error("No task was selected");
            Dialog.showError("No task selected", "Please select a Task from your To Do List");
        }
        return null;
    }
}
