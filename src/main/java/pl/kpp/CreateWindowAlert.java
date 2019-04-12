package pl.kpp;

import javafx.scene.control.Alert;

public class CreateWindowAlert {


    public static void createWindowError(String coumunicat) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(coumunicat);
        alert.showAndWait();
    }

    public static void CreateWindowAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
