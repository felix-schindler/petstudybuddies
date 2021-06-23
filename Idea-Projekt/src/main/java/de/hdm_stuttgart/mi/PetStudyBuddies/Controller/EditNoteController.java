package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.Note;
import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import de.hdm_stuttgart.mi.PetStudyBuddies.Views.Dialog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class EditNoteController extends Controller implements Initializable {
    private final static Logger log = LogManager.getLogger(EditNoteController.class);
    @FXML
    private TextField title;
    @FXML
    private TextArea content;
    private Note note;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int ID = NotesController.getEditNote();

        if (ID != -1) {
            note = new Note(ID);
            title.setText(note.getContent());
            content.setText(note.getContent());
        } else {
            log.error("Failed to get a note");
            log.info("Tipp: Check if you have set the ID");
            log.debug("Redirecting to home screen");
            PetStudyBuddies.setStage("");
        }
    }

    public void save() {
        note.setTitle(Utils.getInputString(title));
        note.setContent(Utils.getInputString(content));

        if (note.save()) {
            PetStudyBuddies.setStage("/fxml/Notes/Notes.fxml");
        } else {
            Dialog.showError("Failed to save note");
            log.error("Failed to save note");
        }
    }
}
