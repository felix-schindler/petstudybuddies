package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.ToDoList;

import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.InsertQuery;
        import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
        import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import javafx.stage.Stage;
        import org.apache.logging.log4j.LogManager;
        import org.apache.logging.log4j.Logger;

        import java.net.URL;
        import java.util.ResourceBundle;

public class ToDoListControllerViewList extends Controller implements Initializable{

    private static final Logger log = LogManager.getLogger(ToDoListControllerViewList.class);
    @FXML
    Button ButtonSetFlag, ButtonShareList,ButtonChangeTitle,ButtonAddNewTask,ButtonModifyTask;
    @FXML
    TableView TableViewList;
    @FXML
    Stage secondStage;
    @FXML
    TableColumn colContent,colUntil,colAssignedTo;
    @FXML
    TableView TableViewSelectedList;
    @FXML
    Label LabelToDoListName;
    @FXML
    Stage anotherStage = new Stage();

    @FXML
    public void setTableViewList(ActionEvent actionViewList) {
        Node node = (Node) actionViewList.getSource();
        Stage stage = (Stage)node.getScene().getWindow();

        //this.selectedList = ToDoList.;
        TableViewList.setItems(selectedList);
        colContent.setCellValueFactory(new PropertyValueFactory<>("Content"));
        colUntil.setCellValueFactory(new PropertyValueFactory<>("Until"));
        colAssignedTo.setCellValueFactory(new PropertyValueFactory<>("Assigned To"));
        log.debug("Table View Data set");
    }
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        Stage stage = null;
        Parent root = null;

        if(event.getSource()==ButtonSetFlag) {
            log.debug("ButtonSetFlag was clicked");
        }else if(event.getSource()==ButtonChangeTitle){
                log.debug("ButtonChangeTitle was clicked");
                openSecondScene("/fxml/ToDoList/ToDoListModifyTitle.fxml");
        }else if(event.getSource()==ButtonAddNewTask){
            log.debug("ButtonAddNewTask was clicked");
            openSecondScene("/fxml/ToDoList/ToDoListAddTask.fxml");
        }else if(event.getSource()==ButtonModifyTask){
            log.debug("ButtonModifyTask was clicked");
            openSecondScene("/fxml/ToDoList/ToDoListModifyTask.fxml");
        }else if(event.getSource()==ButtonShareList){
            log.debug("ButtonShareList was clicked");
            openSecondScene("/fxml/ToDoList/ToDoListShare.fxml");
        }

        if(root!=null && stage != null) {
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void openSecondScene(String filepath) {
        try {
        FXMLLoader firstPaneLoader = new FXMLLoader(getClass().getResource("/fxml/ToDoList/ToDoListView2.fxml"));
        Parent firstPane = firstPaneLoader.load();
        FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource(filepath)) ;

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
        Stage secondStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        secondStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        log.debug("New Controller loaded");
        if(this.selectedList!=null){
            TableViewSelectedList.setItems(this.selectedList);
            //colContent.setCellValueFactory(new SimpleStringProperty(selectedList.get(1).toString()).getBean());
            colContent.setCellValueFactory(new PropertyValueFactory<ToDoList,String>("Content"));
            colUntil.setCellValueFactory(new PropertyValueFactory<ToDoList,Integer>("Until"));
            colAssignedTo.setCellValueFactory(new PropertyValueFactory<ToDoList,Integer>("Assigned To"));

            log.debug("TableView set");
        }else log.debug("List was null");


    }
}