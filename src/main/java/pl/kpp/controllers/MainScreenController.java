package pl.kpp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ResourceBundle;


public class MainScreenController {

    @FXML
    private BorderPane general;
    @FXML
    private ToggleButton showPoliceman;

    private Node pane = null;

    @FXML
    void clickShowPoliceman(ActionEvent event) {
        ResourceBundle boundle = ResourceBundle.getBundle("Bundle/strings");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/ShowPolicemanScreen.fxml"), boundle);
        try {
            pane = loader.load();
        //    ShowPolicemanScreenController controller = loader.getController();
        //    controller.setMainScreenController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        general.setCenter(pane);
    }

    public void setCentralPanel(Node pane){
        general.setCenter(pane);
    }

    @FXML
    void clickConfiguration(ActionEvent event) {
        AnchorPane pane =null;
    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/ConfigurationScreen.fxml"));
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        general.setCenter(pane);
    }

    public void clickMaterials(ActionEvent event){
        createCenter("/FXML/ShowMaterialsScreen.fxml");
    }

    @FXML
    void clickAddPoliceman(ActionEvent event){
            ResourceBundle boundle = ResourceBundle.getBundle("Bundle/strings");
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/FXML/AddPolicemanScreen.fxml"), boundle);
            try{
                pane = loader.load();
            }catch (IOException e) {
                System.out.println("błąd tworzenia okna dodawania policjanta" + e);
            }
            general.setCenter(pane);
    }

    @FXML
    void clickShowTransaction(ActionEvent event) {
        createCenter("/FXML/ShowTransactionScreen.fxml");
    }

    private void createCenter(String s) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(s));
        try {
            pane = loader.load();
        } catch (IOException e) {
            System.out.println("Nie stworzyłem okna" + e);
        }
        general.setCenter(pane);
    }

}


