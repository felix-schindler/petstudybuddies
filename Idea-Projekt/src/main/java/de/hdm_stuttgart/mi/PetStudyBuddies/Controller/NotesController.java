package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.DeleteQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.Note;
import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import de.hdm_stuttgart.mi.PetStudyBuddies.Views.Components.Dialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class NotesController extends Controller implements Initializable {
    private final static Logger log = LogManager.getLogger(NotesController.class);
    private static int editNote = -1;
    @FXML
    private TableColumn<Note, String> colTitle;
    @FXML
    private TableColumn<Note, String> colContent;
    @FXML
    private TableColumn<Note, Date> colLastEdited;
    @FXML
    private Label labelUsername;
    @FXML
    private TableView<Note> noteTable;

    public static int getEditNote() {
        return editNote;
    }

    public ObservableList<Note> getNotes() {
        ObservableList<Note> notes = FXCollections.observableArrayList();

        try {
            CachedRowSet notesSet = new SelectQuery("Note", "ID", "UserID=" + Account.getLoggedUser().getID(), "DATETIME(LastEditedOn)", null).fetchAll();
            do {
                notes.add(new Note(notesSet.getInt("ID")));
            } while (notesSet.next());

            CachedRowSet sharedNotesSet = new SelectQuery("NoteShare", "NoteID", "UserID=" + Account.getLoggedUser().getID(), "DATETIME(LastEditedOn)", null).fetchAll();
            do {
                notes.add(new Note(sharedNotesSet.getInt("NoteID")));
            } while (sharedNotesSet.next());
            log.debug("Added own and shared notes");
        } catch (SQLException e) {
            log.catching(e);
            log.error("Failed to load notes");
        }
        return notes;
    }

    /**
     * @param location  URL location of the FXML file that was given to the FXMLLoader
     * @param resources ResourceBundle that was given to the FXMLLoader
     */
    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL location, ResourceBundle resources) {
        // Delete empty notes
        new DeleteQuery("Note", "UserID=" + Account.getLoggedUser().getID() + " AND (Title IS NULL OR Title='null' OR Title='') AND (Content IS NULL OR Content='null' OR Content='')");

        noteTable.setItems(getNotes());

        labelUsername.setText(Account.getLoggedUser().getUsername());

        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colContent.setCellValueFactory(new PropertyValueFactory<>("Content"));
        colLastEdited.setCellValueFactory(new PropertyValueFactory<>("LastEditedOn"));
        noteTable.setEditable(true);
        noteTable.getSelectionModel().setCellSelectionEnabled(true);
    }

    @FXML
    public Note getSelectedNote() {
        ObservableList<Note> selectedNote = noteTable.getSelectionModel().getSelectedItems();
        if (!selectedNote.isEmpty()) {
            return selectedNote.get(0);
        }
        return null;
    }

    @FXML
    public void editNote() {
        if (getSelectedNote() != null) {
            editNote = getSelectedNote().getID();
            goToEditNote();
        } else {
            log.error("Note could not be selected");
            Dialog.showError("Note selection error", "Note could not be selected");
        }
    }

    @FXML
    public void createNewNote() {
        new InsertQuery("Note", new String[]{"UserID"}, new String[]{String.valueOf(Account.getLoggedUser().getID())});
        try {
            editNote = Integer.parseInt(new SelectQuery("Note", "ID", "UserID=" + Account.getLoggedUser().getID() + " AND Title IS NULL AND Content IS NULL").fetch());
        } catch (NumberFormatException e) {
            log.error("Failed to select and set new note");
        }

        goToEditNote();
    }

    @FXML
    public void share() {
        Note selectedNote = getSelectedNote();
        String username = Dialog.showInput("Input username to share to: ");

        if (selectedNote == null || username == null)
            return;

        try {
            if (selectedNote.share(Integer.parseInt(new SelectQuery("User", "ID", "Username='" + username + "'").fetch()))) {
                Dialog.showInfo("Success", "User added");
            }
        } catch (NumberFormatException e) {
            log.catching(e);
            log.error("User not found");
            Dialog.showError("Failed to add user", "User does not exists");
        }
    }

    public void goToEditNote() {
        PetStudyBuddies.setStage("/fxml/Notes/EditNote.fxml", "Edit note");
    }
}
