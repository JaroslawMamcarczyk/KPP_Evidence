package pl.kpp.controllers.workersControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.kpp.dao.Database;
import pl.kpp.dao.productDao.CardsDao;
import pl.kpp.produckt.Cards;
import pl.kpp.workers.Worker;

public class CardsScreenController {
    @FXML
    private TableView<Cards> tableViewCards;

    @FXML
    private TableColumn<Cards, Integer> columnLp;

    @FXML
    private TableColumn<Cards, String> columnNumber;

    @FXML
    private TableColumn<Cards, Worker> columnUser;

    @FXML
    void clickAddCard(ActionEvent event) {

    }

    @FXML
    void clickEditCard(ActionEvent event) {

    }

    @FXML
    public void initialize(){
        Database date = new Database();
        CardsDao.readCards(date);
        ObservableList<Cards> cardsObservableList = FXCollections.observableList(Cards.getCardsList());
        setTable(cardsObservableList);

    }

    private void setTable(ObservableList<Cards> glist) {
        tableViewCards.getItems().clear();
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
        columnUser.setCellValueFactory(new PropertyValueFactory<>("worker"));
}

}