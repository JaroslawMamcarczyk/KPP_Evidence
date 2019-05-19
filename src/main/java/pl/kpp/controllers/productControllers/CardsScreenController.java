package pl.kpp.controllers.productControllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pl.kpp.dao.Database;
import pl.kpp.dao.productDao.CardsDao;
import pl.kpp.product.Cards;
import pl.kpp.workers.Worker;

import java.io.IOException;

public class CardsScreenController {
    @FXML
    private TableView<Cards> tableViewCards;

    @FXML
    private TableColumn<Cards, Integer> columnLp;

    @FXML
    private TableColumn<Cards, String> columnNumber;

    @FXML
    private TableColumn<Cards, Worker> columnUser;

    private Stage newStageAddCard;
    private static BooleanProperty isNewCard = new SimpleBooleanProperty(false);

    public static void setIsNewCard(){isNewCard.set(true);}

    @FXML
    void clickAddCard(ActionEvent event) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/product/AddCardScreen.fxml"));
            try {
                Parent parent = loader.load();
                newStageAddCard = new Stage(StageStyle.TRANSPARENT);
                newStageAddCard.initModality(Modality.WINDOW_MODAL);
                newStageAddCard.setTitle("Dodawanie nowej karty");
                newStageAddCard.setScene(new Scene(parent));
                newStageAddCard.show();
            } catch (IOException e) {
                System.out.println("Nie utworzyłem okna modalnego "+e);
            }
    }

    @FXML
    void clickEditCard(ActionEvent event) {

    }

    @FXML
    public void initialize(){
        setTable(createObservableList());
        isNewCard.addListener(observable -> {
            if(isNewCard.get()){
                isNewCard.set(false);
                tableViewCards.getItems().clear();
                setTable(createObservableList());
            }
        });

    }

    public ObservableList<Cards> createObservableList() {
        Database date = new Database();
        CardsDao.readCards(date);
        return FXCollections.observableList(Cards.getCardsList());
    }

    private void setTable(ObservableList<Cards> glist) {
        tableViewCards.setItems(glist);
        columnLp.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNumber.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
        columnUser.setCellFactory(col->{
        return new TableCell<Cards,Worker>(){
            @Override
            protected  void updateItem(Worker item,boolean empty){
            super.updateItem(item,empty);
            if(item==null|| empty){
            setText(null);
                                 }else{
            setText(item.getName()+" "+item.getSurrname());
                                     }
                                                                }
                                            };
        });
        columnUser.setCellValueFactory(new PropertyValueFactory<>("cardWorker"));
}

}