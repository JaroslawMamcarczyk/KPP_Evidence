package pl.kpp.controllers.materials;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.kpp.CreateWindowAlert;
import pl.kpp.converters.materials.DeliveryConverter;
import pl.kpp.dao.materialsDao.DeliverysDao;
import pl.kpp.dao.materialsDao.TransactionDao;
import pl.kpp.materials.Deliverys;

public class AddNewGetInController extends AddNewTransactionController {

    private TextField newDeliverer;
    private TextField newAddress;
    private ChoiceBox<Deliverys> choiceDelivery;
    private boolean isButtonNewDeliveryPush = false;
    @FXML
    VBox vBoxChangeDeliverer;
    @FXML
    TextField yearField;
    @FXML
    TextField monthField;
    @FXML
    TextField dayField;
    @FXML
    TextField numberOfTransactionField;

    @FXML
    void initialize(){
       super.initialize();
    }

    @FXML
    void clickSave(ActionEvent event) {
        DeliverysDao.readDeliverys();
        if (DeliverysDao.getDeliveryDaoList().size()==0) {
            DeliverysDao delivery = new DeliverysDao("Komenda Wojewódzka Policji w Krakowie", "Mogilska 109, Kraków");
            delivery.saveDelivery();
        }
        String date;
        if (!numberOfTransactionField.getText().isEmpty()) {
        if(yearField.getText().matches("[1-2]\\d{3}")&&monthField.getText().matches("[0-1]\\d")&&dayField.getText().matches("[0-3]\\d")) {
                date = yearField.getText() + "-" + monthField.getText() + "-" + dayField.getText();
                if (!isButtonNewDeliveryPush) {
                    if (SaveNewTransaction(2)) {
                        TransactionDao transactionDAO = new TransactionDao(0,1,numberOfTransactionField.getText(), date, 2);
                        transactionDAO.saveTransaction(2);
                        ShowTransactionScreenController.setIsNewTransaction();
                    }
                } else {
                    if (newDeliverer.getText() != null) {
                        String deliveryToSave = newDeliverer.getText();
                        if (SaveNewTransaction(2)) {
                            DeliverysDao deliverysDAO = new DeliverysDao(deliveryToSave, newAddress.getText());
                            int lastId = deliverysDAO.saveDelivery();
                            TransactionDao transactionDAO = new TransactionDao(0,lastId, numberOfTransactionField.getText(), date, 2);
                            transactionDAO.saveTransaction(2);
                            ShowTransactionScreenController.setIsNewTransaction();
                        }
                    } else if (choiceDelivery.getValue() != null) {
                    }
                }
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }
            else CreateWindowAlert.createWindowError("Brak lub zły format daty");
        }
        else CreateWindowAlert.createWindowError("Wpisz numer faktury");
    }

    @FXML
    void clickChangeDistributor(ActionEvent event) {
        isButtonNewDeliveryPush=true;
        ObservableList<Deliverys> deliveryObservableList = FXCollections.observableArrayList();
        Label textLabel = new Label("Wpisz nazwe nowego dostawcy");
        newDeliverer = new TextField();
        Label textLabe2 = new Label("Oraz (opcjonalnie) adres");
        newAddress = new TextField();
        Label chocieDelivery = new Label(("Lub wybierz z listy dotychczasowych:"));
        DeliverysDao.readDeliverys();
        for (DeliverysDao deliverysDAO:DeliverysDao.getDeliveryDaoList()){
        deliveryObservableList.add(new Deliverys(deliverysDAO));
        }
        choiceDelivery = new ChoiceBox<>();
        choiceDelivery.setConverter(new DeliveryConverter());
        choiceDelivery.setItems(deliveryObservableList);
        vBoxChangeDeliverer.getChildren().addAll(textLabel,newDeliverer,textLabe2,newAddress,chocieDelivery,choiceDelivery) ;
    }
}
