package pl.kpp.controllers.productControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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

    @FXML
    private TableColumn<Integer, Product> tableRoom;

    @FXML
    private TableColumn<Integer, Product> tableDepartment;

    @FXML
    private TableColumn<String, Product> tableComments;

    public void setProductTable(ObservableList<Product> glist) {
        tableViewProduct.setItems(glist);
        tableID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableKind.setCellValueFactory(new PropertyValueFactory<>("kind"));
        tableProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tableSerial.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        tableInventory.setCellValueFactory(new PropertyValueFactory<>("inventoryNumber"));
        tableEwidential.setCellValueFactory(new PropertyValueFactory<>("evidentialNumber"));
        tablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableYear.setCellValueFactory(new PropertyValueFactory<>("productionYear"));
        tableType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableRoom.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        tableDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        tableComments.setCellValueFactory(new PropertyValueFactory<>("comments"));
    }

    public void initialize(){
        ProductDao.readProduckt();
        ObservableList<Product> productObservableList = FXCollections.observableList(Product.getProductList());
        setProductTable(productObservableList);
    }
}
