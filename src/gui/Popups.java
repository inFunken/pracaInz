package gui;

import javafx.scene.control.Alert;

public class Popups {
    public static void genericError(String contentText){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}