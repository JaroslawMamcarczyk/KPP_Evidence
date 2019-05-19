package pl.kpp.product;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.kpp.dao.productDao.ProductDao;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int id;
    private ProductKind kind;
    private StringProperty productName = new SimpleStringProperty();
    private StringProperty serialNumber =new SimpleStringProperty();
    private StringProperty inventoryNumber = new SimpleStringProperty();
    private int evidentialNumber;
    private int price;
    private int productionYear;
    private StringProperty type = new SimpleStringProperty();
    private int roomNumber;
    private int department;
    private StringProperty comments = new SimpleStringProperty();
    private static List<Product> productList = new ArrayList<>();
    public enum ProductKind {
        INFORMATICS("I","Informatyka"),
        CONECTION("L","Łączność");
        private String code;
        private String text;

        ProductKind(String i, String informatyka) {
            this.code = i;
            this.text = informatyka;
        }

        public String getText(){return text;}
            @Override
            public String toString(){
                return this.code;
            }

        public int fromString(){
            if (this.equals(INFORMATICS))
                return 1;
            else
                return 2;
        }

    }
    public static List<Product> getProductList(){return productList;}

    public int getId() {
        return id;
    }

    public ProductKind getKind() {
        return kind;
    }

    public String getProductName() {
        return productName.get();
    }

    public String getSerialNumber() {
        return serialNumber.get();
    }

    public String getInventoryNumber() {
        return inventoryNumber.get();
    }

    public int getEvidentialNumber() {
        return evidentialNumber;
    }

    public int getPrice() {
        return price;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getDepartment() {
        return department;
    }

    public String getComments() {
        return comments.get();
    }

    public StringProperty commentsProperty() {
        return comments;
    }

    public Product(ProductDao productDao) {
        this.id = productDao.getIdDao();
        switch (productDao.getKindDao()){
            case 1:{
                this.kind = ProductKind.INFORMATICS;
                break;
            }
            case 2:{
                this.kind = ProductKind.CONECTION;
                break;
            }
        }
        this.productName.set(productDao.getNameDao());
        this.serialNumber.set(productDao.getSerialNumberDao());
        this.inventoryNumber.set(productDao.getInventoryNumberDao());
        this.evidentialNumber = productDao.getEvidentaialNumberDao();
        this.price = productDao.getPriceDao();
        this.productionYear = productDao.getProductionYearDao();
        switch (productDao.getTypeDao()){
            case 1: {
                this.type.set("Pozostałe środki trwałe");
                break;
            }
            case 2:{
                this.type.set("Środki trwałe");
                break;
            }
            case 3:{
                this.type.set("Niematerialne środki trwałe");
                break;
            }
        }
        this.roomNumber = productDao.getRoomNumberDao();
        this.department = productDao.getDepartamentDao();
        this.comments.set(productDao.getCommentsDao());
    }
}
