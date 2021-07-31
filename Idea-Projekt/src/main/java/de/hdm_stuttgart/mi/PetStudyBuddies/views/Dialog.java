package de.hdm_stuttgart.mi.PetStudyBuddies.views;

import de.hdm_stuttgart.mi.PetStudyBuddies.core.Utils;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;

/**
 * See http://code.makery.ch/blog/javafx-dialogs-official
 * for further explanations.
 */
public class Dialog {
    private static void loadStyle(Alert alert) {
        alert.getDialogPane().getScene().getStylesheets().add(Dialog.class.getResource("/styles/Dialog.css").toString());
    }

    /**
     * Show a modal info box
     *
     * @param msg A message to be displayed
     */
    public static void showInfo(final String title, final String msg) {
        final String realTitle = Objects.requireNonNullElse(title, "Info");
        final Alert alert = new Alert(AlertType.INFORMATION);
        loadStyle(alert);
        alert.setTitle(realTitle);
        alert.setHeaderText(realTitle);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void showInfo(final String msg) {
        showInfo(null, msg);
    }

    /**
     * Show a modal error box
     *
     * @param title A title to be displayed
     * @param msg   A message to be displayed
     */
    public static void showError(final String title, final String msg) {
        final String realTitle = Objects.requireNonNullElse(title, "Error");
        final Alert alert = new Alert(AlertType.ERROR);
        loadStyle(alert);
        alert.setTitle(realTitle);
        alert.setHeaderText(realTitle);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Shows Error Dialog
     * @param msg message to be shown to user
     */
    public static void showError(final String msg) {
        showError(null, msg);
    }

    /**
     * Opens new Alert InputScene
     * @param title title of Scene
     * @return String message to be displayed to User
     */
    public static String showInput(String title) {
        final Alert alert = new Alert(AlertType.CONFIRMATION);
        loadStyle(alert);
        alert.setTitle("Input");
        alert.setHeaderText(title);

        final TextField username = new TextField();
        username.setPromptText("Username...");
        VBox root = new VBox();
        // root.getStylesheets().add(Dialog.class.getResource("/styles/fullpackstyling.css").toString());
        root.getChildren().add(username);
        alert.setGraphic(root);
        alert.showAndWait();
        return Utils.getInputString(username);
    }

    /**
     * Showing an error box and terminating without any further error processing
     *
     * @param msg      An informative message
     * @param ex       The exception to be interpreted by an expert
     * @param exitCode The exit code to be used by e.g. the calling process.
     */
    public static void showExceptionAndExit(final String msg, final Exception ex, int exitCode) {
        final Alert alert = new Alert(AlertType.ERROR);
        loadStyle(alert);
        alert.setTitle("Unrecoverable error");
        alert.setHeaderText("Application will be terminated!");
        alert.setContentText(msg);

        // Create expandable Exception.
        if (null != ex) {
            final StringWriter sw = new StringWriter();
            final PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            final String exceptionText = sw.toString();

            final Label label = new Label("You may copy and forward this exception stacktrace to an expert:");

            final TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            final GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            // Set expandable Exception into the dialog pane.
            alert.getDialogPane().setExpandableContent(expContent);
        }
        alert.showAndWait();
        System.exit(exitCode);
    }
}
