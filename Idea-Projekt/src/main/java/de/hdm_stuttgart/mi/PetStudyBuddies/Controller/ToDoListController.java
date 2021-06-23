package de.hdm_stuttgart.mi.PetStudyBuddies.Controller;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Models.ToDoList;
import de.hdm_stuttgart.mi.PetStudyBuddies.PetStudyBuddies;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ToDoListController extends Controller implements Initializable {
    /**
     * Log object for error handling
     */
    private static final Logger log = LogManager.getLogger(ToDoListController.class);

    @FXML
    Label LabelUsername;
    @FXML
    Label LabelCountToDoToday, LabelCountToDoScheduled, LabelCountToDoFlagged, LabelCountToDoAll;
    @FXML
    Button ButtonToDoToday, ButtonToDoScheduled, ButtonToDoFlagged, ButtonToDoAll, ButtonAddNewList, ButtonViewList;
    @FXML
    ScrollPane ScrollPaneAllLists;
    @FXML
    TableView TableViewTest;
    @FXML
    TableColumn colTitle;
    /*
    @FXML
    TableColumn<Object, Object> colTitle;
    ObservableList<ToDoList> data = FXCollections.observableArrayList();
     */
    Scene SceneToDoDashboard, SceneToDoViewList;
    @FXML
    TextField TextFieldAddNewList;
    int NToday, NScheduled, NFlagged, NAll;
    @FXML
    CachedRowSet AllUserLists = new SelectQuery("ToDoList", "ID", "UserID = " + Account.getLoggedUser().getID(), "ID", null).fetchAll();
    @FXML
    CachedRowSet TodayUserLists = new SelectQuery("ToDoList, Task", "*", "UserID = " + Account.getLoggedUser().getID() + " AND date(datetime(Task.Until / 1000 , 'unixepoch')) = date('now')").fetchAll();
    @FXML
    CachedRowSet ScheduledUserLists = new SelectQuery("ToDoList, Task", "DISTINCT(ToDoList.ID), ToDoList.UserID, ToDoList.Title", "UserID = " + Account.getLoggedUser().getID() + " AND date(datetime(Task.Until / 1000 , 'unixepoch')) IS NOT NULL").fetchAll();
    @FXML
    CachedRowSet FlaggedUserLists = new SelectQuery("ToDoList", "*", "UserID = " + Account.getLoggedUser().getID() + " AND Flagged = '1'").fetchAll();
    @FXML
    Stage anotherStage = new Stage();
    @FXML
    Scene secondScene;
    @FXML
    TableView TableViewList;
    private Stage Window;
    private final Integer UserID = Account.getLoggedUser().getID();
    //ObservableList<ToDoList> selectedList;

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        Stage stage = null;
        Parent root = null;


        if (event.getSource() == ButtonAddNewList) {
            log.debug("ButtonAddList was clicked");
            try {
                FXMLLoader firstPaneLoader = new FXMLLoader(getClass().getResource("/fxml/ToDoList/ToDoListDashboard2.fxml"));
                Parent firstPane = firstPaneLoader.load();
                FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource("/fxml/ToDoList/ToDoListAddList.fxml"));
                Parent secondPane = secondPageLoader.load();
                Scene secondScene = new Scene(secondPane);
                anotherStage.setScene(secondScene);
                anotherStage.show();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }

        if (root != null && stage != null) {
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LabelUsername.setText(Account.getLoggedUser().getUsername());
        ObservableList<ToDoList> data = FXCollections.observableArrayList();
        try {
            do {
                data.add(new ToDoList(AllUserLists.getInt("ID")));
                log.debug("ToDo List " + AllUserLists.getInt("ID") + " added");
            } while (AllUserLists.next());

            TableViewTest.setItems(data);
            colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
            log.debug("Table View Data set");


            this.NAll = AllUserLists.size();
            log.debug("Number of All To Do Lists " + NAll);
            LabelCountToDoAll.setText(String.valueOf(NAll));

            this.NFlagged = FlaggedUserLists.size();
            log.debug("Number of Flagged To Do Lists " + NFlagged);
            LabelCountToDoFlagged.setText(String.valueOf(NFlagged));

            this.NScheduled = ScheduledUserLists.size();
            log.debug("Number of To Do Lists " + NScheduled);
            LabelCountToDoScheduled.setText(String.valueOf(NScheduled));

            this.NToday = TodayUserLists.size();
            log.debug("Number of To Do Lists " + NToday);
            LabelCountToDoToday.setText(String.valueOf(NToday));

        } catch (SQLException e) {
            log.catching(e);
            log.error("Beim Hinzufügen von ToDoLists zum TableView  ist ein Fehler aufgetreten");
        }
    }


    @FXML
    public void setTableViewTest(ResultSet QueryResult) {
        ObservableList<ToDoList> data = FXCollections.observableArrayList();
        try {
            if (QueryResult.first() == false) {
                log.debug("Data is empty");
                data.clear();
                TableViewTest.setItems(data);
            } else {
                QueryResult.first();
                do {
                    data.add(new ToDoList(QueryResult.getInt("ID")));
                    log.debug("ToDo List " + QueryResult.getInt("ID") + " added");
                } while (QueryResult.next());

                TableViewTest.setItems(data);
                colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
            }
            log.debug("Table View Data set");
        } catch (SQLException e) {
            log.catching(e);
            log.error("Beim Hinzufügen von ToDoLists zum TableView  ist ein Fehler aufgetreten");
        }

    }


    @FXML
    void filterButtons(ActionEvent actionEvent) {
        log.debug("Button Event Handler called");
        if (actionEvent.getSource() == ButtonToDoAll) {
            setTableViewTest(AllUserLists);
            log.debug("AllUserLists was set");
        } else if (actionEvent.getSource() == ButtonToDoScheduled) {
            setTableViewTest(ScheduledUserLists);
            log.debug("ScheduledUserLists was set");
        } else if (actionEvent.getSource() == ButtonToDoFlagged) {
            setTableViewTest(FlaggedUserLists);
            log.debug("FlaggedUserLists was set");
        } else if (actionEvent.getSource() == ButtonToDoToday) {
            setTableViewTest(TodayUserLists);
            log.debug("TodayUserLists was set");
        } else if (actionEvent.getSource() == ButtonViewList) {
            log.debug("ButtonViewList was clicked on");
            ObservableList<ToDoList> selectedList = TableViewTest.getSelectionModel().getSelectedItems();
            log.debug("Observable List with selected Items was created");
            if (!selectedList.isEmpty()) {
                log.debug("Items were selected");
                //setSelectedList(selectedList);
                Controller.selectedList = selectedList;
                if (Controller.selectedList.isEmpty()) {
                    log.debug("List is empty");
                }
                closeButtonAction(ButtonViewList);
                PetStudyBuddies.setStage("/fxml/ToDoList/ToDoListViewList2.fxml");

                //FXMLLoader firstPaneLoader = new FXMLLoader(getClass().getResource("/fxml/ToDoList/ToDoListDashboard2.fxml"));
                        /*try {
                           // Parent firstPane = firstPaneLoader.load();

                            FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource("/fxml/ToDoList/ToDoListViewList2.fxml")) ;
                            Parent secondPane = secondPageLoader.load();
                            Scene secondScene = new Scene(secondPane);
                            Window.setScene(secondScene);
                            Window.show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/


                //setTableViewList(selectedList);
                        /*
                        public void setSelectedList(ObservableList<ToDoList> selectedList){
                                this.selectedList=selectedList;
                        }
                        public ObservableList<ToDoList> getSelectedList(){
                            return selectedList;
                           }
                         */

            } else log.debug("nothing selected");

        }
    }

    @FXML
    public void closeButtonAction(Button CloseButton) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void openSecondScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(secondScene);
    }

    @FXML
    public void setSecondScene(Scene scene) {
        secondScene = scene;
    }

    @FXML
    public ObservableList<ToDoList> getSelectedList() {
        return selectedList;
    }

}
