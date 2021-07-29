package de.hdm_stuttgart.mi.PetStudyBuddies.core;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;

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
     * Validates the user input in the text fields.
     *
     * @return the trimmed input string
     */
    public static String getInputString(TextArea textField) {
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
     *
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
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Print a result set to system out.
     *
     * @param rs The ResultSet to print
     */
    public static void printResultSet(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(" | ");
                    System.out.print(rs.getString(i));
                }
                System.out.println();
            }
            rs.first();
        } catch (SQLException e) {
            System.err.println("Didn't work");
        }
    }

    /**
     * Parses a given String to a date
     *
     * @param dateStr string to be parsed
     * @return Date as a valid object
     */
    public static LocalDate parseDate(String dateStr) {
        LocalDate date = null;
        try {
            date = LocalDate.ofEpochDay(Long.parseLong(dateStr));
        } catch (NumberFormatException ignored) {
            try {
                int year = Integer.parseInt(dateStr.substring(0, 4));
                int month = Integer.parseInt(dateStr.substring(5, 7));
                int day = Integer.parseInt(dateStr.substring(8, 10));
                date = LocalDate.of(year, month, day);
            } catch (NumberFormatException ignored1) {
            }
        }

        return date;
    }
}
