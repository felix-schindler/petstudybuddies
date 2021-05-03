package de.hdm_stuttgart.mi.PetStudyBuddies.Core;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Utility functions you can use everywhere
 */
public class Utils {
    /**
     * Validates the user input in the text fields.
     *
     * @return the trimmed input string
     */
    public static String getInputString(TextField textField) {
        if (textField.getText() == null || textField.getText().trim().length() == 0)
            return null;
        return textField.getText().trim();
    }

    /**
     * Validates the user input in the password fields.
     *
     * @return the input string
     */
    public static String getInputString(PasswordField textField) {
        if (textField.getText() == null || textField.getText().length() == 0)
            return null;
        return textField.getText();
    }

    /**
     * "Validates" the given eMail (locally)
     *
     * @param eMail eMail to be checked
     * @return true if the eMail is valid
     */
    public static boolean verifyMail(String eMail) {
        return eMail.matches("^\\S+@\\S+\\.\\S+$");
    }

    /**
     * SHA1 for Java bc apparently it's too hard to have a standard sha1() function
     * @param input string to be encrypted
     * @return sha1 encrypted string
     */
    public static String sha1(String input) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception ignored) {}
        return null;
    }

    /**
     * Print a result set to system out.
     *
     * @param  rs           The ResultSet to print
     * @throws SQLException If there is a problem reading the ResultSet
     */
    public static void printResultSet(ResultSet rs) throws SQLException
    {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(" | ");
                System.out.print(rs.getString(i));
            }
            System.out.println("");
        }
    }
}
