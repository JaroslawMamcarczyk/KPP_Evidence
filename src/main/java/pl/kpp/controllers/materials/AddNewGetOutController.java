package pl.kpp.controllers.materials;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import pl.kpp.CreateWindowAlert;
import pl.kpp.HandlingPdfFiles;
import pl.kpp.converters.workers.PolicemanConverter;
import pl.kpp.dao.materialsDao.TransactionDao;
import pl.kpp.materials.Transaction;
import pl.kpp.workers.Worker;

import java.time.LocalDate;



public class AddNewGetOutController extends AddNewTransactionController{

    @FXML
    private ChoiceBox<Worker> choiceEmployer;
    @FXML
    private CheckBox checkHelples;
    @FXML
    private CheckBox checkCriminal;
    @FXML
    private CheckBox checkPrewent;
    @FXML
    private CheckBox checkCar;




    private ObservableList<Worker> observableListWorker = FXCollections.observableArrayList();

    @FXML
    void initialize (){
        super.initialize();
        choiceEmployer.setConverter(new PolicemanConverter());
        observableListWorker = FXCollections.observableList(Worker.getWorekrList());
        choiceEmployer.setItems(observableListWorker);
    }

    @FXML
    void clickSave(ActionEvent e){
        if(choiceEmployer.getValue()!=null) {
            if (SaveNewTransaction(1)!=0) {
                Worker police = choiceEmployer.getValue();
                String numberOfTransaction = (Transaction.getTransactionList().size() + 1) + "/" + LocalDate.now().getYear();
                TransactionDao transaction = new TransactionDao(police.getId(), numberOfTransaction);
                transaction.saveTransaction(1);
                HandlingPdfFiles pdfFiles = new HandlingPdfFiles();
                Transaction transactionToPdf = new Transaction(TransactionDao.getLastSavedTransaction());
                pdfFiles.showPdfFile(pdfFiles.print(transactionToPdf,getArticleInTransactionObservableList()));
            }
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }else
        {
            CreateWindowAlert.CreateWindowConfirmation("Nie wybrałeś policjanta");
        }
}}