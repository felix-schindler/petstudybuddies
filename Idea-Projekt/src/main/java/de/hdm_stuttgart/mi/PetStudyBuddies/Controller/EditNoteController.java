package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Controller;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.Note;
import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditNoteController extends Controller implements Initializable {
    private final static Logger log = LogManager.getLogger(EditNoteController.class);

    @FXML
    private TextField title;
    @FXML
    private TextArea content;

    private Note note;
    private int ID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ID = NotesController.getEditNote();

        if (ID != -1) {
            note = new Note(ID);
            title.setText(note.getContent());
            content.setText(note.getContent());
        } else {
            log.error("Failed to get a note");
            log.info("Tipp: Check if you have set the ID");
            log.debug("Redirecting to home screen");
            PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListDashboard2.fxml");
        }
    }

    @FXML
    public void save() {
        note.setTitle(Utils.getInputString(title));
        note.setContent(Utils.getInputString(content));
        note.save();
    }
}