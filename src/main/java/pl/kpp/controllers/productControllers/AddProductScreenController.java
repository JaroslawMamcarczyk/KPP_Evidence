package pl.kpp.controllers.productControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import pl.kpp.CreateWindowAlert;
import pl.kpp.converters.product.ProductKindConverter;
import pl.kpp.converters.product.ProductTypeConverter;
import pl.kpp.dao.productDao.ProductDao;
import pl.kpp.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;

public class AddProductScreenController {
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldPrice;
    @FXML
    private ChoiceBox<Product.ProductType> choiceBoxType;
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
    private ChoiceBox<String> choiceBoxCategory;
    private static ArrayList<String> categoryList = new ArrayList<>();

    public static ArrayList<String> getCategoryList(){return  categoryList;}

    @FXML
    public void clickSave(){
        boolean isGood = true;
        if(textFieldName.getText().isEmpty()){
            isGood = false;
            textFieldName.setStyle("-fx-background-color: red");
        }
        if(textFieldEvidential.getText().isEmpty()&& textFieldInventory.getText().isEmpty()&&textFieldSerial.getText().isEmpty()) {
            isGood = false;
            textFieldSerial.setStyle("-fx-background-color: red");
            textFieldInventory.setStyle("-fx-background-color: red");
            textFieldEvidential.setStyle("-fx-background-color: red");
        }
            int productKind=0;
            BigDecimal productPrice = null;
            if(choiceboxKind.getSelectionModel().getSelectedItem()!=null) {
                productKind = choiceboxKind.getSelectionModel().getSelectedItem().fromString();
            }
            if(!textFieldPrice.getText().isEmpty()) {
                if (textFieldPrice.getText().matches("([0-9])*\\.[0-9]{2}")|| textFieldPrice.getText().matches("([0-9])*"))
                    productPrice = new BigDecimal(textFieldPrice.getText());
                else {
                    textFieldPrice.setStyle("-fx-background-color: red");
                    isGood = false;
                }
            }
            Integer productYear=null;
            if(!textFieldYear.getText().isEmpty()){
                if(textFieldYear.getText().matches("[1-2][0-9]{3}"))
                   productYear = Integer.parseInt(textFieldYear.getText());
                else{
                    textFieldYear.setStyle("-fx-background-color: red");
                    isGood = false;
                }
            }
            String productComment = "";
            if(!textFieldComment.getText().isEmpty())
                productComment = textFieldComment.getText();
            int productType=0;
            if(choiceBoxType.getSelectionModel().getSelectedItem()!=null){
                productType = choiceBoxType.getSelectionModel().getSelectedItem().fromString();
            }
        if(isGood) {
            ProductDao productDao = new ProductDao(productKind, textFieldName.getText(), textFieldSerial.getText(),
                    textFieldInventory.getText(), textFieldEvidential.getText(), productPrice, productYear, productType, 0, 0,productComment,choiceBoxCategory.getSelectionModel().getSelectedItem());
            productDao.saveProduckt();
        }
    }

    @FXML
    public void clickCancel(){
    }


    public void initialize() {
        ObservableList<Product.ProductKind> productKindObservableList = FXCollections.observableArrayList(Product.ProductKind.values());
        choiceboxKind.setItems(productKindObservableList);
        choiceboxKind.setConverter(new ProductKindConverter());
        ObservableList<Product.ProductType> productTypeObservableList = FXCollections.observableArrayList(Product.ProductType.values());
        choiceBoxType.setItems(productTypeObservableList);
        choiceBoxType.setConverter(new ProductTypeConverter());
        createListProductType();
        ObservableList<String> categoryObservableList = FXCollections.observableArrayList(categoryList);
        categoryObservableList.add("Dodaj nową");
        choiceBoxCategory.setItems(categoryObservableList);
        choiceBoxCategory.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            if(newValue.equals("Dodaj nową")){
                CreateWindowAlert.createWindow("Podaj nową kategorię").ifPresent(category->{
                    if(!category.equals("")){
                        categoryObservableList.add(category);
                        choiceBoxCategory.getSelectionModel().select(category);
                    }
                });
            }
        });

        }

    public static void createListProductType() {
        boolean isOnList =false;
        for (Product product : Product.getProductList()) {
            if (product.getCategory() != null) {
                if (categoryList.size() == 0) {
                    categoryList.add(product.getCategory());
                } else {
                    for (int i=0;i<categoryList.size();i++){
                       if(product.getCategory().equals(categoryList.get(i))){
                           isOnList = true;
                       }
                    }
                    if (!isOnList){
                        categoryList.add(product.getCategory());
                    }
                    isOnList = false;
                }
            }
        }
    }
}