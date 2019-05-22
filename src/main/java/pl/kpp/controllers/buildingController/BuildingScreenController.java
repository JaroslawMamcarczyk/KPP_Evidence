package pl.kpp.controllers.buildingController;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.kpp.building.Building;
import pl.kpp.controllers.MainScreenController;
import pl.kpp.controllers.productControllers.AddProductScreenController;
import pl.kpp.dao.Database;
import pl.kpp.dao.buildingDao.BuildingDao;
import pl.kpp.product.Product;

import java.io.IOException;
import java.util.ArrayList;


public class BuildingScreenController {
    @FXML
    private TabPane tabPaneGeneral;


    public void initialize(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/product/ProductBoxScreen.fxml"));
        MainScreenController.getMainScreenController().createLeft(createVBox());
        Stage newStage;
        try {
            Parent parent = loader.load();
            newStage = new Stage(StageStyle.DECORATED);
            newStage.initModality(Modality.NONE);
            newStage.setTitle("Przydział do pokojów");
            newStage.setScene(new Scene(parent));
       //     newStage.show();
        } catch (IOException e) {
            System.out.println("Nie utworzyłem okna modalnego "+e);
        }
        Database date = new Database();
        BuildingDao.readBuilding(date);
        for (Building building:Building.getBuildingList()) {
            Tab tabBuilding = new Tab(building.getName());
            tabPaneGeneral.getTabs().add(tabBuilding);
            if (Building.getFloorList().size() > 0) {
                TabPane tabPane = new TabPane();
                for (Building floor : Building.getFloorList()) {
                    int maxX = BuildingDao.getMaxPosition(floor.getId())+1;
                    int maxY = BuildingDao.getMaxPositionY(floor.getId())+1;
                    System.out.println(maxX+" "+maxY);
                    if (floor.getParent().equals(building)) {
                        Tab tabFloor = new Tab(floor.getName());
                        tabPane.getTabs().add(tabFloor);
                        tabBuilding.setContent(tabPane);
                        GridPane gridPane = new GridPane();
                        gridPane.setStyle("-fx-border-color: red; -fx-border-width: 2px");
                        tabFloor.setContent(gridPane);
                        for(Building room:Building.getRoomList()){
                            if(room.getParent().equals(floor)){
                                if(room.getGetPositionY()==0) {
                                    ColumnConstraints columnConstraints = new ColumnConstraints();
                                    columnConstraints.setPercentWidth(100 / maxY);
                                    columnConstraints.setFillWidth(true);
                                    gridPane.getColumnConstraints().add(columnConstraints);
                                }
                                if(room.getPositionX()==0) {
                                    RowConstraints rowConstraints = new RowConstraints();
                                    rowConstraints.setPercentHeight(100 / maxX);
                                }
                                AnchorPane anchorPane = new AnchorPane();
                               anchorPane.setStyle("-fx-border-color:black;-fx-border-width: 2px;-fx-background-color: white");
//                                anchorPane.setPrefSize(900/countX,800/countY);
//                                ColumnConstraints columnConstraints = new ColumnConstraints();
//                                columnConstraints.setHgrow(Priority.ALWAYS);
//                                gridPane.getColumnConstraints().add(columnConstraints);
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
                        }System.out.println(gridPane.getColumnConstraints().size());
                        System.out.println(gridPane.getRowConstraints().size());
                    }
                }
            }
        }
    }

    private VBox createVBox(){
        VBox vBoxMenu = new VBox();
        vBoxMenu.setPrefWidth(100);
        vBoxMenu.setPadding(new Insets(10,0,0,10));
        MenuItem menuProduct = new MenuItem("Sprzęt");
//        for(Product product:Product.getProductList()){
////            if(product.getRoomNumber()==null){
////                MenuItem menuItem = new MenuItem(product.getProductName());
////                buttonproduct.getItems().add(menuItem);
////            }
////        }
        ArrayList<String> categoryList = AddProductScreenController.createListProductType();
        for(String string:categoryList){
            MenuButton buttonType = new MenuButton(string);
        }
        Separator separator = new Separator();
        separator.setPrefSize(10,100);
        ImageView logo = new ImageView("/pics/indeks.jpg");
        Button button = new Button("Cancel");
        button.setOnMouseClicked(event->{
            MainScreenController.getMainScreenController().setLeftMenu();
        });
        logo.setFitWidth(150);
        logo.setFitHeight(75);
        vBoxMenu.getChildren().addAll(logo,separator,button);
        return vBoxMenu;
    }
}
