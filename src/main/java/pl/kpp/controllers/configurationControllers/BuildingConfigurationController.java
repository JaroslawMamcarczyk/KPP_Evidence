package pl.kpp.controllers.configurationControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.kpp.building.Building;
import pl.kpp.converters.building.BuildingConverter;
import pl.kpp.dao.Database;
import pl.kpp.dao.buildingDao.BuildingDao;

public class BuildingConfigurationController {
    @FXML
    private ChoiceBox<Building> choiceFloor;
    @FXML
    private ChoiceBox<Building> choiceBuilding;
    @FXML
    private TextField textFieldNewBuilding;
    @FXML
    private TextField textFieldNewRoom;
    @FXML
    private TextField textFieldNewFloor;
    @FXML
    private HBox hBoxFloor;
    @FXML
    private HBox hBoxRoom;
    @FXML
    private HBox hBoxBuilding;
    private Building chosenbuilding = null;

    @FXML
    public void initialize(){
        Database date = new Database();
        BuildingDao.readBuilding(date);
        ObservableList<Building> buildingObservableList = FXCollections.observableList(Building.getBuildingList());
        choiceBuilding.setItems(buildingObservableList);
        choiceBuilding.setConverter(new BuildingConverter());
        choiceBuilding.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            hBoxBuilding.setVisible(true);
            hBoxFloor.setVisible(true);
            chosenbuilding = newValue;
            ObservableList<Building>floorObservableList= FXCollections.observableArrayList();
            for(Building building:Building.getFloorList()){
                  if(building.getParent().getId()==chosenbuilding.getId())
                floorObservableList.add(building);
            }
            choiceFloor.setItems(floorObservableList);
            choiceFloor.setConverter(new BuildingConverter());
            choiceFloor.getSelectionModel().selectedItemProperty().addListener((observable2, oldValue2, newValue2)->{
                hBoxRoom.setVisible(true);
            });
        });
    }

    @FXML
    void clickNewBuilding() {
        if (textFieldNewBuilding.getText() != null) {
            BuildingDao buildingDao = new BuildingDao(textFieldNewBuilding.getText(),1,0);
            buildingDao.saveBuilding();
        }
    }
    @FXML
    void clickNewFloor() {
        if(textFieldNewFloor.getText()!=" "&& chosenbuilding!=null){
            BuildingDao buildingDao = new BuildingDao(textFieldNewFloor.getText(),2,chosenbuilding.getId());
            buildingDao.saveBuilding();
            Building.getBuildingList().add(new Building(buildingDao));
        }
    }

    @FXML
    void clickNewRoom() {

    }
}
