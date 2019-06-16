package pl.kpp.controllers.productControllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pl.kpp.controllers.MainScreenController;
import pl.kpp.dao.Database;
import pl.kpp.dao.productDao.ProductDao;
import pl.kpp.product.Product;
import javafx.fxml.FXML;

public class ProductScreenController {
    @FXML
    private AnchorPane anchorGeneral;
    @FXML
    private TableView<Product> tableViewProduct;
    @FXML
    private TableColumn<Integer, Product> tableID;

    @FXML
    private TableColumn<String, Product> tableKind;

    @FXML
    private TableColumn<String, Product> tableProduct;

    @FXML
    private TableColumn<String, Product> tableSerial;

    @FXML
    private TableColumn<String, Product> tableInventory;

    @FXML
    private TableColumn<Integer, Product> tableEwidential;

    @FXML
    private TableColumn<Integer, Product> tablePrice;

    @FXML
    private TableColumn<Integer, Product> tableYear;

    @FXML
    private TableColumn<String, Product> tableType;
    private static BooleanProperty isNewProduct = new SimpleBooleanProperty(false);
    private static Product chosenProduct;
    public static Product getChosenProduct(){return chosenProduct;}

    public static void setIsNewProduct(){ isNewProduct.set(true);}

//    @FXML
//    private TableColumn<Integer, Product> tableRoom;
//
//    @FXML
//    private TableColumn<Integer, Product> tableDepartment;

    @FXML
    private TableColumn<String, Product> tableComments;

    public void setProductTable(ObservableList<Product> glist) {
        tableViewProduct.setItems(glist);
        tableID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableID.prefWidthProperty().bind(tableViewProduct.widthProperty().multiply(0.03));
        tableKind.setCellValueFactory(new PropertyValueFactory<>("kind"));
        tableKind.prefWidthProperty().bind(tableViewProduct.widthProperty().multiply(0.05));
        tableProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tableProduct.prefWidthProperty().bind(tableViewProduct.widthProperty().multiply(0.285));
        tableSerial.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        tableSerial.prefWidthProperty().bind(tableViewProduct.widthProperty().multiply(0.1));
        tableInventory.setCellValueFactory(new PropertyValueFactory<>("inventoryNumber"));
        tableInventory.prefWidthProperty().bind(tableViewProduct.widthProperty().multiply(0.1));
        tableEwidential.setCellValueFactory(new PropertyValueFactory<>("evidentialNumber"));
        tableEwidential.prefWidthProperty().bind(tableViewProduct.widthProperty().multiply(0.1));
        tablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tablePrice.prefWidthProperty().bind(tableViewProduct.widthProperty().multiply(0.05));
        tableYear.setCellValueFactory(new PropertyValueFactory<>("productionYear"));
        tableYear.prefWidthProperty().bind(tableViewProduct.widthProperty().multiply(0.05));
        tableType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableType.prefWidthProperty().bind(tableViewProduct.widthProperty().multiply(0.03));
  //      tableRoom.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
   //     tableDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        tableComments.setCellValueFactory(new PropertyValueFactory<>("comments"));
        tableComments.prefWidthProperty().bind(tableViewProduct.widthProperty().multiply(0.2));
    }

    public void initialize(){
        ObservableList<Product> productObservableList = FXCollections.observableList(Product.getProductList());
        setProductTable(productObservableList);
        isNewProduct.addListener(observable -> {
            if(isNewProduct.get()){
                isNewProduct.set(false);
                ProductDao.readProduckt();
                ObservableList<Product> productObservableList2 = FXCollections.observableList(Product.getProductList());
                setProductTable(productObservableList2);
            }
        });
        setIsNewProduct();
        tableViewProduct.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->
            chosenProduct = newValue);
        tableViewProduct.setOnMouseClicked(click ->{
            if (click.getClickCount()==2){
                MainScreenController.getMainScreenController().createCenter("/FXML/product/DetailsProductScreen.fxml");
            }
        });

    }
}
