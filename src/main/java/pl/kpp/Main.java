package pl.kpp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.kpp.dao.Database;
import pl.kpp.dao.workersDao.PolicemanDao;
import pl.kpp.dao.workersDao.RangeDao;
import pl.kpp.workers.Range;

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
        Database date = new Database();
        PolicemanDao.readPoliceman();
        RangeDao.readRange();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
