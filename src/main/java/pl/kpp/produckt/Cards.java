package pl.kpp.produckt;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.kpp.dao.productDao.CardsDao;
import pl.kpp.workers.Worker;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private int id;
    private int cardWorkerId;
    private StringProperty cardNumber = new SimpleStringProperty();
    private Worker cardWorker;
    private static List<Cards> cardsList = new ArrayList<>();

    public static List<Cards> getCardsList(){return cardsList;}
    public int getId(){return id;}
    public String getCardNumber(){return cardNumber.get();}
    public Worker getCardWorker(){return cardWorker;}

    public Cards(CardsDao cardsDao) {
        this.id=cardsDao.getIdDao();
        this.cardNumber.set(cardsDao.getCardDaoNumber());
        this.cardWorkerId = cardsDao.getCardWorker();
        if(cardWorkerId!=0){
           cardWorker =Worker.findWorker(cardWorkerId);
        }
    }
}
