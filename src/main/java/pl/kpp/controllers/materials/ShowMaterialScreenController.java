package pl.kpp.controllers.materials;



import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
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
    private Materials changeEquip =null;

public static void setIsNewMaterials(){isNewMaterials.set(true);}
    @FXML
    public void initialize() {
        isNewMaterials.addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                Materials.createMaterialsList();
                isNewMaterials.set(false);
                initialize();
            }
        });
        Materials.createMaterialsList();
         list = FXCollections.observableArrayList(Materials.getMaterialsList());
        setTable(list);
//        list.addListener((ListChangeListener.Change<? extends Materials> newEquip) -> {      //dodanie Listnera na obserwowaną listę
//                    while (newEquip.next()) if (newEquip.wasUpdated()) setTable(list);
//                }
//        );
        tableEquipment.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                changeEquip = newValue);
    }

    @FXML
    void clickAdd(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/materials/AddMaterialScreen.fxml"));
        try {
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
