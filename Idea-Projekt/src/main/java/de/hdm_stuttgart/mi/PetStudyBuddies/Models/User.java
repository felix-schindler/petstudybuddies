package de.hdm_stuttgart.mi.PetStudyBuddies.Models;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.UpdateQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Model;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends Model {
    /**
     * log object for error handling
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
     * @param ID ID of the user in the database
     */
    public User(int ID) {
        super(ID);
        try {
            ResultSet user = new SelectQuery(getTable(), "*", "ID="+ID).ReadData();
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
     * @return The SHA1-Encrypted password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the users EMail (only after safe to DB!)
     */
    public void setEMail(String newMail) {
        eMail = newMail;
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
     * @throws Exception if username is changed
     */
    public void save() throws Exception {
        if (password.length() != 40) {
            log.error("The password has not the SHA1 length and is therefore not encrypted.");
            throw new Exception("Password has to be SHA1 encrypted");
        }

        String realUsername = getField("Username");
        if (username.equals(realUsername)) {
            log.error(realUsername + " tried to change username to " + username);
            throw new Exception("Username is not changeable");
        }

        new UpdateQuery(getTable(), new String[]{"EMail", "Password"}, new String[]{eMail, password});
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
}
