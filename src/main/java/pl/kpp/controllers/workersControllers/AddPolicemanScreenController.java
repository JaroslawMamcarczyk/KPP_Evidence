package pl.kpp.controllers.workersControllers;



import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import pl.kpp.CreateWindowAlert;
import pl.kpp.controllers.ConfigurationScreenController;
import pl.kpp.controllers.MainScreenController;
import pl.kpp.dao.Database;
import pl.kpp.dao.workersDao.RanksDao;
import pl.kpp.workers.Departament;
import pl.kpp.workers.Range;
import pl.kpp.workers.Ranks;
import pl.kpp.converters.workers.DepartamentConverter;
import pl.kpp.converters.workers.RangeConverter;
import pl.kpp.converters.workers.RanksConverter;
import pl.kpp.dao.workersDao.WorkerDao;


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
    @FXML
    private CheckBox checkBoxIntradok;
    @FXML
    private  CheckBox checkBoxIntranet;
    @FXML
    private CheckBox checkBoxExchange;
    @FXML
    private CheckBox checkBoxLotus;
    @FXML
    private CheckBox checkBoxSWD;
    @FXML
    private CheckBox checkBoxCryptomail;
    private WorkerDao policeman=null;
    private BooleanProperty isNewRanks = new SimpleBooleanProperty(false);



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
        isNewRanks.addListener((observable, oldValue, newValue) -> {
            if(newValue){
                Database date = new Database();
                RanksDao.readRanks(date);
                int i=choiceDepartament.getSelectionModel().getSelectedIndex();
                choiceDepartament.getSelectionModel().clearSelection();
                choiceDepartament.getSelectionModel().select(i);
                isNewRanks.set(false);
            }
        });

    }

    @FXML
    void clickSave(ActionEvent event) {
        boolean isOK = true;
        if (lewidential.getText().length() != 6) {
            isOK = false;
            lewidential.setStyle("-fx-background-color: red");
        }
        if (lpesel.getText().length() != 11) {
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
        if (isOK) {
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
            policeman = new WorkerDao(laddName.getText(),lsurrname.getText(),lewidential.getText(),lpesel.getText(),rangeToSave,departamentToSave,ranksToSave,0,0,0,0,0,0);
            if(checkBoxIntradok.isSelected())policeman.setDaoIntradok(1);
            if(checkBoxIntranet.isSelected())policeman.setDaoIntranet(1);
            if(checkBoxExchange.isSelected())policeman.setDaoExchange(1);
            if(checkBoxCryptomail.isSelected())policeman.setDaoCryptomail(1);
            if(checkBoxLotus.isSelected())policeman.setDaoLotus(1);
            if(checkBoxSWD.isSelected())policeman.setDaoSWD(1);
            policeman.savePoliceman();
                WorkerDao.isChangeOnDatabaseProperty().setValue(true);
                CreateWindowAlert.createWindowConfirmation("Dodano Nowego Pracownika");
           MainScreenController.getMainScreenController().createCenter("/FXML/policeman/ShowPolicemanScreen.fxml");
            }else CreateWindowAlert.createWindowError("Błąd dodawania nowego policjanta - popraw pola świecące na czerowno");
    }

    public void clickAddRanks(){
        ConfigurationScreenController.createNewRanks(choiceDepartament.getSelectionModel().getSelectedItem());
        isNewRanks.set(true);
    }
}
