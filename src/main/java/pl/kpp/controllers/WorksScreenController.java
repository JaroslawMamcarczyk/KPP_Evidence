package pl.kpp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pl.kpp.CreateWindowAlert;
import pl.kpp.Works;
import pl.kpp.dao.Database;
import pl.kpp.dao.WorksDao;


public class WorksScreenController {
   @FXML
    VBox vBoxWorks;
   @FXML
   VBox vBoxDate;
   @FXML
   VBox vBoxButton;
    @FXML
    void initialize(){
        Database date = new Database();
        WorksDao.readWorks(date);
        for(Works works: WorksDao.getWorksList()){
            Label label = new Label(works.getJob());
            label.setWrapText(true);
            vBoxWorks.getChildren().add(label);
            Button button = new Button("wykonano");
            vBoxButton.getChildren().add(button);
        }
    }

    @FXML
    void clickNewJob(){
        CreateWindowAlert.createWindow("podsj zadanie").ifPresent(job->{
            WorksDao worksDao = new WorksDao(job);
            worksDao.saveWorks();
        });
    }
}
