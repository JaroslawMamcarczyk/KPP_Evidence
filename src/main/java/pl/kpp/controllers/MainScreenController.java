package pl.kpp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import java.io.IOException;


public class MainScreenController {

    @FXML
    private BorderPane general;

    private Node pane = null;
    private static MainScreenController mainScreenController;

    public static MainScreenController getMainScreenController(){ return mainScreenController;}

    @FXML
    void clickShowPoliceman(ActionEvent event) {
        createCenter("/FXML/policeman/ShowPolicemanScreen.fxml");
    }
    @FXML
    void clickConfiguration(ActionEvent event) {
       createCenter("/FXML/ConfigurationScreen.fxml");
    }
   public void clickMaterials(ActionEvent event){
        createCenter("/FXML/materials/ShowMaterialsScreen.fxml");
    }
    @FXML
    void clickAddPoliceman(ActionEvent event){
        createCenter("/FXML/policeman/AddPolicemanScreen.fxml");
    }
    @FXML
    void clickShowTransaction(ActionEvent event) {
        createCenter("/FXML/materials/ShowTransactionScreen.fxml");
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


