/**
 * Sample Skeleton for 'Untitled' Controller Class
 */

package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
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

public class NotesController implements Initializable {
    private final static Logger log = LogManager.getLogger(NotesController.class);

    public TableColumn<Note, String> colTitle;
    public TableColumn<Note, Double> colContent;
    public TableColumn<Note, Integer> colCreated;
    @FXML private TableView<Note> tableview;

    @FXML private TextField searchField;

    ObservableList<Note> observableList = getNotes();

    public ObservableList<Note> getNotes() {
        ObservableList<Note> notes = FXCollections.observableArrayList();

        try {
            CachedRowSet notesSet = new SelectQuery("Note", "ID", "1").fetchAll();
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
     * @param location URL location of the FXML file that was given to the FXMLLoader
     * @param resources ResourceBundle that was given to the FXMLLoader
     */
    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Note> notes = getNotes();

        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colContent.setCellValueFactory(new PropertyValueFactory<>("Content"));
        tableview.setEditable(true);
        tableview.getSelectionModel().setCellSelectionEnabled(true);
    }
}
