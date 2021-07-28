package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.DeleteQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.Note;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
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

public class NotesController extends Controller implements Initializable {
    /**
     * Logger object for smart logging
     */
    private final static Logger log = LogManager.getLogger(NotesController.class);
    /**
     * Note to be edited
     */
    private static Note editNote = null;
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

     Runnable updateView = () -> {
        log.debug("Updating view...");
        noteTable.setItems(getNotes());

        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colContent.setCellValueFactory(new PropertyValueFactory<>("Content"));
        colLastEdited.setCellValueFactory(new PropertyValueFactory<>("LastEditedOn"));
        noteTable.setEditable(true);
        noteTable.getSelectionModel().setCellSelectionEnabled(true);
    };

    /**
     * @param location  URL location of the FXML file that was given to the FXMLLoader
     * @param resources ResourceBundle that was given to the FXMLLoader
     */
    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL location, ResourceBundle resources) {
        // Delete empty notes
        new DeleteQuery("Note", "UserID=" + Account.getLoggedUser().getID() + " AND (Title IS NULL OR Title='null' OR Title='') AND (Content IS NULL OR Content='null' OR Content='')");
        labelUsername.setText(Account.getLoggedUser().getUsername());

        new Thread(updateView).start();
    }

    public ObservableList<Note> getNotes() {
        ObservableList<Note> notes = FXCollections.observableArrayList();
        try {
            CachedRowSet notesSet = new SelectQuery("Note", "ID", "UserID=" + Account.getLoggedUser().getID(), "DATETIME(LastEditedOn)", null).fetchAll();
            do {
                notes.add(new Note(notesSet.getInt("ID")));
            } while (notesSet.next());

            CachedRowSet sharedNotesSet = new SelectQuery("NoteShare", "NoteID", "UserID=" + Account.getLoggedUser().getID()).fetchAll();
            do {
                notes.add(new Note(sharedNotesSet.getInt("NoteID")));
            } while (sharedNotesSet.next());
        } catch (SQLException e) {
            log.catching(e);
            log.error("Failed to load notes");
        }
        return notes;
    }

    /**
     * @return The currently selected note from the list
     */
    @FXML
    public Note getSelectedNote() {
        ObservableList<Note> selectedNote = noteTable.getSelectionModel().getSelectedItems();
        if (!selectedNote.isEmpty()) {
            return selectedNote.get(0);
        }

        log.error("Note could not be selected");
        Dialog.showError("Note selection error", "Note could not be selected, please try again.");
        return null;
    }

    /**
     * Opens a dialog window and waits for user input, then adds the UserID and NoteID to the NoteShare table
     */
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
        new Thread(updateView).start();
    }

    /**
     * Creates a new note, selects it as editNote, then redirects to edit note
     * @see NotesController#editNote
     * @see NotesController#goToEditNote()
     */
    public void createNewNote() {
        new InsertQuery("Note", new String[]{"UserID"}, new String[]{String.valueOf(Account.getLoggedUser().getID())});
        try {
            editNote = new Note(Integer.parseInt(new SelectQuery("Note", "ID", "UserID=" + Account.getLoggedUser().getID() + " AND Title IS NULL AND Content IS NULL").fetch()));
        } catch (NumberFormatException e) {
            log.catching(e);
            log.error("Failed to select and set new note");
        }

        goToEditNote();
    }

    /**
     * Deletes the selected note, then refreshes the note list in a new Thread
     */
    public void deleteNote() {
        if (getSelectedNote() != null) {
            DeleteQuery q = new DeleteQuery("Note", "ID=" + getSelectedNote().getID());
            if (q.Count() <= -1) {
                Dialog.showError("Failed to delete selected note, please try again.");
            }
            new Thread(updateView).start();
        }
    }

    /**
     * Checks if a note is selected, then redirects to edit note
     * @see NotesController#goToEditNote()
     */
    public void editNote() {
        if (getSelectedNote() != null) {
            editNote = getSelectedNote();
            goToEditNote();
        }
    }

    /**
     * Redirects the user to the edit note screen
     */
    public void goToEditNote() {
        PetStudyBuddies.setStage("/fxml/Notes/EditNote.fxml", "Edit note");
    }

    /**
     * @return The current note chosen to be edited
     */
    public static Note getEditNote() {
        return editNote;
    }
}
