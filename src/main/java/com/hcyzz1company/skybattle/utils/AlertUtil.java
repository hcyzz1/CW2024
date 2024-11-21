package com.hcyzz1company.skybattle.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;

/**
 * Utility class to show various types of alerts in a JavaFX application.
 * You can choose the alert type from "information", "warning", "error", and "confirmation".
 */
public class AlertUtil {

    /**
     * Shows an alert with the specified type, title, header, and content.
     * This is a common private method used by other alert types.
     *
     * @param alertType The type of the alert (e.g., INFORMATION, WARNING, ERROR, CONFIRMATION).
     * @param title     The title of the alert window.
     * @param header    The header text of the alert window.
     * @param content   The content of the alert window.
     */
    private static void showAlert(AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);

        if (alertType == AlertType.ERROR) {
            TextArea textArea = new TextArea(content);
            textArea.setEditable(false);
            alert.getDialogPane().setContent(textArea);
        } else {
            alert.setContentText(content);
        }

        alert.showAndWait();
    }

    /**
     * Displays an INFORMATION alert with the given title, header, and content.
     *
     * @param title   The title of the alert.
     * @param header  The header text of the alert.
     * @param content The content of the alert.
     */
    public static void showInfo(String title, String header, String content) {
        showAlert(AlertType.INFORMATION, title, header, content);
    }

    /**
     * Displays a WARNING alert with the given title, header, and content.
     *
     * @param title   The title of the alert.
     * @param header  The header text of the alert.
     * @param content The content of the alert.
     */
    public static void showWarning(String title, String header, String content) {
        showAlert(AlertType.WARNING, title, header, content);
    }

    /**
     * Displays an ERROR alert with the given title, header, and content.
     *
     * @param title   The title of the alert.
     * @param header  The header text of the alert.
     * @param content The content of the alert.
     */
    public static void showError(String title, String header, String content) {
        showAlert(AlertType.ERROR, title, header, content);
    }

}
