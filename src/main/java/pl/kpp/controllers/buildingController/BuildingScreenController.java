package pl.kpp.controllers.buildingController;

import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import pl.kpp.building.Building;
import pl.kpp.dao.Database;
import pl.kpp.dao.buildingDao.BuildingDao;




public class BuildingScreenController {
    @FXML
    private TabPane tabPaneGeneral;


    public void initialize(){
        Database date = new Database();
        BuildingDao.readBuilding(date);
        int countX =1;
        int countY =1;
        for (Building building:Building.getBuildingList()) {
            Tab tabBuilding = new Tab(building.getName());
            tabPaneGeneral.getTabs().add(tabBuilding);
            if (Building.getFloorList().size() > 0) {
                TabPane tabPane = new TabPane();
                for (Building floor : Building.getFloorList()) {
                    if (floor.getParent().equals(building)) {
                        Tab tabFloor = new Tab(floor.getName());
                        tabPane.getTabs().add(tabFloor);
                        tabBuilding.setContent(tabPane);
                        GridPane gridPane = new GridPane();
                        tabFloor.setContent(gridPane);
                        for(Building room:Building.getRoomList()){
                            if(room.getParent().equals(floor)){
                                countX = BuildingDao.getMaxPosition(room.getParent().getId());
                                countY = BuildingDao.getMaxPositionY(room.getParent().getId());
                                AnchorPane anchorPane = new AnchorPane();
                                anchorPane.setStyle("-fx-border-color:black;-fx-border-width: 2px;-fx-background-color: white");
                                anchorPane.setPrefSize(900/countX,800/countY);
                                ColumnConstraints columnConstraints = new ColumnConstraints();
                                columnConstraints.setHgrow(Priority.ALWAYS);
                                gridPane.getColumnConstraints().add(columnConstraints);
                                VBox vBoxWorkers=new VBox();
                                vBoxWorkers.setSpacing(10);
                                Label label = new Label(room.getName());
                                Label labelWorkers = new Label("Pracownicy:");
                                vBoxWorkers.getChildren().addAll(label,labelWorkers);
                                label.setStyle("-fx-background-color: red");
                                labelWorkers.setStyle("-fx-text-fill: black");
                                anchorPane.getChildren().add(vBoxWorkers);
                                AnchorPane.setTopAnchor(vBoxWorkers,5.0);
                                AnchorPane.setLeftAnchor(vBoxWorkers,5.0);
                                gridPane.add(anchorPane,room.getPositionX(),room.getGetPositionY());


                            }
                        }
                    }
                }
            }
        }
    }

}
