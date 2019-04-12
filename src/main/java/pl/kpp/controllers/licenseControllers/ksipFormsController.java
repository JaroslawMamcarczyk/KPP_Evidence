package pl.kpp.controllers.licenseControllers;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.kpp.license.Ksip;

public class ksipFormsController {
    @FXML
    private TableView<Ksip> tableKsip;
    @FXML
    private TableColumn<Ksip, String> columnName;
    @FXML
    private TableColumn<Ksip, String> columnDescribe;
    @FXML
    private TableColumn<Ksip, Boolean> columnDenied;
    @FXML
    private TableColumn<Ksip, Boolean> columnRead;
    @FXML
    private TableColumn<Ksip, Boolean> columnSave;
    @FXML
    private TableColumn<Ksip, Boolean> columnModify;
    @FXML
    private TableColumn<Ksip, Boolean> columnDelete;
    @FXML
    private TableColumn<Ksip, String> columnUnity;
    private ObservableList<Ksip> listKsipName ;

    @FXML
    public void initialize() {

       // listKsipName = FXCollections.observableList(ConfigurationScreenController.getListKsip());
        setTableKsip(listKsipName);
        tableKsip.setEditable(true);
    }


    public void setTableKsip(ObservableList<Ksip> gksipList) {
        tableKsip.setItems(gksipList);
        columnName.setCellValueFactory((new PropertyValueFactory<>("name")));
        columnDescribe.setCellValueFactory(new PropertyValueFactory< >("synopsis"));
        columnDenied.setCellFactory(cellDenied->new CheckBoxTableCell<>());
        columnRead.setCellFactory(cellRead->new CheckBoxTableCell<>());
        columnSave.setCellFactory(cellSave ->new CheckBoxTableCell<>());
        columnModify.setCellFactory(cellModify -> new CheckBoxTableCell<>());
        columnDelete.setCellFactory(cellDelete->new CheckBoxTableCell<>());

        columnDenied.setCellValueFactory(cellFactory -> {
            SimpleBooleanProperty isDeniedChecked = new SimpleBooleanProperty(cellFactory.getValue().isAcess());
            isDeniedChecked.addListener((arg, bool, bool1)->{
                cellFactory.getValue().setAcess(bool1);
                cellFactory.getValue().showKsip();
                tableKsip.refresh();
            });
            return isDeniedChecked;
        });

        columnRead.setCellValueFactory(cellFactory -> {
            SimpleBooleanProperty isReadChecked = new SimpleBooleanProperty(cellFactory.getValue().isRead());
            isReadChecked.addListener((observable, oldVal, newVal)->{
                cellFactory.getValue().setRead(newVal);
                cellFactory.getValue().showKsip();
                tableKsip.refresh();
            });
            return isReadChecked;
        });

        columnSave.setCellValueFactory(cellFactory -> {
            SimpleBooleanProperty isSaveChecked = new SimpleBooleanProperty(cellFactory.getValue().isInsert());
            isSaveChecked.addListener((observable, oldVal, newVal)->{
                cellFactory.getValue().setInsert(newVal);
                cellFactory.getValue().showKsip();
                tableKsip.refresh();
            });
            return isSaveChecked;
        });

        columnModify.setCellValueFactory(cellFactory -> {
            SimpleBooleanProperty isModifyChecked = new SimpleBooleanProperty(cellFactory.getValue().isModify());
            isModifyChecked.addListener((observable, oldVal, newVal)->{
                cellFactory.getValue().setModify(newVal);
                cellFactory.getValue().showKsip();
                tableKsip.refresh();
            });
            return isModifyChecked;
        });

        columnDelete.setCellValueFactory(cellFactory->{
            SimpleBooleanProperty booleanDelete = new SimpleBooleanProperty(cellFactory.getValue().isDelete());
            booleanDelete.addListener((observable, oldVal, newVal)-> {
                cellFactory.getValue().setDelete(newVal);
                cellFactory.getValue().showKsip();
                tableKsip.refresh();
            });
            return booleanDelete;
        });
    }

    @FXML
    void clickLegites(ActionEvent event) {
        listKsipName.get(0).setInsert(true);
        tableKsip.refresh();
    }

    @FXML
    void clickCriminal(ActionEvent event) {

    }

    @FXML
    void clickOffice(ActionEvent event) {

    }

    @FXML
    void clickSaveKsip(ActionEvent event) {
        int counter = 00;
        for (Ksip x : listKsipName) {
        //    KsipDAO ksip = new KsipDAO(x,counter);
            counter++;
        //    KsipDAO.getListKsipDao().add(ksip);
        }
    }
}
