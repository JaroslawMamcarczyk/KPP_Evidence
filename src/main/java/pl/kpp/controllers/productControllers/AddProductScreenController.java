package pl.kpp.controllers.productControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import pl.kpp.converters.product.ProductConverter;
import pl.kpp.dao.productDao.ProductDao;
import pl.kpp.product.Product;

import java.math.BigDecimal;

public class AddProductScreenController {
    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldPrice;

    @FXML
    private ChoiceBox<String> choiceBoxType;

    @FXML
    private TextField textFieldComment;

    @FXML
    private ChoiceBox<Product.ProductKind> choiceboxKind;

    @FXML
    private TextField textFieldInventory;
    @FXML
    private TextField textFieldEvidential;
    @FXML
    private TextField textFieldSerial;

    @FXML
    private TextField textFieldYear;

    @FXML
    public void clickSave(){
        ProductDao productDao = new ProductDao(choiceboxKind.getSelectionModel().getSelectedItem().fromString(),textFieldName.getText(),textFieldSerial.getText(),
                textFieldInventory.getText(),textFieldEvidential.getText(),new BigDecimal(textFieldPrice.getText()),Integer.parseInt(textFieldYear.getText()),1,0,0,textFieldComment.getText());
    productDao.saveProduckt();
    }

    @FXML
    public void clickCancel(){
    }


    public void initialize(){
        ObservableList<Product.ProductKind> productKindObservableList = FXCollections.observableArrayList(Product.ProductKind.values());
        choiceboxKind.setItems(productKindObservableList);
        choiceboxKind.setConverter(new ProductConverter());
    }
}
