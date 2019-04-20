package pl.kpp.controllers.materials;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.kpp.CreateWindowAlert;
import pl.kpp.converters.materials.EquipmentNameConverter;
import pl.kpp.dao.materialsDao.ArticleInTransactionDao;
import pl.kpp.dao.materialsDao.MaterialsDao;
import pl.kpp.dao.materialsDao.TransactionDao;
import pl.kpp.materials.Materials;

import java.util.ArrayList;
import java.util.List;

public class AddNewTransactionController {
    @FXML
    public VBox boxArticle;
    @FXML
    private VBox boxCount;
    @FXML
    private ChoiceBox<Materials> choiceEquipment;
    @FXML
    public Button cancelButton;

    public List<Integer> listCount = new ArrayList<>();
    private int numberOfChoiceBox = -1;
    private int finalCount;
    private ObservableList<Materials> observableListMaterials = FXCollections.observableArrayList();
    private boolean flagIfCreateNextCheckbox=true;
    private boolean flagIfCreateNextTexfield=true;

    public AddNewTransactionController() {
    }

    private void addListener(ChoiceBox<Materials> gchoice) {
        gchoice.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
            flagIfCreateNextCheckbox=true;
               if(flagIfCreateNextTexfield&& flagIfCreateNextCheckbox) {
                   flagIfCreateNextTexfield=false;
                   TextField secondCount = new TextField();
                   boxCount.getChildren().add(secondCount);
                   this.addListenerTextField(secondCount);
               }
        });
    }

    private void addListenerTextField(TextField gtextField) {
        numberOfChoiceBox++;
        listCount.add(0);
        gtextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
            try {
                finalCount = Integer.parseInt(newValue);
                listCount.set(numberOfChoiceBox, finalCount);
                if(flagIfCreateNextCheckbox) {
                    flagIfCreateNextCheckbox = false;
                    ChoiceBox<Materials> secondChoice = new ChoiceBox<>();
                    secondChoice.setConverter(new EquipmentNameConverter());
                    secondChoice.setItems(observableListMaterials);
                    addListener(secondChoice);
                    boxArticle.getChildren().add(secondChoice);
                    flagIfCreateNextTexfield = true;
                }
            } catch (NumberFormatException e) {
                CreateWindowAlert.CreateWindowAlert("Podana wartość jest błędna");
            }}
        });

    }

    @FXML
    void initialize() {
        cancelButton.setGraphic(new ImageView("Pics/cancel.jpg"));
        choiceEquipment.setConverter(new EquipmentNameConverter());
        Materials.createMaterialsList();
        observableListMaterials = FXCollections.observableList(Materials.getMaterialsList());
        choiceEquipment.setItems(observableListMaterials);
        addListener(choiceEquipment);
    }

    public void clickCancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public boolean SaveNewTransaction(int transactiontype){
        boolean isArticleToSave=false;
        ArticleInTransactionDao article;
        int temporaryNumberOfList = 0;
        Group group = new Group();
        group.getChildren().add(boxArticle);
        Node nodeOut = group.getChildren().get(0);
        for(Node nodeIn:((VBox)nodeOut).getChildren())
            if (nodeIn instanceof ChoiceBox && ((ChoiceBox) nodeIn).getValue() != null) {
                Materials eq = ((ChoiceBox<Materials>) nodeIn).getValue();
                if (listCount.get(temporaryNumberOfList) != 0) {
                    article = new ArticleInTransactionDao(TransactionDao.getLastUsingId() + 1,
                            eq.getId(), listCount.get(temporaryNumberOfList));
                    article.saveArticleInTransaction();
                    if(transactiontype==1)
                    MaterialsDao.changeNumberOfEquipment(MaterialsDao.checkNumberOfEquipment(eq.getId()) - listCount.get(temporaryNumberOfList), eq.getId());
                    else if(transactiontype==2)
                        MaterialsDao.changeNumberOfEquipment(MaterialsDao.checkNumberOfEquipment(eq.getId()) + listCount.get(temporaryNumberOfList), eq.getId());
                    isArticleToSave = true;
                }
                temporaryNumberOfList++;
            }return isArticleToSave;
    }

}
