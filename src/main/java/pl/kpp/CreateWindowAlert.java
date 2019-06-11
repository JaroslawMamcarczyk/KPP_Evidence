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

    public static Optional<ButtonType> createWindowChoice(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);
        ButtonType ok = new ButtonType("OK");
        ButtonType stay = new ButtonType("Dodaj kolejny");
        ButtonType cancel = new ButtonType("Wróć", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().addAll(ok,stay,cancel);
        return alert.showAndWait();
    }
}
