package pl.kpp.controllers.materials;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pl.kpp.converters.materials.MaterialConverter;
import pl.kpp.dao.materialsDao.MaterialsDao;
import pl.kpp.materials.Materials;

public class AddMaterialScreenController {

    @FXML
    private Label lcount;
    @FXML
    private Label lnewType;
    @FXML
    private TextField lname;
    @FXML
    private TextField lfieldNewType;
    @FXML
    private TextField lnewCount;
    @FXML
    private Button cancel;
    @FXML
    private CheckBox checkNewValue;
    @FXML
    private CheckBox checkNewType;
    @FXML
    private ChoiceBox<Materials> choiceTyp;


    /**
     * Method saving new materials to database
     * @param e
     */
    public void clickSaveEquipment(ActionEvent e){
        int count=0;
        if(lnewCount.getText().equals("")) count = 0; else {
            try{
                count = Integer.parseInt(lnewCount.getText());
            }catch(NumberFormatException expresion){
                System.out.println("nie numer");
            }
        }
        if(!lfieldNewType.getText().equals("")) {
           MaterialsDao dao = new MaterialsDao(lname.getText(), count, lfieldNewType.getText());
            dao.saveEquipment();
            ShowMaterialScreenController.setIsNewMaterials();
            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        } else {
            if (lfieldNewType.getText().equals("") && !choiceTyp.getValue().equals("")){
                MaterialsDao dao = new MaterialsDao(lname.getText(), count, choiceTyp.getValue().getType());
                dao.saveEquipment();
                ShowMaterialScreenController.setIsNewMaterials();
                Stage stage = (Stage) cancel.getScene().getWindow();
                stage.close();
            }else{
                System.out.println("Coś nie poszło tak, sprawdź wszytskie pola");
            }
        }

    }

    /**
     * The method  handling button cancel
     * @param event
     */

    public void clickCancel(ActionEvent event){
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void check(ActionEvent event) {
        if (checkNewValue.isSelected()) {
            lnewCount.setVisible(true);
            lcount.setVisible(true);
        }else{
            lnewCount.setVisible(false);
            lcount.setVisible(false);

        }
    }
    @FXML
    void checkNewType(ActionEvent event){
        if(checkNewType.isSelected()){
            lfieldNewType.setVisible(true);
            lnewType.setVisible(true);
            choiceTyp.setValue(null);
            choiceTyp.setDisable(true);
        }else{
            lfieldNewType.setVisible(false);
            lnewType.setVisible(false);choiceTyp.setDisable(false);
        }
    }

    @FXML
    public void initialize(){
        lnewCount.setVisible(false);
        lcount.setVisible(false);
        lfieldNewType.setVisible(false);
        lnewType.setVisible(false);
        ObservableList<Materials> listMaterials = FXCollections.observableArrayList();
        listMaterials.addAll(Materials.getMaterialsList());
        for(int i=0;i<listMaterials.size()-1;i++){
            for(int j=i+1;j<listMaterials.size();j++){
          if(listMaterials.get(i).getType().equals(listMaterials.get(j).getType())){
              listMaterials.remove(j);
              j--;
          }}
        }
    choiceTyp.setConverter(new MaterialConverter());
    choiceTyp.setItems(listMaterials);
    }

}
