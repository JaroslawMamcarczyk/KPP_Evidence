package pl.kpp.dao.productDao;

import pl.kpp.dao.Database;
import pl.kpp.produckt.Produckt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProducktDao {
    private int idDao;
    private int kindDao;
    private String nameDao;
    private String serialNumberDao;
    private String inventoryNumberDao;
    private int evidentaialNumberDao;
    private int priceDao;
    private int productionYearDao;
    private int typeDao;
    private int roomNumberDao;
    private int departamentDao;
    private String commentsDao;
    private PreparedStatement statement;

    public int getIdDao() {
        return idDao;
    }

    public int getKindDao() {
        return kindDao;
    }

    public String getNameDao() {
        return nameDao;
    }

    public String getSerialNumberDao() {
        return serialNumberDao;
    }

    public String getInventoryNumberDao() {
        return inventoryNumberDao;
    }

    public int getEvidentaialNumberDao() {
        return evidentaialNumberDao;
    }

    public int getPriceDao() {
        return priceDao;
    }

    public int getProductionYearDao() {
        return productionYearDao;
    }

    public int getTypeDao() {
        return typeDao;
    }

    public int getRoomNumberDao() {
        return roomNumberDao;
    }

    public int getDepartamentDao() {
        return departamentDao;
    }

    public String getCommentsDao() {
        return commentsDao;
    }

    public ProducktDao(int idDao, int kindDao, String nameDao, String serialNumberDao, String inventoryNumberDao, int evidentaialNumberDao, int priceDao, int productionYearDao, int typeDao, int roomNumberDao, int departamentDao, String commentsDao) {
        this.idDao = idDao;
        this.kindDao = kindDao;
        this.nameDao = nameDao;
        this.serialNumberDao = serialNumberDao;
        this.inventoryNumberDao = inventoryNumberDao;
        this.evidentaialNumberDao = evidentaialNumberDao;
        this.priceDao = priceDao;
        this.productionYearDao = productionYearDao;
        this.typeDao = typeDao;
        this.roomNumberDao = roomNumberDao;
        this.departamentDao = departamentDao;
        this.commentsDao = commentsDao;
    }

    public ProducktDao(int kindDao, String nameDao, String serialNumberDao, String inventoryNumberDao, int evidentaialNumberDao, int priceDao, int productionYearDao, int typeDao, int roomNumberDao, int departamentDao, String commentsDao) {
        this.kindDao = kindDao;
        this.nameDao = nameDao;
        this.serialNumberDao = serialNumberDao;
        this.inventoryNumberDao = inventoryNumberDao;
        this.evidentaialNumberDao = evidentaialNumberDao;
        this.priceDao = priceDao;
        this.productionYearDao = productionYearDao;
        this.typeDao = typeDao;
        this.roomNumberDao = roomNumberDao;
        this.departamentDao = departamentDao;
        this.commentsDao = commentsDao;
    }

    public static void readProduckt(){
        Database date = new Database();
        Produckt.getProducktList().clear();
        try (ResultSet result = date.select("SELECT * from produckts")) {
            while (result.next()) {
                ProducktDao producktDao = new ProducktDao(result.getInt("id"),result.getInt("product_kind"),result.getString("product_name"),
                        result.getString("product_serial"),result.getString("product_inventory"),result.getInt("product_evidential"),result.getInt("produckt_price"),
                        result.getInt("product_year"),result.getInt("product_type"),result.getInt("product_room"),result.getInt("product_department"),result.getString("product_comments"));
                Produckt produckt= new Produckt(producktDao);
                Produckt.getProducktList().add(produckt);
            }
        }catch (SQLException e){
            System.out.println("błąd odczytu tabeli");
        }
        date.closeDatabase();
    }

    public void saveProduckt(){
        Database date = new Database();
        try {
            statement = date.getCon().prepareStatement("INSERT INTO materials (product_kind,product_name,product_serial,product_inventory,product_evidential,product_price," +
                    "product_year,product_type,product_room,product_department,product_comments) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            statement.setInt(1,this.kindDao);
            statement.setString(2,this.nameDao);
            statement.setString(3, this.serialNumberDao);
            statement.setString(4,this.inventoryNumberDao);
            statement.setInt(5,this.evidentaialNumberDao);
            statement.setInt(6,this.priceDao);
            statement.setInt(7,this.productionYearDao);
            statement.setInt(8,this.typeDao);
            statement.setInt(9,this.roomNumberDao);
            statement.setInt(10,this.departamentDao);
            statement.setString(11,this.commentsDao);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Nie zapisałem - cos nie pykło");
        }
        date.closeDatabase();
    }
}
