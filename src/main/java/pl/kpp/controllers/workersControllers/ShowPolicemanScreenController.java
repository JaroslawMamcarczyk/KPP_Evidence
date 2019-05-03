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
import pl.kpp.dao.workersDao.WorkerDao;
import pl.kpp.workers.Departament;
import pl.kpp.workers.Worker;

import java.util.ArrayList;
import java.util.List;

public class ShowPolicemanScreenController {
    @FXML
    private TableColumn<Worker, String> nameColumn;
    @FXML
    private TableColumn<Worker, String> peselColumn;
    @FXML
    private TableView<Worker> policemanTableView;
    @FXML
    private TableColumn<Worker, String> surrnameColumn;
    @FXML
    private TableColumn<Worker, String> idColumn;
    @FXML
    private TableColumn<Worker, String> standingColumn;
    @FXML
    private TextField searchPolicemanTextField;
    @FXML
    private VBox vBoxButtons;


    private String search;
    private static Worker editWorker;
    private List<Worker> temporaryList = new ArrayList<Worker>();
 //   public ObservableList<Worker> specialObservablePolicemanList = FXCollections.observableArrayList();


    public static Worker getEditWorker() {
        return editWorker;
    }


    @FXML
    public void initialize() {
        WorkerDao.isChangeOnDatabaseProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                Database date = new Database();
                WorkerDao.readWorkers(date);
                WorkerDao.isChangeOnDatabaseProperty().setValue(false);
            }
        });
        ObservableList<Worker> observableListWorker;
        observableListWorker = FXCollections.observableList(Worker.getWorekrList());
        setPolicemanTableView(observableListWorker);
        observableListWorker.addListener((ListChangeListener.Change<? extends Worker> c)-> {      //dodanie Listnera na obserwowaną listę
                while (c.next()) if (c.wasUpdated()) setPolicemanTableView(observableListWorker);
            }
        );
        /**
         * Getting the element from the table on which the cursor is positioned
         */
            policemanTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->editWorker = newValue);
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
                    for (Worker x : observableListWorker) {
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
                    ObservableList<Worker> listResult = FXCollections.observableArrayList(temporaryList);
                    setPolicemanTableView(listResult);
                }
        );
        ToggleGroup buttonsgroup = new ToggleGroup();
        for(Departament departament:Departament.getDepartamentList()){
            ToggleButton toggleButton = new ToggleButton(departament.getName());
            toggleButton.setWrapText(true);
            toggleButton.setPrefWidth(340);
            toggleButton.setToggleGroup(buttonsgroup);
            toggleButton.setOnAction(event-> {
                        List <Worker> workerSpecialList = new ArrayList<>();
                for (Worker worker : Worker.getWorekrList()) {
                    if(worker.getPolicemanDepartament()!=null&& worker.getPolicemanDepartament().getId()==departament.getId())
                    workerSpecialList.add(worker);
                }
                setPolicemanTableView(FXCollections.observableArrayList(workerSpecialList));
                        });
            vBoxButtons.getChildren().add(toggleButton);
        }
    }
    /**
     * filling the table with data
     * @param glist - workers list
     */

    public void setPolicemanTableView(ObservableList<Worker> glist) {
        policemanTableView.setItems(glist);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surrnameColumn.setCellValueFactory(new PropertyValueFactory<>("surrname"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ewidential"));
        peselColumn.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        standingColumn.setCellValueFactory(new PropertyValueFactory<>("namePoliceDepartament"));
    }

    @FXML
     void clickShowAll(){
        ObservableList<Worker> listResult = FXCollections.observableArrayList(Worker.getWorekrList());
        setPolicemanTableView(listResult);
    }
}
