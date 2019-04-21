package pl.kpp;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class CreateWindowAlert {


    public static void createWindowError(String coumunicat) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(coumunicat);
        alert.showAndWait();
    }

    public static Optional<ButtonType> CreateWindowConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);
        return alert.showAndWait();
    }
}
