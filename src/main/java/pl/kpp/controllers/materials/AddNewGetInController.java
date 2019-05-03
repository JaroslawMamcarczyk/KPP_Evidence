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
    private ChoiceBox<Deliverys> choiceDelivery = new ChoiceBox<>();
    private boolean isButtonNewDeliveryPush = false;
    private Deliverys choseDelivery = null;
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
        ShowMaterialScreenController.getIsNewMaterials().addListener(observable -> {
            if(ShowMaterialScreenController.getIsNewMaterials().get()){
                createChoiceEquipmentField();
            }
        });
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
            if (yearField.getText().matches("[1-2]\\d{3}") && monthField.getText().matches("[0-1]\\d") && dayField.getText().matches("[0-3]\\d")) {
                date = yearField.getText() + "-" + monthField.getText() + "-" + dayField.getText();
                if (!isButtonNewDeliveryPush) {
                    if (SaveNewTransaction(2) != 0) {
                        TransactionDao transactionDAO = new TransactionDao(0, 1, numberOfTransactionField.getText(), date, 2);
                        transactionDAO.saveTransaction(2);
                        ShowTransactionScreenController.setIsNewTransaction();
                    }
                } else {
                    if (!newDeliverer.getText().isEmpty()) {
                        String deliveryToSave = newDeliverer.getText();
                        if (SaveNewTransaction(2) != 0) {
                            DeliverysDao deliverysDAO = new DeliverysDao(deliveryToSave, newAddress.getText());
                            int lastId = deliverysDAO.saveDelivery();
                            ShowTransactionScreenController.setIsNewDelivery(true);
                            TransactionDao transactionDAO = new TransactionDao(0, lastId, numberOfTransactionField.getText(), date, 2);
                            transactionDAO.saveTransaction(2);
                            ShowTransactionScreenController.setIsNewTransaction();
                        }
                    }
                    else {
                        if (choseDelivery != null) {
                            if (SaveNewTransaction(2) != 0) {
                                TransactionDao transactionDao = new TransactionDao(0, choseDelivery.getId(), numberOfTransactionField.getText(), date, 2);
                                transactionDao.saveTransaction(2);
                                ShowTransactionScreenController.setIsNewTransaction();
                            }
                        } else {
                            CreateWindowAlert.createWindowError("Musisz podac nazwę nowego kontrachenta lub wybrac z listy");
                        }
                    }
                    isButtonNewDeliveryPush=false;
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
        Label labelChoiceDelivery = new Label(("Lub wybierz z listy dotychczasowych:"));
        DeliverysDao.readDeliverys();
        for (DeliverysDao deliverysDAO:DeliverysDao.getDeliveryDaoList()){
        deliveryObservableList.add(new Deliverys(deliverysDAO));
        }
        choiceDelivery.setConverter(new DeliveryConverter());
        choiceDelivery.setItems(deliveryObservableList);
        vBoxChangeDeliverer.getChildren().addAll(textLabel,newDeliverer,textLabe2,newAddress,labelChoiceDelivery,choiceDelivery) ;
        choiceDelivery.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                choseDelivery = newValue;
                System.out.println(newValue.getId());
            }
        });
    }

    @FXML
    void clickAddNewMaterials() {
        ShowMaterialScreenController showMaterialScreenController = new ShowMaterialScreenController();
        showMaterialScreenController.openAddNewMaterialScreen(true);
    }
}
