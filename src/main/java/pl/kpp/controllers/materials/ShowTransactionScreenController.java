package pl.kpp.controllers.materials;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.kpp.HandlingPdfFiles;
import pl.kpp.dao.materialsDao.DeliverysDao;
import pl.kpp.dao.materialsDao.TransactionDao;
import pl.kpp.materials.ArticleInTransaction;
import pl.kpp.materials.Deliverys;
import pl.kpp.materials.Materials;
import pl.kpp.materials.Transaction;
import pl.kpp.workers.Policeman;
import java.io.IOException;
import java.util.Date;

public class ShowTransactionScreenController {
    @FXML
    private TableColumn<Transaction, Date> columnDataTransaction;
    @FXML
    private TableColumn<Policeman, String> columnNumberTransaction;
    @FXML
    private TableView<Transaction> tableTransaction;
    @FXML
    private TableColumn<Transaction, String> columnCustomerTransaction;
    @FXML
    private TableView<ArticleInTransaction> tableMaterials;
    @FXML
    private TableColumn<ArticleInTransaction, String> columnMaterials;
    @FXML
    private TableColumn<ArticleInTransaction, Integer> columnCount;

    private ObservableList<Transaction> transactionInObservableList = FXCollections.observableArrayList();
    private ObservableList<Transaction> transactionOutObservableList = FXCollections.observableArrayList();
    private ObservableList<Transaction> transactionObservableList = FXCollections.observableArrayList();
    private ObservableList<ArticleInTransaction> articleInTransactionsObservableList = FXCollections.observableArrayList();
    private Stage newStageAddGetOut;
    private static BooleanProperty isNewTransaction = new SimpleBooleanProperty(false);
    private Transaction selectedTransaction;

    public static void setIsNewTransaction() {
        ShowTransactionScreenController.isNewTransaction.set(true);
    }

    @FXML
    void  initialize() {
        Materials.createMaterialsList();
        Transaction.createTransactionList();
        ArticleInTransaction.CreateArticleIntransactionList();
        DeliverysDao.readDeliverys();
        Deliverys.createDeliverysList();
        createObservableList();
        clickShowAll();
        addListenerToTable();
        isNewTransaction.addListener((Observable, oldValue, newValue)->{
            if (newValue){
                isNewTransaction.set(false);
                TransactionDao.readTransaction();
                createObservableList();
                ArticleInTransaction.CreateArticleIntransactionList();
                clickShowAll();
            }
        });
    }

    private void setTable(ObservableList<Transaction> transactionObservableList) {
        tableTransaction.setItems(transactionObservableList);
        columnNumberTransaction.setCellValueFactory(new PropertyValueFactory<>("nameTransaction"));
        columnDataTransaction.setCellValueFactory(new PropertyValueFactory<>("dateTransaction"));
        columnCustomerTransaction.setCellValueFactory(new PropertyValueFactory<>("customerName"));
    }

    private void addListenerToTable() {
        tableTransaction.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                    articleInTransactionsObservableList.clear();
                    for (ArticleInTransaction article:ArticleInTransaction.getArticleInTransactionList()){
                        if(article.getArticleInTransactionTransaction().getIdTransaction()==newValue.getIdTransaction())
                        articleInTransactionsObservableList.add(article);
                    }
                    tableMaterials.setItems(articleInTransactionsObservableList);
                    columnMaterials.setCellValueFactory(new PropertyValueFactory<>("nameArticleInTransaction"));
                    columnCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        }}
        );
    }

    public void clickAddGetIn() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/materials/AddNewGetIn.fxml"));
        try {
            Parent parent = loader.load();
            newStageAddGetOut = new Stage(StageStyle.TRANSPARENT);
            newStageAddGetOut.initModality(Modality.WINDOW_MODAL);
            newStageAddGetOut.setTitle("Dodawanie nowego przyjęcia");
            newStageAddGetOut.setScene(new Scene(parent));
            newStageAddGetOut.show();
        } catch (IOException e) {
            System.out.println("Nie utworzyłem okna modalnego "+e);
        }
    }


    public void clickAddGetOut() {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/materials/AddNewGetOut.fxml"));
        try {
            Parent parent = loader.load();
            newStageAddGetOut = new Stage(StageStyle.TRANSPARENT);
            newStageAddGetOut.initModality(Modality.WINDOW_MODAL);
            newStageAddGetOut.setTitle("Dodawanie nowego Wydania");
            newStageAddGetOut.setScene(new Scene(parent));
            newStageAddGetOut.show();
        } catch (IOException e) {
            System.out.println("Nie utworzyłem okna modalnego "+e);
        }
    }

    public void clickGeneratePdf(){
        HandlingPdfFiles pdfFiles = new HandlingPdfFiles();
      if(pdfFiles.print(selectedTransaction,articleInTransactionsObservableList)==1)
      pdfFiles.showPdfFile();
    }

    public void clickShowAll(){
        tableMaterials.setItems(null);
        setTable(transactionObservableList);
    }

    public void clickShowIn(){
        tableMaterials.setItems(null);
        setTable(transactionInObservableList);
    }

    public void ClickShowOut(){
        tableMaterials.setItems(null);
        setTable(transactionOutObservableList);
    }

    private void createObservableList() {
        if (transactionInObservableList.size() != 0)
            transactionInObservableList.clear();
        if (transactionOutObservableList.size() != 0)
            transactionOutObservableList.clear();
        if (transactionObservableList.size() != 0)
            transactionObservableList.clear();
        Transaction.createTransactionList();
        transactionObservableList =FXCollections.observableArrayList(Transaction.getTransactionList());
        for (Transaction transaction : Transaction.getTransactionList()) {
            if (transaction.getType() == 1)
                transactionOutObservableList.add(transaction);
            else if (transaction.getType() == 2)
                transactionInObservableList.add(transaction);
        }
    }
}
