package pl.kpp.dao.productDao;

import pl.kpp.dao.Database;
import pl.kpp.product.Cards;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardsDao {
    private int idDao;
    private String cardDaoNumber;
    private int cardWorkerDao;

    public int getIdDao() {
        return idDao;
    }

    public String getCardDaoNumber() {
        return cardDaoNumber;
    }

    public int getCardWorker(){
        return cardWorkerDao;
    }

    public CardsDao(int idDao, String cardDaoNumber,int cardWorker) {
        this.idDao = idDao;
        this.cardDaoNumber = cardDaoNumber;
        this.cardWorkerDao = cardWorker;
    }

    public CardsDao(String cardDaoNumber,int cardWorker){
        this.cardDaoNumber = cardDaoNumber;
        this.cardWorkerDao = cardWorker;
    }

    public static void readCards(Database date){
        try (ResultSet result = date.select("SELECT * from cards")) {
            while (result.next()) {
               CardsDao cardDao = new CardsDao(result.getInt("id"),result.getString("card_number"),result.getInt("card_worker"));
                Cards card = new Cards(cardDao);
                Cards.getCardsList().add(card);
            }
        }catch (SQLException e){
            System.out.println("błąd odczytu tabeli");
        }
    }


    public  void saveCards(){
        Database date = new Database();
        try {
            PreparedStatement statement = date.getCon().prepareStatement("INSERT INTO cards(card_number,card_worker) VALUES (?,?)");
            statement.setString(1,this.cardDaoNumber);
            statement.setInt(2,this.cardWorkerDao);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("błąd zapytania");
        }
    }
}
