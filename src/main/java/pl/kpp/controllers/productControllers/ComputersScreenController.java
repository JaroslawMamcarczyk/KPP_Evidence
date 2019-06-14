package pl.kpp.controllers.productControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.kpp.product.Computer;
import pl.kpp.workers.Worker;



public class ComputersScreenController {
    @FXML
    private TableView tableViewComputers;
    @FXML
    private TableColumn<Computer,Integer> columnIp;
    @FXML
    private TableColumn<Computer,String> columnMask;
    @FXML
    private TableColumn<Computer,String> columnGate;
    @FXML
    private TableColumn<Computer,String> columnMac;
    @FXML
    private TableColumn<Computer,String> columnName;
    @FXML
    private TableColumn<Computer,String> columnWorkGroup;
    @FXML
    private TableColumn<Computer,String> columnEwidential;
    @FXML
    private TableColumn<Computer,String> columnSystem;
    @FXML
    private TableColumn<Worker,String> columnOwner;
    @FXML
    private TableColumn<Computer,String> columnSwitch;
    @FXML
    private TableColumn<Computer,String> columnPort;
    @FXML
    private TableColumn<Computer,String> columnSocket;
    @FXML
    private TableColumn<Computer,String> columnKey;

    public void setComputerTable(ObservableList<Computer> glist) {
        tableViewComputers.setItems(glist);
        columnIp.setCellValueFactory(new PropertyValueFactory<>("computerIP"));
        columnIp.prefWidthProperty().bind(tableViewComputers.widthProperty().multiply(0.08));
        columnMask.setCellValueFactory(new PropertyValueFactory<>("computerMask"));
        columnMask.prefWidthProperty().bind(tableViewComputers.widthProperty().multiply(0.08));
        columnGate.setCellValueFactory(new PropertyValueFactory<>("computerGate"));
        columnGate.prefWidthProperty().bind(tableViewComputers.widthProperty().multiply(0.08));
        columnMac.setCellValueFactory(new PropertyValueFactory<>("computerMAC"));
        columnMac.prefWidthProperty().bind(tableViewComputers.widthProperty().multiply(0.1));
        columnName.setCellValueFactory(new PropertyValueFactory<>("computerName"));
        columnName.prefWidthProperty().bind(tableViewComputers.widthProperty().multiply(0.1));
        columnWorkGroup.setCellValueFactory(new PropertyValueFactory<>("computerWorkGroup"));
        columnWorkGroup.prefWidthProperty().bind(tableViewComputers.widthProperty().multiply(0.07));
        columnEwidential.setCellValueFactory(new PropertyValueFactory<>("computerEwidential"));
        columnEwidential.prefWidthProperty().bind(tableViewComputers.widthProperty().multiply(0.1));
        columnSystem.setCellValueFactory(new PropertyValueFactory<>("computerSystem"));
        columnSystem.prefWidthProperty().bind(tableViewComputers.widthProperty().multiply(0.07));
        columnSwitch.setCellValueFactory(new PropertyValueFactory<>("computerSwitch"));
        columnSwitch.prefWidthProperty().bind(tableViewComputers.widthProperty().multiply(0.05));
        columnKey.setCellValueFactory(new PropertyValueFactory<>("computerKey"));
        columnKey.prefWidthProperty().bind(tableViewComputers.widthProperty().multiply(0.05));
        columnPort.setCellValueFactory(new PropertyValueFactory<>("computerPort"));
        columnPort.prefWidthProperty().bind(tableViewComputers.widthProperty().multiply(0.05));
        columnSocket.setCellValueFactory(new PropertyValueFactory<>("computerSocket"));
        columnSocket.prefWidthProperty().bind(tableViewComputers.widthProperty().multiply(0.05));
        columnOwner.setCellValueFactory(new PropertyValueFactory<>("computerOwner"));
        columnOwner.prefWidthProperty().bind(tableViewComputers.widthProperty().multiply(0.12));
    }

    public void initialize(){
        ObservableList<Computer> computerObservableList = FXCollections.observableArrayList(Computer.getComputerList());
        setComputerTable(computerObservableList);
    }
}
