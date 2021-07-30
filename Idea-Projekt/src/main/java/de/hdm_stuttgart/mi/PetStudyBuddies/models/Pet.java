package de.hdm_stuttgart.mi.PetStudyBuddies.models;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.db.UpdateQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.core.user.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.rowset.CachedRowSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Pet extends Model {
    /**
     * log object for error handling
     */
    private static final Logger log = LogManager.getLogger(Pet.class);

    /**
     * Name of the pet
     */
    private String name;

    /**
     * Emotion of the pet
     */
    private String emotion;

    private double balance=1;

    /**
     * @param ID
     */
    public Pet(int ID) {
        super(ID);
        try {
            ResultSet pet = new SelectQuery("Pet", "*", "ID=" + ID, null, null).fetchAll();
            name = pet.getString("Name");
            emotion = pet.getString("Emotion");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * @see Model#getTable()
     */
    @Override
    public String getTable() {
        return "Pet";
    }

    /**
     * @see Pet#name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name
     *
     * @param name New name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @see Pet#emotion
     */
    public String getEmotion() {
        return emotion;
    }

    /**
     * Sets the emotion
     *
     *
     */
    public void setEmotion() {
        this.balance=balance;
        SelectQuery UserToDoLists = new SelectQuery("ToDoList","*","UserID="+ Account.getLoggedUser().getID());
        List<String> IDsToDoLists = new ArrayList<String>();

        int nToDos = UserToDoLists.Count();
        if (nToDos > 0) {
            log.debug("Number of todolists in list " + UserToDoLists.Count());
            CachedRowSet todolistSet = UserToDoLists.fetchAll();
            log.debug("ToDoLists found:"+todolistSet.size());
            try {
                do {
                    IDsToDoLists.add(String.valueOf(todolistSet.getInt("UserID")));
                    log.debug("IDsToDoList size" + IDsToDoLists.size());
                } while (todolistSet.next());
                log.debug("List size " + IDsToDoLists.size());
            } catch (SQLException e) {
                log.debug("Could not resolve ToDos from CachedRowSet");
            }
        } else {
            log.debug("No ToDoLists existing.");
        }


        if(!IDsToDoLists.isEmpty()){
            int nTasksOpen=IDsToDoLists.stream().mapToInt(ToDoListID -> new SelectQuery("Task", "*", "ToDoListID = " + ToDoListID + " AND Until >= CURRENT_DATE", null, null, true).Count()).sum() ,
                    nTasksClosed= IDsToDoLists.stream().mapToInt(ToDoListID -> new SelectQuery("Task", "*", "ToDoListID = " + ToDoListID + " AND Until < CURRENT_DATE", null, null, true).Count()).sum();
            log.debug("Number open Tasks " + nTasksOpen + " Number closed Tasks" + nTasksClosed);

            if(nTasksClosed!= 0 && nTasksOpen!= 0){
                balance=nTasksOpen/nTasksClosed;
            }else if(nTasksClosed==0 && nTasksOpen==0 ){
                balance=1;
            }else if(nTasksClosed==0 && nTasksOpen!=0){
                balance=1.1;
            }
            log.debug(balance +" = balance");
        }else{
            log.debug("No ToDoLists.");
            balance=1;
        }


        if (balance >= 1.1) {
            this.emotion= "Sad";
        } else if (balance < 1.1 && balance >= 0.9) {
            this.emotion="Content";
        } else {
            this.emotion= "Happy";
        }
        log.debug("Emotion " + emotion);
    }

    /**
     * @see Model#save()
     */
    public boolean save() {
        log.debug("Trying to save");
        return new UpdateQuery(getTable(), new String[]{"Name", "Emotion"},
                new String[]{name, emotion},
                "ID=" + getID()).Count() == 1;
    }
}
