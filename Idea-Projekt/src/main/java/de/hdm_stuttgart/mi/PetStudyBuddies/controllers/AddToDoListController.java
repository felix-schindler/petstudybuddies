package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.views.Dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddToDoListController extends Controller implements ControlledScreen {
    private static final Logger log = LogManager.getLogger(AddToDoListController.class);
    @FXML
    Button ButtonCreateList, ButtonBack;
    @FXML
    TextField TextFieldAddNewList;


    @FXML
    public void buttonAction(ActionEvent actionEvent) {
        // Create new ToDoList
        if (actionEvent.getSource() == ButtonCreateList) {
            log.debug("Open create new ToDoList dialog");
            String title = Utils.getInputString(TextFieldAddNewList);
            if (title != null && new SelectQuery("ToDoList", "ID", "Title='" + title + "' ").Count() == 0) {
                new InsertQuery("ToDoList", new String[]{"UserID", "Title"}, new String[]{String.valueOf(Account.getLoggedUser().getID()), title});

                closeSecondScene(actionEvent);

                // TODO Update ToDoList Table
                /*
                ObservableList<ToDoList> newList = FXCollections.observableArrayList();
                CachedRowSet newToDo = new SelectQuery("ToDoList","ID","UserID="+ Account.getLoggedUser().getID() + " AND Title= '" + eingabe + "'").fetchAll();
                try {
                    newList.add(new ToDoList(newToDo.getInt("ID")));
                    ToDoListController.setSelectedListData(newList);
                    closeSecondScene(actionEvent);
                } catch (SQLException throwables) {
                    log.catching(throwables);
                    log.debug(("Created List could not be set as selected List"));
                }
                 */
            } else {
                Dialog.showError("Please enter a new Title for your List!");
                log.debug("No New Title entered, Dialog shown");
            }
        } else if (actionEvent.getSource() == ButtonBack) {     // Close dialog
            closeSecondScene(actionEvent);
        }
    }
}
