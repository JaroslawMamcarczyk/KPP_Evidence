package pl.kpp.controllers.productControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.kpp.produckt.Produckt;
import javafx.fxml.FXML;

public class ProductScreenController {
    @FXML
    private TableView<Produckt> tableViewProduct;
    @FXML
    private TableColumn<Integer, Produckt> tableID;

    @FXML
    private TableColumn<String, Produckt> tableKind;

    @FXML
    private TableColumn<String, Produckt> tableProduct;

    @FXML
    private TableColumn<String, Produckt> tableSerial;

    @FXML
    private TableColumn<String, Produckt> tableInventory;

    @FXML
    private TableColumn<Integer, Produckt> tableEwidential;

    @FXML
    private TableColumn<Integer, Produckt> tablePrice;

    @FXML
    private TableColumn<Integer, Produckt> tableYear;

    @FXML
    private TableColumn<String, Produckt> tableType;

    @FXML
    private TableColumn<Integer, Produckt> tableRoom;

    @FXML
    private TableColumn<Integer, Produckt> tableDepartment;

    @FXML
    private TableColumn<String, Produckt> tableComments;

    public void setProductTable(ObservableList<Produckt> glist) {
        tableViewProduct.setItems(glist);
        tableID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableKind.setCellValueFactory(new PropertyValueFactory<>("kind"));
        tableProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tableSerial.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        tableInventory.setCellValueFactory(new PropertyValueFactory<>("inventoryNumber"));
        tableEwidential.setCellValueFactory(new PropertyValueFactory<>("evidentaialNumber"));
        tablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableYear.setCellValueFactory(new PropertyValueFactory<>("productionYear"));
        tableType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableRoom.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        tableDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        tableComments.setCellValueFactory(new PropertyValueFactory<>("comments"));
    }

    public void initialize(){
        ObservableList<Produckt> producktObservableList= FXCollections.observableList(Produckt.getProducktList());
        setProductTable(producktObservableList);
    }
}
