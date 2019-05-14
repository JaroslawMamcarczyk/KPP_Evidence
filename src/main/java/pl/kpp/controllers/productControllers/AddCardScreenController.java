package pl.kpp.controllers.productControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pl.kpp.converters.workers.PolicemanConverter;
import pl.kpp.dao.productDao.CardsDao;
import pl.kpp.workers.Worker;

public class AddCardScreenController {


    @FXML
    private TextField textfieldNewNumber;

    @FXML
    private Button cancelButton;

    @FXML
    private ChoiceBox<Worker> choiceWorkers;

    @FXML
    void clickCancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize(){
        cancelButton.setGraphic(new ImageView("/pics/cancel.jpg"));
        ObservableList<Worker> workerObservableList= FXCollections.observableList(Worker.getWorekrList());
        choiceWorkers.setItems(workerObservableList);
        choiceWorkers.setConverter(new PolicemanConverter());
    }

    @FXML
    public void clickSave(){
        if(textfieldNewNumber.getText()!=" "){
            if(choiceWorkers.getValue()!=null){
                CardsDao cardsDao = new CardsDao(textfieldNewNumber.getText(),choiceWorkers.getValue().getId());
                cardsDao.saveCards();
                CardsScreenController.setIsNewCard();
                clickCancel();
            }
        }
    }
}
