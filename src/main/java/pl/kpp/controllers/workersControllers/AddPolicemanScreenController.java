package pl.kpp.controllers.workersControllers;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import pl.kpp.CreateWindowAlert;
import pl.kpp.controllers.MainScreenController;
import pl.kpp.workers.Departament;
import pl.kpp.workers.Range;
import pl.kpp.workers.Ranks;
import pl.kpp.converters.workers.DepartamentConverter;
import pl.kpp.converters.workers.RangeConverter;
import pl.kpp.converters.workers.RanksConverter;
import pl.kpp.dao.workersDao.PolicemanDao;


public class AddPolicemanScreenController {
    @FXML
    private TextField laddName;
    @FXML
    private TextField lewidential;
    @FXML
    private TextField lsurrname;
    @FXML
    private TextField lpesel;
    @FXML
    private ChoiceBox<Range> choiceRange;
    @FXML
    private ChoiceBox<Ranks> choiceRanks;
    @FXML
    private ChoiceBox<Departament> choiceDepartament;
    private PolicemanDao policeman=null;



    @FXML
    void initialize(){
        ObservableList<Range> rangeObservableList = FXCollections.observableArrayList(Range.getListRange());
        choiceRange.setConverter(new RangeConverter());
        choiceRange.setItems(rangeObservableList);
        choiceDepartament.setConverter(new DepartamentConverter());
        ObservableList<Departament> departamentObservableList= FXCollections.observableList(Departament.getDepartamentList());
        choiceDepartament.setItems(departamentObservableList);
        choiceDepartament.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            choiceRanks.setConverter(new RanksConverter());
            ObservableList<Ranks> ranksObservableList = FXCollections.observableArrayList();
            for(Ranks ranks:Ranks.getRanksList()){
                if(ranks.getDepartamentRanks().getId()==newValue.getId()){
                    ranksObservableList.add(ranks);
                }
            }
            choiceRanks.setItems(ranksObservableList);
        });

    }
    @FXML
    void clickSave(ActionEvent event) {
        boolean isOK = true;
        if (lewidential.getText().length() != 6 || lewidential.getText().isEmpty()) {
            isOK = false;
            lewidential.setStyle("-fx-background-color: red");
        }
        if (lpesel.getText().length() != 11 || lpesel.getText().isEmpty()) {
            isOK = false;
            lpesel.setStyle("-fx-background-color: red");
        }
        if (laddName.getText().isEmpty()) {
            isOK = false;
            laddName.setStyle("-fx-background-color: red");
        }
        if (lsurrname.getText().isEmpty()) {
            isOK = false;
            lsurrname.setStyle("-fx-background-color: red");
        }
        if (isOK == true) {
            int rangeToSave=0;
            int ranksToSave=0;
            int departamentToSave=0;
            if(choiceRange.getValue()!=null){
              rangeToSave=choiceRange.getValue().getId();
            }
            if(choiceRanks.getValue()!=null){
                ranksToSave=choiceRanks.getValue().getRanksId();
            }
            if(choiceDepartament.getValue()!=null){
                departamentToSave=choiceDepartament.getValue().getId();
            }
            policeman = new PolicemanDao(laddName.getText(),lsurrname.getText(),lewidential.getText(),lpesel.getText(),rangeToSave,departamentToSave,ranksToSave);
                policeman.savePoliceman();
                PolicemanDao.isChangeOnDatabaseProperty().setValue(true);
                CreateWindowAlert.CreateWindowAlert("Dodano Nowego Policjanta");
           MainScreenController.getMainScreenController().createCenter("/FXML/policeman/ShowPolicemanScreen.fxml");
            }else CreateWindowAlert.createWindowError("Błąd dodawania nowego policjanta - popraw pola świecące na czerowno");
    }
}
