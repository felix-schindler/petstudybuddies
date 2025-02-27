package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.Screens;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.models.Note;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for Editing Notes
 */
public class EditNoteController extends Controller implements Initializable {
    /**
     * log object for error handling
     */
    private final static Logger log = LogManager.getLogger(EditNoteController.class);
    @FXML
    private TextField title;
    @FXML
    private TextArea content;
    @FXML
    private Note note;

    /**
     * Sets parameters needed to initialize scene
     * @param url URL location of the FXML file that was given to the FXMLLoader
     * @param resourceBundle ResourceBundle that was given to the FXMLLoader
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        note = NotesController.getEditNote();

        if (note != null) {
            title.setText(note.getTitle());
            content.setText(note.getContent());
        } else {
            log.error("Failed to get a note");
            log.info("Tipp: Check if you have set the ID");
            log.debug("Redirecting to note screen");
            Screens.setStage(NoteID);
        }
    }

    /**
     * Saves the current note, shows a dialog on error
     */
    public void save() {
        note.setTitle(Utils.getInputString(title));
        note.setContent(Utils.getInputString(content));

        if (note.save()) {
            Screens.setStage(NoteID);
        } else {
            log.error("Failed to save note");
            Dialog.showError("Failed to save note");
        }
    }
}
