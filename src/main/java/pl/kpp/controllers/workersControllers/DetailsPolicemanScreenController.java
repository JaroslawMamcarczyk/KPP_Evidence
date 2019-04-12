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
import javafx.scene.layout.VBox;
import pl.kpp.workers.Departament;
import pl.kpp.workers.Policeman;
import pl.kpp.workers.Range;
import pl.kpp.workers.Ranks;
import pl.kpp.converters.converters.DepartamentConverter;
import pl.kpp.converters.converters.RangeConverter;
import pl.kpp.converters.converters.RanksConverter;
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




    private Policeman police;

    @FXML
    public void initialize() {
        police = ShowPolicemanScreenController.getEditPoliceman();
        lname.setText(police.getName() + " " + police.getSurrname());
        lid.setText(police.getEwidential());
        lpesel.setText(police.getPesel());
       // ldepartament.setText(police.getNamePoliceDepartament());
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
        //PolicemanDAO policeman = new PolicemanDAO(names[0],names[1],lid.getText(),lpesel.getText(),police.getId(),choiceRange.getValue().getId());
        //PolicemanDAO.updatePoliceman(policeman);
    }

    @FXML
    void clickDelete(ActionEvent event) {
        PolicemanDao.deletePoliceman(police);
    }

    @FXML
    void clickModifyButton(ActionEvent event){
        buttonDelete.setVisible(true);
        buttonSave.setVisible(true);
        buttonModify.setVisible(false);
        ObservableList<Range> rangeObservableList = FXCollections.observableList(Range.getListRange());
        ChoiceBox<Range> choiceRange=new ChoiceBox<>();
        choiceRange.setConverter(new RangeConverter());
        choiceRange.setItems(rangeObservableList);
        vBoxTextField.getChildren().remove(2);
        vBoxTextField.getChildren().remove(2);
        ObservableList<Departament> departamentObservableList = FXCollections.observableList(Departament.getDepartamentList());
        ChoiceBox<Departament> choiceDepartament = new ChoiceBox<>();
        choiceDepartament.setConverter(new DepartamentConverter());
        choiceDepartament.setItems(departamentObservableList);
        choiceDepartament.getSelectionModel().select(police.getPolicemanDepartament());
        vBoxTextField.getChildren().remove(2);
        ObservableList<Ranks> ranksObservableList = FXCollections.observableList(Ranks.getRanksList());
        ChoiceBox<Ranks> choiceRanks = new ChoiceBox<>(ranksObservableList);
        choiceRanks.setConverter(new RanksConverter());
        choiceRanks.setItems(ranksObservableList);
        choiceRanks.getSelectionModel().select(ranksObservableList.indexOf(searchRanks(police.getPolicemanRanks())));
        vBoxTextField.getChildren().add(2,choiceRange);
        vBoxTextField.getChildren().add(3,choiceDepartament);
        vBoxTextField.getChildren().add(4,choiceRanks);
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
