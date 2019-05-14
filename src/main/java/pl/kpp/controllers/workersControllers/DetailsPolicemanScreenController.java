package pl.kpp.controllers.workersControllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import pl.kpp.CreateWindowAlert;
import pl.kpp.controllers.MainScreenController;
import pl.kpp.dao.workersDao.WorkerDao;
import pl.kpp.workers.Departament;
import pl.kpp.workers.Worker;
import pl.kpp.workers.Range;
import pl.kpp.workers.Ranks;
import pl.kpp.converters.workers.DepartamentConverter;
import pl.kpp.converters.workers.RangeConverter;
import pl.kpp.converters.workers.RanksConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private TextField lranks;
    @FXML
    private TextField ltelephone;
    @FXML
    private HBox hBoxRange;
    @FXML
    private HBox hBoxDepartament;
    @FXML
    private HBox hBoxRanks;
    @FXML
    VBox vBoxHaving;

    private Worker police;
    private Range newPolicemanRange = null;
    private Ranks newWorkerRanks = null;
    private Departament newPolicemanDepartament = null;
    private List<CheckBox> checkBoxList = new ArrayList<>();
    private CheckBox checkBoxExchange;
    private CheckBox checkBoxCryptomail;
    private CheckBox checkBoxLotus;
    private CheckBox checkBoxIntradok;
    private CheckBox checkBoxIntranet;
    private  ObservableList<Ranks> ranksObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        police = ShowPolicemanScreenController.getEditWorker();
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
        String policemanAdres = deletePolishChar(police.getName()+"."+police.getSurrname());
        if(police.getPolicemanIntranet()==1) createLabel(policemanAdres, "intranet");
                else {
                    checkBoxIntranet = new CheckBox("Intranet");
                    checkBoxList.add(checkBoxIntranet);
        }
        if(police.getPolicemanIntradok()==1) createLabel(policemanAdres, "intradok");
        else {
            checkBoxIntradok = new CheckBox("Intradok");
            checkBoxList.add(checkBoxIntradok);
        }
        if(police.getPolicemanLotus()==1) createLabel(policemanAdres+"@kk", "lotus");
        else {
             checkBoxLotus = new CheckBox("Lotus");
            checkBoxList.add(checkBoxLotus);
        }
        if(police.getPolicemanCryptomail()==1) createLabel(policemanAdres+"@","cryptomail");
        else {
            checkBoxCryptomail = new CheckBox("Kryptomail");
            checkBoxList.add(checkBoxCryptomail);
        }
        if(police.getPolicemanExchange()==1)createLabel(policemanAdres+"@","exchange");
        else {
             checkBoxExchange = new CheckBox("Exchange");
            checkBoxList.add(checkBoxExchange);
        }
        ranksObservableList = createRanksObservableList(police.getPolicemanDepartament().getId());
        }

    @FXML
    void clickSave(ActionEvent event) {
        if(!lname.isDisable()) {
            String[] names = lname.getText().split(" ");
            if(!names[0].equals(police.getName())){
                WorkerDao.updateWorkerString("worker_name",names[0],police.getId());
            }
            if(!names[1].equals(police.getSurrname())){
                WorkerDao.updateWorkerString("worker_surname",names[1],police.getId());
            }
        }
        if(!lpesel.isDisable()){
            if(!lpesel.getText().equals(police.getPesel())){
                if(lpesel.getText().length() == 11)
                WorkerDao.updateWorkerString("worker_pesel",lpesel.getText(),police.getId());
                else
                    lpesel.setStyle("-fx-background-color: red");
            }
        }
        if(!lid.isDisable()){
            if(!lid.getText().equals(police.getEwidential())){
                if (lid.getText().length() == 6)
                WorkerDao.updateWorkerString("worker_evidential",lid.getText(),police.getId());
                else
                    lid.setStyle("-fx-background-color: red");
            }
        }
        if(newPolicemanRange!=null){
            WorkerDao.updateWorkerInt("worker_range",newPolicemanRange.getId(),police.getId());
        }
        if(newPolicemanDepartament!=null){
            WorkerDao.updateWorkerInt("worker_departament",newPolicemanDepartament.getId(),police.getId());
        }
        if(newWorkerRanks!=null){
            WorkerDao.updateWorkerInt("worker_ranks", newWorkerRanks.getRanksId(),police.getId());
        }
        MainScreenController.getMainScreenController().createCenter("/FXML/workers/ShowPolicemanScreen.fxml");
    }

    @FXML
    void clickCancel(ActionEvent event) {
        hBoxRange.getChildren().remove(1);
        hBoxRange.getChildren().add(1,lrange);
        hBoxRanks.getChildren().remove(1);
        hBoxRanks.getChildren().add(1,lranks);
        hBoxDepartament.getChildren().remove(1);
        hBoxDepartament.getChildren().add(1,ldepartament);
        newPolicemanRange = null;
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
        choiceRange.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            newPolicemanRange=newValue;
        });
    }
    @FXML
    void modifyRanks(){
        ChoiceBox<Ranks> choiceRanks = new ChoiceBox<>();
        choiceRanks.setConverter(new RanksConverter());
        choiceRanks.setItems(ranksObservableList);
        choiceRanks.getSelectionModel().select(police.getPolicemanRanks());
        choiceRanks.setPrefWidth(230);
        hBoxRanks.getChildren().remove(1);
        hBoxRanks.getChildren().add(1,choiceRanks);
        buttonDelete.setVisible(true);
        buttonSave.setVisible(true);
        choiceRanks.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            newWorkerRanks =newValue;
        });
    }

    private ObservableList<Ranks> createRanksObservableList(int idRanks) {
        ranksObservableList.clear();
        for(Ranks ranks:Ranks.getRanksList()){
            if(ranks.getDepartamentRanks().getId()==idRanks){
                ranksObservableList.add(ranks);
            }
        }
        return ranksObservableList;
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
        choiceDepartament.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            newPolicemanDepartament = newValue;
            createRanksObservableList(newPolicemanDepartament.getId());
            modifyRanks();
        }));
    }

    @FXML
    void clickModify(){
    lname.setDisable(false);
    buttonDelete.setVisible(true);
    buttonSave.setVisible(true);
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

    private void createLabel(String text, String name){
        Label label = new Label(text);
        label.setStyle("-fx-border-style: solid;-fx-border-color: #000000;-fx-border-width: 2px; -fx-background-color: #63B8EE");
        label.setMinSize(300,40);
        label.setAlignment(Pos.CENTER);
        Label label1 = new Label(name+":    ");
        label1.setMinSize(150,40);
        label1.setTextAlignment(TextAlignment.CENTER);
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(0,10,10,10));
        hBox.setSpacing(5);
        Button deleteButton = new Button("Usuń");
        deleteButton.setOnMouseClicked(event -> {
            Optional<ButtonType> result = CreateWindowAlert.createWindowConfirmation("Czy na pewno chcesz usunąć dostęp do: "+name);
            if (result.get()== ButtonType.OK) {
                WorkerDao.updateWorkerInt(name, 0, police.getId());
                label.setText("usunięto");
                label.setStyle("-fx-background-color: red");
            }
        });
        hBox.getChildren().addAll(label1,label,deleteButton);
        vBoxHaving.getChildren().add(hBox);
    }
    private String deletePolishChar(String textToModify){
        String result = textToModify;
        String[] tablePolischChar = new String[]{"ą","ę","ć","ł","ń","ó","ś","ż","ź"} ;
        String [] tableNonPolischchar = new String[]{"a","e","SS","l","n","o","s","z","z"};
        for(int i=0;i<9;i++){
            result = result.toLowerCase().replace(tablePolischChar[i],tableNonPolischchar[i]);
        }
    return result;
}
@FXML
 void clickAdd(){
        vBoxHaving.getChildren().addAll(checkBoxList);
        Button saveButton = new Button("Zapisz");
        saveButton.setOnMouseClicked(event -> {
            if(checkBoxIntranet!=null && checkBoxIntranet.isSelected()) {
                WorkerDao.updateWorkerInt("intranet",1,police.getId());
                changeNodeInVBox(checkBoxIntranet, "Intranet dodano");
            }
            if(checkBoxIntradok!= null && checkBoxIntradok.isSelected()){
                WorkerDao.updateWorkerInt("intradok",1,police.getId());
                changeNodeInVBox(checkBoxIntradok, "Intradok dodano");
            }
            if(checkBoxExchange!=null && checkBoxExchange.isSelected()){
                WorkerDao.updateWorkerInt("exchange",1,police.getId());
                changeNodeInVBox(checkBoxExchange, "Exchange dodano");
            }
            if(checkBoxLotus!= null && checkBoxLotus.isSelected()){
                WorkerDao.updateWorkerInt("lotus",1,police.getId());
                changeNodeInVBox(checkBoxIntranet, "Lotus dodano");
            }
            if(checkBoxCryptomail!=null && checkBoxCryptomail.isSelected()){
                WorkerDao.updateWorkerInt("cryptomail",1,police.getId());
                changeNodeInVBox(checkBoxCryptomail,"Kryptomail dodano");
            }
        });
        vBoxHaving.getChildren().add(saveButton);
}

    private void changeNodeInVBox(CheckBox checkBox, String s) {
        int pos = vBoxHaving.getChildren().indexOf(checkBox);
        vBoxHaving.getChildren().remove(pos);
        Label label = new Label(s);
        vBoxHaving.getChildren().add(pos, label);
    }
}

