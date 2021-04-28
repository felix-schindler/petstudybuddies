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
     * @return true if the input is valid
     */
    public static boolean isInputValid(TextField textField) {
        return !(textField.getText() == null || textField.getText().trim().length() == 0);
    }

    /**
     * Validates the user input in the password fields.
     *
     * @return true if the input is valid
     */
    public static boolean isInputValid(PasswordField textField) {
        return !(textField.getText() == null || textField.getText().trim().length() == 0);
    }
}
