package pl.kpp.controllers.workersControllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.kpp.workers.Departament;
import pl.kpp.workers.Policeman;
import pl.kpp.workers.Range;
import pl.kpp.workers.Ranks;
import pl.kpp.converters.workers.DepartamentConverter;
import pl.kpp.converters.workers.RangeConverter;
import pl.kpp.converters.workers.RanksConverter;
import pl.kpp.dao.workersDao.PolicemanDao;


public class DetailsPolicemanScreenController {

    @FXML
    private TextField ldepartament;
    @FXML
    private TextField lname;
    @FXML
    private TextField lid;
    @FXML
    private TextField lpesel;
    @FXML
    private ImageView lpagons;
    @FXML
    private TextField lrange;
    @FXML
    private VBox vBoxTextField;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonModify;
    @FXML
    private TextField lranks;
    @FXML
    private TextField ltelephone;
    @FXML
    private HBox hBoxRange;
    @FXML
    private HBox hBoxDepartament;
    @FXML
    private HBox hBoxRanks;

    private Policeman police;

    @FXML
    public void initialize() {
        police = ShowPolicemanScreenController.getEditPoliceman();
        lname.setText(police.getName() + " " + police.getSurrname());
        lname.setDisable(true);
        lid.setText(police.getEwidential());
        lid.setDisable(true);
        lpesel.setText(police.getPesel());
        lpesel.setDisable(true);
        ldepartament.setText(police.getNamePoliceDepartament());
        ldepartament.setDisable(true);
        lrange.setDisable(true);
        lranks.setDisable(true);
        if (police.getPolicemanRange()!=null) {
            lrange.setText(police.getPolicemanRange().getRangeName());
            lpagons.setImage(new Image(police.getPolicemanRange().getPath()));
        }
        if(police.getPolicemanRanks()!=null)
        lranks.setText(police.getPolicemanRanks().getNameRanks());
        }

    @FXML
    void clickSave(ActionEvent event) {
        String[] names = lname.getText().split(" ");
    }

    @FXML
    void clickCancel(ActionEvent event) {
        initialize();
    }


    @FXML
    void modifyId(){
        lid.setDisable(false);
        buttonDelete.setVisible(true);
        buttonSave.setVisible(true);
    }
    @FXML
    void modifyPesel(){
        lpesel.setDisable(false);
        buttonDelete.setVisible(true);
        buttonSave.setVisible(true);
    }
    @FXML
    void modifyRange(){
        ObservableList<Range> rangeObservableList = FXCollections.observableList(Range.getListRange());
        ChoiceBox<Range> choiceRange=new ChoiceBox<>();
        choiceRange.setConverter(new RangeConverter());
        choiceRange.setItems(rangeObservableList);
        choiceRange.getSelectionModel().select(police.getPolicemanRange());
        choiceRange.setPrefWidth(230);
        hBoxRange.getChildren().remove(1);
        hBoxRange.getChildren().add(1,choiceRange);
        buttonDelete.setVisible(true);
        buttonSave.setVisible(true);
    }
    @FXML
    void modifyRanks(){
        ObservableList<Ranks> ranksObservableList = FXCollections.observableList(Ranks.getRanksList());
        ChoiceBox<Ranks> choiceRanks = new ChoiceBox<>(ranksObservableList);
        choiceRanks.setConverter(new RanksConverter());
        choiceRanks.setItems(ranksObservableList);
        choiceRanks.getSelectionModel().select(ranksObservableList.indexOf(searchRanks(police.getPolicemanRanks())));
        choiceRanks.setPrefWidth(230);
        hBoxRanks.getChildren().remove(1);
        hBoxRanks.getChildren().add(1,choiceRanks);
        buttonDelete.setVisible(true);
        buttonSave.setVisible(true);
    }
    @FXML
    void modifyDepartament(){
        ObservableList<Departament> departamentObservableList = FXCollections.observableList(Departament.getDepartamentList());
        ChoiceBox<Departament> choiceDepartament = new ChoiceBox<>();
        choiceDepartament.setConverter(new DepartamentConverter());
        choiceDepartament.setItems(departamentObservableList);
        choiceDepartament.getSelectionModel().select(police.getPolicemanDepartament());
        choiceDepartament.setPrefWidth(230);
        hBoxDepartament.getChildren().remove(1);
        hBoxDepartament.getChildren().add(1,choiceDepartament);
        buttonDelete.setVisible(true);
        buttonSave.setVisible(true);
    }

    @FXML
    void clickModify(){
    lname.setDisable(false);
    }

    public Ranks searchRanks(Ranks sranks){
        Ranks ranks=null;
        for(Ranks ranksl:Ranks.getRanksList()){
            if (ranksl.equals(sranks)) {
                ranks=ranksl;
            }
        }
        return ranks;
    }

}
