package com.institute.util;

import javafx.scene.control.Alert;

public class AlertUtil {
    private static Alert alert;

    private AlertUtil() {
    }

    public static Alert getAlert(String title, String headerText, Alert.AlertType alertType){
        alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setResizable(false);
        return alert;
    }
}
