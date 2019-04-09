package pl.kpp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/FXML/MainScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("KPP Ewidential");
        primaryStage.setScene(new Scene(root, 1200, 700));
       // String cssPath = this.getClass().getResource("application.css").toExternalForm();
       // root.getStylesheets().add(cssPath);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
