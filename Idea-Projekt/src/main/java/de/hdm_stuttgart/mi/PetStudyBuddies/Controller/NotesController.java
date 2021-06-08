package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Controller;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.Note;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Sample Skeleton for 'Untitled' Controller Class
 */
public class NotesController extends Controller {
    private final static Logger log = LogManager.getLogger(NotesController.class);

    @FXML
    public TableColumn<Note, Integer> colID;
    @FXML
    public TableColumn<Note, String> colTitle;
    @FXML
    public TableColumn<Note, String> colContent;
    @FXML
    public TableColumn<Note, Date> colLastEdited;
    @FXML
    public TableColumn<Note, Date> colCreated;
    @FXML
    private TableView<Note> tableview;
    @FXML
    private TextField searchField;

    public ObservableList<Note> getNotes() {
        ObservableList<Note> notes = FXCollections.observableArrayList();

        try {
            CachedRowSet notesSet = new SelectQuery("Note", "ID", "UserID="+ Account.getLoggedUser().getID()).fetchAll();
            while (notesSet.next()) {
                notes.add(new Note(notesSet.getInt("ID")));
                log.debug("Note " + notesSet.getInt("ID") + " added");
            }
        } catch (SQLException e) {
            log.catching(e);
            log.error("Failed to load notes");
        }
        return notes;
    }

    @FXML
    public void search(KeyEvent event) {
        final String search = Utils.getInputString(searchField);
    }

    /**
     * @param location  URL location of the FXML file that was given to the FXMLLoader
     * @param resources ResourceBundle that was given to the FXMLLoader
     */
    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL location, ResourceBundle resources) {
        tableview.setItems(getNotes());

        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colContent.setCellValueFactory(new PropertyValueFactory<>("Content"));
        colLastEdited.setCellValueFactory(new PropertyValueFactory<>("LastEditedOn"));
        colCreated.setCellValueFactory(new PropertyValueFactory<>("CreatedOn"));
        tableview.setEditable(true);
        tableview.getSelectionModel().setCellSelectionEnabled(true);
    }
}
