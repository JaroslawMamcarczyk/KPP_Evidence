package pl.kpp.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pl.kpp.CreateWindowAlert;
import pl.kpp.Works;
import pl.kpp.dao.Database;
import pl.kpp.dao.WorksDao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class WorksScreenController {
   @FXML
    VBox vBoxWorks;
   @FXML
   VBox vBoxWorksEnding;

   private BooleanProperty isNewJob = new SimpleBooleanProperty(false);
    @FXML
    void initialize(){
        Database date = new Database();
        WorksDao.readWorks(date);
        for(Works works: WorksDao.getWorksList()){
            if(works.getStatus()==0){
                HBox hBox = new HBox();
                hBox.setSpacing(10);
                hBox.setPadding(new Insets(2,5,2,2));
                hBox.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID,null,new BorderWidths(1))));
                Label label = new Label(works.getJob());
                hBox.getChildren().add(label);
                label.setWrapText(true);
                label.setPrefWidth(470);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                String data = dateFormat.format(works.getWorksDate());
                Label labelDate = new Label(data);
                labelDate.setPrefWidth(200);
                labelDate.setMaxHeight(Double.MAX_VALUE);
                hBox.getChildren().add(labelDate);
            Button button = new Button("wykonano");
            button.setPrefWidth(200);
            button.setMaxHeight(Double.MAX_VALUE);
            button.setOnMouseClicked(clik->{
                WorksDao.deleteWorks(works.getId());
                isNewJob.set(true);
            });
            hBox.getChildren().add(button);
            vBoxWorks.getChildren().add(hBox);
            label.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Dragboard db = label.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    content.putString(label.getText());
                    db.setContent(content);
                    event.consume();
                }
            });
        }else{
            Text name = new Text(works.getJob());
            name.setWrappingWidth(373);
            name.setStyle("-fx-strikethrough: true");
                name.setFont(Font.font("VERDANA",20));
                name.setFill(Color.WHITE);
                vBoxWorksEnding.getChildren().add(name);
                Separator separator = new Separator();
                separator.setStyle("-fx-background-color: white");
                vBoxWorksEnding.getChildren().add(separator);
        }

            isNewJob.addListener(observable -> {
                if(isNewJob.getValue()){
                    isNewJob.set(false);
                    vBoxWorks.getChildren().clear();
                    vBoxWorksEnding.getChildren().clear();
                    initialize();
                }
            });
    }}

    @FXML
    void clickNewJob(){
        CreateWindowAlert.createWindow("podsj zadanie").ifPresent(job->{
            if(!job.equals("")) {
                WorksDao worksDao = new WorksDao(job);
                worksDao.saveWorks();
                isNewJob.set(true);
            } });
    }


    @FXML
    void dragOver(DragEvent event) {
        if (event.getDragboard().hasString()){
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
    @FXML
    void dragDropped(DragEvent event){
        for(Works works:WorksDao.getWorksList()){
            if(works.getJob().equals(event.getDragboard().getString())){
                WorksDao.deleteWorks(works.getId());
                isNewJob.set(true);
            }
        }
    }
}
