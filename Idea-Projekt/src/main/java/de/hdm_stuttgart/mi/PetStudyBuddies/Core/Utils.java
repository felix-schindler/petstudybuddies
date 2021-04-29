package de.hdm_stuttgart.mi.PetStudyBuddies.Core;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
}
