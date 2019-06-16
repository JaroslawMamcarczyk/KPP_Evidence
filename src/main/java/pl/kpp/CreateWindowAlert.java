package pl.kpp;

import javafx.scene.control.*;

import java.util.Optional;

public class CreateWindowAlert {
    private static ButtonType ok = new ButtonType("OK");
    private static ButtonType stay = new ButtonType("Dodaj kolejny");

    public static ButtonType getOk() {
        return ok;
    }

    public static ButtonType getStay() {
        return stay;
    }

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
       // ButtonType cancel = new ButtonType("Wróć", ButtonBar.ButtonData.CANCEL_CLOSE);
       // alert.getButtonTypes().addAll(ok,stay,cancel);
        return alert.showAndWait();
    }
}
