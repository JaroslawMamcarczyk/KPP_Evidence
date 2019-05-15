package pl.kpp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pl.kpp.dao.WorksDao;
import java.io.IOException;


public class MainScreenController {

    @FXML
    private BorderPane general;
    @FXML
    private VBox VBoxJobList;
    @FXML
    private TextArea textAreaNewJob;

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
   void clickAddJob(){
        WorksDao worksDao = new WorksDao(textAreaNewJob.getText());
        worksDao.saveWorks();
   }
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
        general.setCenter(pane);
    }

}


