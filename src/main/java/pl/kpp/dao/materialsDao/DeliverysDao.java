package pl.kpp.dao.materialsDao;

import pl.kpp.dao.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliverysDao {
    private int idDao;
    private String nameDeliverDao;
    private String adresDelivery;
    private static List<DeliverysDao> deliveryDaoList = new ArrayList<>();

    public int getIdDao() {
        return idDao;
    }
    public String getNameDeliverDao() {
        return nameDeliverDao;
    }
    public String getAdresDelivery() {
        return adresDelivery;
    }

    public static List<DeliverysDao> getDeliveryDaoList() {
        return deliveryDaoList;
    }

    public DeliverysDao(int idDao, String nameDeliverDao, String adresDelivery) {
        this.idDao = idDao;
        this.nameDeliverDao = nameDeliverDao;
        this.adresDelivery = adresDelivery;
    }

    public DeliverysDao(String nameDeliverDao){
        this.nameDeliverDao = nameDeliverDao;
    }

    public DeliverysDao(String nameDeliverDao, String adresDelivery){
        this.nameDeliverDao = nameDeliverDao;
        this.adresDelivery = adresDelivery;
    }

    public static void readDeliverys(){
        Database date = new Database();
        deliveryDaoList.clear();
        try (ResultSet result = date.select("SELECT * from deliverys")) {
            while (result.next()) {
                DeliverysDao deliverys = new DeliverysDao(result.getInt(1),result.getString(2),result.getString(3));
                deliveryDaoList.add(deliverys);
            }
        }catch (SQLException e){
            System.out.println("błąd odczytu tabeli deliverys");
        }
        date.closeDatabase();
    }

    public int saveDelivery(){
        Database date = new Database();
        int lastId=0;
        PreparedStatement statement;
        try{
            statement = date.getCon().prepareStatement("INSERT INTO deliverys (delivery_name,delivery_address) VALUES (?,?)");
            statement.setString(1,this.nameDeliverDao);
            statement.setString(2,this.adresDelivery);
            statement.execute();
            ResultSet resultSet = date.select("SELECT last_insert_rowid()");
            lastId = resultSet.getInt(1);
        }catch (SQLException e){
            System.out.println("Nie zapisałem, błąd w dostepie do tabeli Deliverys "+e);
        }
        date.closeDatabase();
        return lastId;
    }
}
