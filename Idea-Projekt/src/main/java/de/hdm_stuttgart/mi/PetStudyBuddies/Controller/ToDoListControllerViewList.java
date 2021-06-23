package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.Task;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.ToDoList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ToDoListControllerViewList extends Controller implements Initializable {

    private static final Logger log = LogManager.getLogger(ToDoListControllerViewList.class);
    @FXML
    Button ButtonSetFlag, ButtonShareList, ButtonChangeTitle, ButtonAddNewTask, ButtonModifyTask, ButtonCreateModifiedTask, ButtonBackModifyTask;
    @FXML
    TableView TableViewList;
    @FXML
    Stage secondStage;
    @FXML
    TableColumn colContent, colUntil, colAssignedTo;
    @FXML
    TableView TableViewSelectedList;
    @FXML
    Label LabelToDoListName;
    @FXML
    Stage anotherStage = new Stage();
    ToDoList ToDoListSelected;
    @FXML
    TextField TextFieldModifyTask;
    @FXML
    DatePicker DatePickerModifyTask;

    @FXML
    public void setTableViewList(ActionEvent actionViewList) {
        Node node = (Node) actionViewList.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        //this.selectedList = ToDoList.;
        TableViewList.setItems(selectedList);
        colContent.setCellValueFactory(new PropertyValueFactory<>("Content"));
        colUntil.setCellValueFactory(new PropertyValueFactory<>("Until"));
        colAssignedTo.setCellValueFactory(new PropertyValueFactory<>("Assigned To"));
        log.debug("Table View Data set");
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
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
            openSecondScene("/fxml/ToDoList/ToDoListModifyTask.fxml");
        } else if (event.getSource() == ButtonShareList) {
            log.debug("ButtonShareList was clicked");
            openSecondScene("/fxml/ToDoList/ToDoListShare.fxml");
        }

    }

    @FXML
    public void openSecondScene(String filepath) {
        try {
            FXMLLoader firstPaneLoader = new FXMLLoader(getClass().getResource("/fxml/ToDoList/ToDoListView2.fxml"));
            Parent firstPane = firstPaneLoader.load();
            FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource(filepath));

            Parent secondPane = secondPageLoader.load();
            Scene secondScene = new Scene(secondPane);

            anotherStage.setScene(secondScene);
            anotherStage.show();
        } catch (Exception exc) {

            exc.printStackTrace();

        }
    }

    @FXML
    public void closeSecondScene(ActionEvent actionEvent) {
        Stage secondStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        secondStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        log.debug("New Controller loaded");
        ObservableList<ToDoList> selectedList = getSelectedList();
        log.debug("Selected List Size " + selectedList.size());
        if (getSelectedList() != null) {
            int ToDoListID = 0;
            for (ToDoList todolist : selectedList) {
                ToDoListID = todolist.getID();
                this.ToDoListSelected = todolist;
                LabelToDoListName.setText(ToDoListSelected.getTitle());
            }
            log.debug("ToDoList Id " + ToDoListID);
            ObservableList<Task> tasks = FXCollections.observableArrayList();
            CachedRowSet TasksInSelectedList = new SelectQuery("Task", "ID", "ToDoListID=" + ToDoListID, "ID", null, true).fetchAll();
            try {
                do {
                    tasks.add(new Task(TasksInSelectedList.getInt("ID")));
                    log.debug("Observable List Size " + tasks.size());
                } while (TasksInSelectedList.next());
            } catch (SQLException e) {
                log.debug("Could not resolve Tasks from CachedRowSet");
            }

            TableViewSelectedList.setItems(tasks);
            colContent.setCellValueFactory(new PropertyValueFactory<>("Content"));
            colUntil.setCellValueFactory(new PropertyValueFactory<>("Until"));
            colAssignedTo.setCellValueFactory(new PropertyValueFactory<>("AssignedTo"));

            log.debug("TableView set");
        } else log.debug("List was null");


    }
}