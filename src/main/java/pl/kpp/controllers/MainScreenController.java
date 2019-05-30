package pl.kpp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class MainScreenController {

    @FXML
    private BorderPane general;
    @FXML
    AnchorPane anchorGeneral;
    @FXML
    VBox vBoxMenu;
    private static Node pane = null;
    public static Node getPane(){return  pane;}
    private static MainScreenController mainScreenController;

    public static MainScreenController getMainScreenController(){ return mainScreenController;}

    @FXML
    void clickShowPoliceman(ActionEvent event) {
        createCenter("/FXML/workers/ShowPolicemanScreen.fxml");
    }
    @FXML
    void clickConfiguration(ActionEvent event) {
       createCenter("/FXML/configuration/ConfigurationScreen.fxml");
    }
   public void clickMaterials(ActionEvent event){
        createCenter("/FXML/materials/ShowMaterialsScreen.fxml");
    }
    @FXML
    void clickAddPoliceman(ActionEvent event){
        createCenter("/FXML/workers/AddPolicemanScreen.fxml");
    }
    @FXML
    void clickShowTransaction(ActionEvent event) {
        createCenter("/FXML/materials/ShowTransactionScreen.fxml");
    }
    @FXML
   void clickListKryptomail(){createCenter("/FXML/WorksScreen.fxml");}
   @FXML
    void clickShowProduct(){createCenter("/FXML/product/ProductScreen.fxml");}
    @FXML
    void clickCards(){createCenter("/FXML/product/CardsScreen.fxml");}
    @FXML
    void clickShowBuilding(){createCenter("/FXML/building/BuildingScreen.fxml");}
    @FXML
    void clickShowComputers(){createCenter("/FXML/product/ComputersScreen.fxml");}
   @FXML
   void clickAddProduct(){createCenter("/FXML/product/AddProductScreen.fxml"); }
public void initialize(){
        mainScreenController = this;
}
    public void createCenter(String path) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(path));
        try {
            pane = loader.load();
        } catch (IOException e) {
            System.out.println("Nie stworzyłem okna" + e);
        }
        anchorGeneral.getChildren().clear();
        anchorGeneral.getChildren().add(pane);
        anchorGeneral.setTopAnchor(pane,0.0);
        anchorGeneral.setBottomAnchor(pane,0.0);
        anchorGeneral.setLeftAnchor(pane,0.0);
        anchorGeneral.setRightAnchor(pane,0.0);
    }

    public void createLeft(Node node){
        general.setLeft(node);
    }

    public void setLeftMenu(){ general.setLeft(vBoxMenu);}
}


