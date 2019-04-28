package pl.kpp.controllers;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import pl.kpp.controllers.materials.ShowTransactionScreenController;
import pl.kpp.converters.workers.DepartamentConverter;
import pl.kpp.converters.workers.RanksConverter;
import pl.kpp.dao.Database;
import pl.kpp.dao.workersDao.DepartamentDao;
import pl.kpp.dao.workersDao.RanksDao;
import pl.kpp.workers.Departament;
import pl.kpp.workers.Range;
import pl.kpp.workers.Ranks;

import java.io.*;
import java.util.Optional;


public class ConfigurationScreenController {
    @FXML
    private TableView<Range> tableRange;
    @FXML
    private TableColumn<Range, ImageView> pagons;
    @FXML
    private TableColumn<Range, String> rangeName;
    @FXML
    private ListView<Departament> departamentListView;
   @FXML
    private ListView<Ranks> ranksListView;

    private static BooleanProperty isNewDepartament= new SimpleBooleanProperty(false);
    private static BooleanProperty isNewRanks = new SimpleBooleanProperty(false);

    @FXML
    void initialize() {
        isNewDepartament.addListener(observable -> {
                if(isNewDepartament.get()) {
                    Database date = new Database();
                    DepartamentDao.readDepartament(date);
                    createListViewDepartament();
                    isNewDepartament.set(false);
                }
        });
        ObservableList<Range> rangeList = FXCollections.observableList(Range.getListRange());
        tableRange.setItems(rangeList);
        rangeName.setCellValueFactory(new PropertyValueFactory<Range, String>("rangeName"));
        pagons.setCellValueFactory(new PropertyValueFactory<Range, ImageView>("pagons"));
        createListViewDepartament();
        ObservableList<Ranks> ranksObservableList = FXCollections.observableList(Ranks.getRanksList());
        createListViewRanks(ranksObservableList);
        isNewRanks.addListener(observable -> {
            if(isNewRanks.get()){
                Database date = new Database();
                RanksDao.readRanks(date);
                Ranks.createRanksList();
                createListViewRanks(FXCollections.observableList(Ranks.getRanksList()));
                isNewRanks.set(false);
            }
        });
//        ObservableList<Ksip> ksipList = FXCollections.observableList(listKsip);
//        ksipTable.setItems(ksipList);
//        nameKsip.setCellValueFactory((new PropertyValueFactory<>("name")));
//        decriptKsip.setCellValueFactory(new PropertyValueFactory< >("synopsis"));
//
//    }
    }

    private void createListViewDepartament() {
        Departament.createDepartamentList();
        ObservableList<Departament> departamentObservableList = FXCollections.observableArrayList(Departament.getDepartamentList());
        departamentListView.setItems(departamentObservableList);
        departamentListView.setCellFactory(departamentListView -> {
            TextFieldListCell<Departament> cell = new TextFieldListCell<>();
            cell.setConverter(new DepartamentConverter());
            return cell;
        });
    }
private void createListViewRanks(ObservableList<Ranks> ranksObservableList){
        ranksListView.setItems(ranksObservableList);
        ranksListView.setCellFactory(lv->{
            TextFieldListCell<Ranks> cell=new TextFieldListCell<>();
            cell.setConverter(new RanksConverter());
            return cell;
        });
}
    @FXML
    void clickAddDepartament() {
        Optional<String> result = callInputDialog("Dodawanie nowego Wydziału", "Podaj nazwę nowego Wydziału", "Wpisz nazwę");
        result.ifPresent(name ->{
            DepartamentDao newDao = new DepartamentDao(name);
            newDao.saveDepartament();
            isNewDepartament.set(true);
        });
    }

    public Optional<String> callInputDialog(String s, String s2, String s3) {
        TextInputDialog newdepartament = new TextInputDialog();
        newdepartament.setTitle(s);
        newdepartament.setHeaderText(s2);
        newdepartament.setContentText(s3);
        return newdepartament.showAndWait();
    }

    @FXML
    void clickNewRanks() {
        ObservableList<Ranks> ranksObservableList;
        Dialog<RanksDao> newRanks = new Dialog<>();
        newRanks.setHeaderText("Dodaj nowe Stanowisko");
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Stanowisko"),0,0);
        TextField newRanksField = new TextField();
        grid.add(newRanksField,1,0);
        grid.add(new Label("Wydział"),1,1);
        ChoiceBox<Departament> departamentChoiceBox = new ChoiceBox<>();
        departamentChoiceBox.setConverter(new DepartamentConverter());
        ObservableList<Departament> departamentObservableList = FXCollections.observableArrayList(Departament.getDepartamentList());
        departamentChoiceBox.setItems(departamentObservableList);
        grid.add(departamentChoiceBox,1,2);
        newRanks.getDialogPane().setContent(grid);
        ButtonType saveButton = new ButtonType("save", ButtonBar.ButtonData.OK_DONE);
        newRanks.getDialogPane().getButtonTypes().addAll(saveButton,ButtonType.CANCEL);
        newRanks.setResultConverter(dialogButton->{
            if(dialogButton==saveButton){
                RanksDao daoRanks = new RanksDao(newRanksField.getText(),departamentChoiceBox.getValue().getId());
                return daoRanks;
           }
            return null;
        });
        Optional<RanksDao> newDepartament = newRanks.showAndWait();
        newDepartament.ifPresent(daoToSave-> {
            daoToSave.saveRanks();
            isNewRanks.set(true);
        });
   }
//
//
//    /**
//     * Handling button and create new KSIP Area.
//     * @param event
//     */
//    @FXML
//    void clickNewKsip(ActionEvent event) {
//        Optional<String> result = callInputDialog("Dodawanie obszaru KSIP", "Podaj nazwę obszaru KSIP", "Wpisz nazwę");
//        result.ifPresent(name -> {
//            Optional<String> result2 = callInputDialog("Dodawanie onszaru KSIP", "Podaj opis obszaru KSIP który dodajesz", "Wpisz Opis");
//            result2.ifPresent(describe ->{
//                nameKsipArea.add(name);
//                describeKsipArea.add(describe);
//                FileOperation operation = new FileOperation();
//                operation.saveFile(name);
//                operation.saveFile(describe);
//            });
//        });
//    }
    @FXML
    void clickEditDepartament(){
        Optional<String> result = callInputDialog("Edytowanie Departamentu", "Podaj popraawną nazwę", "Wpisz nazwę");
        result.ifPresent(name-> {
            DepartamentDao.editDepartament(name, departamentListView.getSelectionModel().getSelectedItem().getId());
            isNewDepartament.set(true);
        });
    }
    @FXML
    void clickDeleteDepartament(){
        DepartamentDao.deleteDepartament(departamentListView.getSelectionModel().getSelectedItem().getName());
        isNewDepartament.set(true);
    }
}
