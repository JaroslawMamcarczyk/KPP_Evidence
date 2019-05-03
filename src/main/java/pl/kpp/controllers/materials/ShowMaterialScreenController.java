package pl.kpp.controllers.materials;



import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import pl.kpp.CreateWindowAlert;
import pl.kpp.dao.materialsDao.MaterialsDao;
import pl.kpp.materials.Materials;
import java.io.IOException;


public class ShowMaterialScreenController {

    @FXML
    private TableColumn<Materials, Integer> lp;
    @FXML
    private TableColumn<Materials, String> name;
    @FXML
    private TableColumn<Materials, Integer> count;
    @FXML
    private TableColumn<Materials, String> type;
    @FXML
    private TableView<Materials> tableEquipment;

    private static BooleanProperty isNewMaterials = new SimpleBooleanProperty(false);
    private ObservableList<Materials> list = FXCollections.observableArrayList();
    private static Materials changeEquip =null;

public static Materials getChangeEquip(){return changeEquip;}
public static void setIsNewMaterials(){isNewMaterials.set(true);}
public static BooleanProperty getIsNewMaterials(){return isNewMaterials;}
    @FXML
    public void initialize() {
        isNewMaterials.addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                createTablematerials();
                isNewMaterials.set(false);
            }
        });
        createTablematerials();
        tableEquipment.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                changeEquip = newValue);
    }

    public void createTablematerials() {
        MaterialsDao.readEquipment();
        list = FXCollections.observableArrayList(Materials.getMaterialsList());
        setTable(list);
    }

    @FXML
    void clickAdd(ActionEvent event) {
        openAddNewMaterialScreen(true);

    }

    public  void openAddNewMaterialScreen(boolean editOrAdd) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/materials/AddMaterialScreen.fxml"));
        try {
            AddMaterialScreenController.setAddOrEdit(editOrAdd);
            Parent parent = loader.load();
            Stage newStageAddEquipment = new Stage(StageStyle.TRANSPARENT);
            newStageAddEquipment.initModality(Modality.WINDOW_MODAL);
            newStageAddEquipment.setTitle("Dodawanie nowego artykułu");
            newStageAddEquipment.setScene(new Scene(parent));
            newStageAddEquipment.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickEdit(ActionEvent event) {
    if(changeEquip!=null) {
        openAddNewMaterialScreen(false);
    }else{
        CreateWindowAlert.createWindowError("Nie wybrałeś materiału do edycji");
    }
    }

    @FXML
    void clickDelete(ActionEvent event) {
        if(changeEquip!=null){
            MaterialsDao.deleteEquipment(changeEquip);
            setIsNewMaterials();
        }

    }

    private void setTable(ObservableList<Materials> glist) {
        tableEquipment.getItems().clear();
        tableEquipment.setItems(glist);
        lp.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        count.setCellValueFactory(new PropertyValueFactory<>("amount"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

}
