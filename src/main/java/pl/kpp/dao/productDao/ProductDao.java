package pl.kpp.dao.productDao;

import javafx.beans.property.StringProperty;
import pl.kpp.dao.Database;
import pl.kpp.product.Computer;
import pl.kpp.product.Product;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductDao {
    private int idDao;
    private int kindDao;
    private String nameDao;
    private String serialNumberDao;
    private String inventoryNumberDao;
    private String evidentaialNumberDao;
    private BigDecimal priceDao;
    private int productionYearDao;
    private int typeDao;
    private int roomNumberDao;
    private int departamentDao;
    private String commentsDao;
    private String categoryDao;
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

    public String getEvidentaialNumberDao() {
        return evidentaialNumberDao;
    }

    public BigDecimal getPriceDao() {
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
    public String getCategoryDao() {return categoryDao;}

    public ProductDao(int idDao, int kindDao, String nameDao, String serialNumberDao, String inventoryNumberDao, String evidentaialNumberDao, BigDecimal priceDao, int productionYearDao, int typeDao, int roomNumberDao, int departamentDao, String commentsDao, String categoryDao) {
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
        this.categoryDao = categoryDao;
    }

    public ProductDao(int kindDao, String nameDao, String serialNumberDao, String inventoryNumberDao, String evidentaialNumberDao, BigDecimal priceDao, int productionYearDao, int typeDao, int roomNumberDao, int departamentDao, String commentsDao, String categoryDao) {
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
        this.categoryDao = categoryDao;
    }

    public ProductDao(){}

    public static void readProduckt(){
        Database date = new Database();
        Product.getProductList().clear();
        try (ResultSet result = date.select("SELECT * from product")) {
            while (result.next()) {
                ProductDao productDao = new ProductDao(result.getInt("id"),result.getInt("product_kind"),result.getString("product_name"),
                        result.getString("product_serial"),result.getString("product_inventory"),result.getString("product_evidential"),result.getBigDecimal("product_price"),
                        result.getInt("product_year"),result.getInt("product_type"),result.getInt("product_room"),result.getInt("product_department"),result.getString("product_comment"),result.getString("product_category"));
                if(productDao.getCategoryDao().equals("Komputer")||productDao.getCategoryDao().equals("komputer")||productDao.getCategoryDao().equals("Komputery")||productDao.getCategoryDao().equals("komputery")){
                    ComputerDao computerDao = ComputerDao.findCompuetrDao(result.getInt("id"));
                    if(computerDao!=null) {
                        Computer computer = new Computer(productDao,computerDao);
                        Product.getProductList().add(computer);
                        Computer.getComputerList().add(computer);
                    }else{
                        Product product = new Product(productDao);
                        Product.getProductList().add(product);
                    }
                }else{
                    Product product = new Product(productDao);
                Product.getProductList().add(product);
            }}
        }catch (SQLException e){
            System.out.println("błąd odczytu tabeli");
        }
        date.closeDatabase();
    }

    public void saveProduckt(){
        Database date = new Database();
        try {
            statement = date.getCon().prepareStatement("INSERT INTO product (product_kind,product_name,product_serial,product_inventory,product_evidential,product_price," +
                    "product_year,product_type,product_room,product_department,product_comment,product_category) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            statement.setInt(1,this.kindDao);
            statement.setString(2,this.nameDao);
            statement.setString(3, this.serialNumberDao);
            statement.setString(4,this.inventoryNumberDao);
            statement.setString(5,this.evidentaialNumberDao);
            statement.setBigDecimal(6,this.priceDao);
            statement.setInt(7,this.productionYearDao);
            statement.setInt(8,this.typeDao);
            statement.setInt(9,this.roomNumberDao);
            statement.setInt(10,this.departamentDao);
            statement.setString(11,this.commentsDao);
            statement.setString(12,this.categoryDao);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Nie zapisałem - cos nie pykło");
        }
        date.closeDatabase();
    }

    public static void updateProduct(Product product){
        Database date = new Database();
        PreparedStatement statementUpdate;
        try{
            statementUpdate = date.getCon().prepareStatement("UPDATE product SET product_room =? WHERE id=?");
            statementUpdate.setInt(1,product.getRoomNumber().getId());
            statementUpdate.setInt(2,product.getId());
            statementUpdate.execute();
        }catch (SQLException e){
            System.out.println("Nie zapisałem - cos nie pykło");
        }
    }
}
