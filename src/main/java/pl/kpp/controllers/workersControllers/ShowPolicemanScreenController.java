package pl.kpp.controllers.workersControllers;


import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import pl.kpp.controllers.MainScreenController;
import pl.kpp.dao.Database;
import pl.kpp.dao.workersDao.PolicemanDao;
import pl.kpp.workers.Departament;
import pl.kpp.workers.Policeman;
import java.util.ArrayList;
import java.util.List;

public class ShowPolicemanScreenController {
    @FXML
    private TableColumn<Policeman, String> nameColumn;
    @FXML
    private TableColumn<Policeman, String> peselColumn;
    @FXML
    private TableView<Policeman> policemanTableView;
    @FXML
    private TableColumn<Policeman, String> surrnameColumn;
    @FXML
    private TableColumn<Policeman, String> idColumn;
    @FXML
    private TableColumn<Policeman, String> standingColumn;
    @FXML
    private TextField searchPolicemanTextField;
    @FXML
    private VBox vBoxButtons;


    private String search;
    private static Policeman editPoliceman;
    private List<Policeman> temporaryList = new ArrayList<Policeman>();
 //   public ObservableList<Policeman> specialObservablePolicemanList = FXCollections.observableArrayList();


    public static Policeman getEditPoliceman() {
        return editPoliceman;
    }


    @FXML
    public void initialize() {
        PolicemanDao.isChangeOnDatabaseProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                Database date = new Database();
                PolicemanDao.readPoliceman(date);
                Policeman.createList();
                PolicemanDao.isChangeOnDatabaseProperty().setValue(false);
            }
        });
        ObservableList<Policeman> observableListPoliceman;
        observableListPoliceman = FXCollections.observableList(Policeman.createList());
        setPolicemanTableView(observableListPoliceman);
        observableListPoliceman.addListener((ListChangeListener.Change<? extends Policeman> c)-> {      //dodanie Listnera na obserwowaną listę
                while (c.next()) if (c.wasUpdated()) setPolicemanTableView(observableListPoliceman);
            }
        );
        /**
         * Getting the element from the table on which the cursor is positioned
         */
            policemanTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                        editPoliceman = newValue;
                    }
            );
/**
 * Handling tw-click on mouse and opening window with details
 */
        policemanTableView.setOnMouseClicked(click ->{
            if (click.getClickCount()==2){
               MainScreenController.getMainScreenController().createCenter("/FXML/policeman/DetailsPolicemanScreen.fxml");
            }
        });
        /**
         * Reading the values ​​of the search field and displaying the matching values ​​dynamically
         */
        searchPolicemanTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                    temporaryList = new ArrayList<>();
                    search = newValue.toLowerCase();
                    for (Policeman x : observableListPoliceman) {
                        if (x.getName().toLowerCase().contains(newValue.toLowerCase())) {
                            if (!temporaryList.contains(x))
                                temporaryList.add(x);
                        }
                        if (x.getSurrname().toLowerCase().contains(search)) {
                            if (!temporaryList.contains(x))
                                temporaryList.add(x);
                        }
                        if (x.getEwidential().toLowerCase().contains(search)) {
                            if (!temporaryList.contains(x))
                                temporaryList.add(x);
                        }
                        if (x.getPesel().toLowerCase().contains(search)) {
                            if (!temporaryList.contains(x))
                                temporaryList.add(x);
                        }
                    }
                    ObservableList<Policeman> listResult = FXCollections.observableArrayList(temporaryList);
                    setPolicemanTableView(listResult);
                }
        );
        ToggleGroup buttonsgroup = new ToggleGroup();
        for(Departament departament:Departament.getDepartamentList()){
            ToggleButton toggleButton = new ToggleButton(departament.getName());
            toggleButton.setWrapText(true);
            toggleButton.setPrefWidth(300);
            toggleButton.setToggleGroup(buttonsgroup);
            toggleButton.setOnAction(event-> {
                        List <Policeman> policemanSpecialList = new ArrayList<>();
                for (Policeman policeman:Policeman.getPolicemanList()) {
                    if(policeman.getPolicemanDepartament()!=null&&policeman.getPolicemanDepartament().getId()==departament.getId())
                    policemanSpecialList.add(policeman);
                }
                setPolicemanTableView(FXCollections.observableArrayList(policemanSpecialList));
                        });
            vBoxButtons.getChildren().add(toggleButton);
        }
    }
    /**
     * filling the table with data
     * @param glist - workers list
     */

    public void setPolicemanTableView(ObservableList<Policeman> glist) {
        policemanTableView.setItems(glist);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surrnameColumn.setCellValueFactory(new PropertyValueFactory<>("surrname"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ewidential"));
        peselColumn.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        standingColumn.setCellValueFactory(new PropertyValueFactory<>("namePoliceDepartament"));
    }
    @FXML
     void clickShowAll(){
        ObservableList<Policeman> listResult = FXCollections.observableArrayList(Policeman.getPolicemanList());
        setPolicemanTableView(listResult);
}
}
