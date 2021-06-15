package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.UpdateQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends Model {
    /**
     * Log object for error handling
     */
    private static final Logger log = LogManager.getLogger(User.class);

    /**
     * username of the user
     */
    private String username;

    /**
     * eMail of the user
     */
    private String eMail;

    /**
     * password of the user
     */
    private String password;

    /**
     * Creates a new user linked to the users database-entry via its ID
     *
     * @param ID ID of the user in the database
     */
    public User(int ID) {
        super(ID);
        try {
            ResultSet user = new SelectQuery(getTable(), "*", "ID=" + ID).ReadData();
            username = user.getString("Username");
            eMail = user.getString("EMail");
            password = user.getString("Password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see Model#getTable()
     */
    @Override
    public String getTable() {
        return "User";
    }

    /**
     * @return Username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return EMail of the user
     */
    public String getEMail() {
        return eMail;
    }

    /**
     * Sets the users EMail (only after safe to DB!)
     */
    public void setEMail(String newMail) {
        eMail = newMail;
    }

    /**
     * @return The SHA1-Encrypted password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the users EMail (only after safe to DB!)
     */
    public void setPassword(String newPass) {
        password = Utils.sha1(newPass);
    }

    /**
     * Updates the users EMail in the database
     */
    public void setEMailDB(String newMail) {
        setField("EMail", newMail);
    }

    /**
     * Updates the users password
     */
    public void setPasswordDB(String newPass) {
        setField("Password", newPass);
    }

    /**
     * @throws Exception if username is changed
     */
    public void setUsername() throws Exception {
        throw new Exception("Username is not changeable");
    }

    /**
     * Saves changes of the User
     *
     * @see Model#save()
     */
    public boolean save() {
        log.debug("Trying to safe changes");
        if (password.length() != 40) {
            log.error("Password has to be SHA1 encrypted");
            log.info("The password has not the SHA1 length and is therefore not encrypted.");
            return false;
        }

        String realUsername = getField("Username");
        if (username.equals(realUsername)) {
            log.error("Username is not changeable");
            log.info(realUsername + " tried to change username to " + username);
            return false;
        }

        return new UpdateQuery(getTable(), new String[]{"EMail", "Password"}, new String[]{eMail, password}, "ID=" + ID).Count() == 1;
    }

    /*
    Controller: (calls setPassword of this Model)
    public boolean changePassword(String oldPw, String newPw, String newPwRepeat) {
        return false;
    }

    Controller: (calls setEMail of this Model)
    public boolean changeEmail(String newMail, String password) {
        return false;
    }
    */

    //Tests if works
    public static ObservableList<ToDoList> selectedList=null;

    public static void setSelectedList(ObservableList<ToDoList> selectedList) {
        User.selectedList = selectedList;
    }
    public static ObservableList<ToDoList> getSelectedList(){
        return selectedList;
    }
}
