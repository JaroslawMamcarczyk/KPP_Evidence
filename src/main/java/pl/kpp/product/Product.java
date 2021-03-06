package pl.kpp.product;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.kpp.building.Building;
import pl.kpp.dao.productDao.ProductDao;
import pl.kpp.workers.Departament;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private int id;
    private ProductKind kind;
    private StringProperty productName = new SimpleStringProperty();
    private StringProperty serialNumber =new SimpleStringProperty();
    private StringProperty inventoryNumber = new SimpleStringProperty();
    private StringProperty evidentialNumber = new SimpleStringProperty();
    private BigDecimal price;
    private int productionYear;
    private ProductType type;
    private Building roomNumber;
    private Departament department;
    private StringProperty category = new SimpleStringProperty();
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
    public enum ProductType{
        UNMATERIAL("N", "niematerialny"),
        PERMANENT("T", "Środek Trwały"),
        REMAINING("P","Pozostały środek trwały");
        private String code;
        private String name;

        ProductType(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getName(){return name;}
        public String getCode(){return code;}
        @Override
        public String toString(){
            return this.code;
        }

        public int fromString(){
            if(this.equals(UNMATERIAL))
                return 1;
            else if(this.equals(PERMANENT))
                return 2;
            else return 3;
        }
    }
    public static List<Product> getProductList(){return productList;}

    public int getId() {
        return id;
    }
    public String getCategory(){
        return category.getValue();
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

    public String getEvidentialNumber() {
        return evidentialNumber.get();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getProductionYear() {
        return productionYear;
    }

   public ProductType getType(){
        return type;
   }


    public Building getRoomNumber() {
        return roomNumber;
    }

    public Departament getDepartment() {
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
        this.category.set(productDao.getCategoryDao());
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
        this.evidentialNumber.set(productDao.getEvidentaialNumberDao());
        this.price = productDao.getPriceDao();
        this.productionYear = productDao.getProductionYearDao();
        switch (productDao.getTypeDao()){
            case 1: {
                this.type=ProductType.UNMATERIAL;
                break;
            }
            case 2:{
                this.type=ProductType.PERMANENT;
                break;
            }
            case 3:{
                this.type=ProductType.REMAINING;
                break;
            }
        }
        if(productDao.getRoomNumberDao()!=0){
            this.roomNumber = Building.searchBuilding(productDao.getRoomNumberDao(),Building.getRoomList());
        }else
            this.roomNumber = null;
        if(productDao.getDepartamentDao()!=0){
            this.department = Departament.findDepartament(productDao.getDepartamentDao());
        }else this.department = null;
        this.comments.set(productDao.getCommentsDao());
    }
    public Product(){}

    public static Product findProduct(int id){
        Product findingProduct = null;
        for(Product product:productList){
            if (product.id==id){
                findingProduct = product;
            }
        }
    return findingProduct;
    }
}
