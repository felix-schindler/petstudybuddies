package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Controller;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
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

    private int ID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ID = NotesController.getEditNote();

        if (ID != -1) {
            try {
                SelectQuery sq = new SelectQuery("Note", "*", "ID=" + ID);
                if (sq.Count() == 1) {
                    CachedRowSet rs = sq.fetchAll();
                    title.setText(rs.getString("Title"));
                    content.setText(rs.getString("Content"));
                }
            } catch (SQLException throwables) {
                log.catching(throwables);
                log.error("Failed to get note content");
                log.info("Tipp: Check if you have set the ID");
            }
        }
    }

    @FXML
    public void save() {
        if (ID == -1)
            createNote();
        else
            saveNote();
    }

    public void saveNote() {

    }

    public void createNote() {
        String contentStr = Utils.getInputString(content);
        String titleStr = Utils.getInputString(title);

        new InsertQuery("Note", new String[]{"UserID", "Title", "Content"}, new String[]{String.valueOf(Account.getLoggedUser().getID()), titleStr, contentStr});
    }
}