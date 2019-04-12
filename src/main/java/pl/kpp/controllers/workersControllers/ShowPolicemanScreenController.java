package pl.kpp.controllers.workersControllers;


import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.kpp.controllers.MainScreenController;
import pl.kpp.workers.Policeman;
import java.io.IOException;
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

    private String search;
    private Node pane;
    private MainScreenController mainScreenController;
    private static Policeman editPoliceman;
    private List<Policeman> temporaryList = new ArrayList<Policeman>();

    public static Policeman getEditPoliceman() {
        return editPoliceman;
    }

    public void setMainScreenController(MainScreenController controller){
        this.mainScreenController = controller;
    }

    @FXML
    public void initialize() {
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
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/policeman/DetailsPolicemanScreen.fxml"));
                try{
                    pane = loader.load();
                }catch (IOException e) {
                    System.out.println("Nie wczytałem danych policjanta" + e);
                }
                mainScreenController.setCentralPanel(pane);
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
}
