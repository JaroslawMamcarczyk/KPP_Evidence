package pl.kpp;

import javafx.scene.control.*;

import java.util.Optional;

public class CreateWindowAlert {


    public static void createWindowError(String coumunicat) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(coumunicat);
        alert.showAndWait();
    }

    public static Optional<ButtonType> createWindowConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);
        return alert.showAndWait();
    }


    public static Optional<String> createWindow(String title){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setContentText(title);
        return dialog.showAndWait();
    }
}
