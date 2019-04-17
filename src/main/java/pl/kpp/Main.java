package pl.kpp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.kpp.dao.Database;
import pl.kpp.dao.workersDao.DepartamentDao;
import pl.kpp.dao.workersDao.PolicemanDao;
import pl.kpp.dao.workersDao.RangeDao;
import pl.kpp.dao.workersDao.RanksDao;
import pl.kpp.workers.Departament;
import pl.kpp.workers.Policeman;
import pl.kpp.workers.Range;
import pl.kpp.workers.Ranks;

import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Database date = new Database();
        PolicemanDao.readPoliceman(date);
        Policeman.createList();
        RangeDao.readRange(date);
        Range.createRangeList();
        DepartamentDao.readDepartament(date);
        Departament.createDepartamentList();
        RanksDao.readRanks(date);
        Ranks.createRanksList();
        date.closeDatabase();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/FXML/MainScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("KPP Ewidential");
        primaryStage.setScene(new Scene(root, 1500, 900));
        String cssPath = this.getClass().getResource("/css/mainScreenCss.css").toExternalForm();
        root.getStylesheets().add(cssPath);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
