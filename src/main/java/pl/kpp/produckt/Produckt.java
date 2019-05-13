package pl.kpp.produckt;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.kpp.dao.productDao.ProducktDao;

import java.util.ArrayList;
import java.util.List;

public class Produckt {
    private int id;
    private StringProperty kind = new SimpleStringProperty();
    private StringProperty productName = new SimpleStringProperty();
    private StringProperty serialNumber =new SimpleStringProperty();
    private StringProperty inventoryNumber = new SimpleStringProperty();
    private int evidentaialNumber;
    private int price;
    private int productionYear;
    private StringProperty type = new SimpleStringProperty();
    private int roomNumber;
    private int department;
    private StringProperty comments = new SimpleStringProperty();
    private static List<Produckt> producktList = new ArrayList<>();

    public static List<Produckt> getProducktList(){return  producktList;}

    public Produckt(ProducktDao producktDao) {
        this.id = producktDao.getIdDao();
        switch (producktDao.getKindDao()){
            case 1:{
                this.kind.set("informatyka");
                break;
            }
            case 2:{
                this.kind.set("Łączność");
                break;
            }
        }
        this.productName.set(producktDao.getNameDao());
        this.serialNumber.set(producktDao.getSerialNumberDao());
        this.inventoryNumber.set(producktDao.getInventoryNumberDao());
        this.evidentaialNumber = producktDao.getEvidentaialNumberDao();
        this.price = producktDao.getPriceDao();
        this.productionYear = producktDao.getProductionYearDao();
        switch (producktDao.getTypeDao()){
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
        this.roomNumber = producktDao.getRoomNumberDao();
        this.department = producktDao.getDepartamentDao();
        this.comments.set(producktDao.getCommentsDao());
    }
}
