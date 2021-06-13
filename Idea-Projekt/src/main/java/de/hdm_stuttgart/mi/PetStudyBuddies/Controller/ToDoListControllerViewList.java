package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.ToDoList;

import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
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

public class ToDoListControllerViewList implements Initializable{

    private static final Logger log = LogManager.getLogger(ToDoListControllerViewList.class);
    @FXML
    Button ButtonCreateList,ButtonBack;
    @FXML
    TableView TableViewList;
    @FXML
    Stage secondStage;
    @FXML
    Label LabelValidInput;
    @FXML
    ObservableList<ToDoList> selectedList;
    @FXML
    TableColumn colContent,colUntil,colAssignedTo;

    @FXML
    public void setTableViewList(ActionEvent actionViewList) {
        Node node = (Node) actionViewList.getSource();
        Stage stage = (Stage)node.getScene().getWindow();

        this.selectedList = ToDoList.;
        TableViewList.setItems(selectedList);
        colContent.setCellValueFactory(new PropertyValueFactory<>("Content"));
        colUntil.setCellValueFactory(new PropertyValueFactory<>("Until"));
        colAssignedTo.setCellValueFactory(new PropertyValueFactory<>("Assigned To"));
        log.debug("Table View Data set");
    }
    @FXML
    public void closeSecondScene(ActionEvent actionEvent) {
        Stage secondStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        secondStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedList
    }
}