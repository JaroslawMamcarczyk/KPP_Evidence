package pl.kpp.materials;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.kpp.dao.materialsDao.DeliverysDao;

import java.util.ArrayList;
import java.util.List;

public class Deliverys {
    private StringProperty name = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private int id;
    private static  List<Deliverys> deliverysList= new ArrayList<>();

    public String getName() {
        return name.get();
    }
    public String getAddress() {
        return address.get();
    }
    public int getId() {
        return id;
    }
    public static List<Deliverys> getDeliverysList(){return  deliverysList;}

    public Deliverys(DeliverysDao deliverysDAO) {
        this.name.set(deliverysDAO.getNameDeliverDao());
        this.address.set(deliverysDAO.getAdresDelivery());
        this.id = deliverysDAO.getIdDao();
    }

    public static Deliverys findDelivery(int idDelivery) {
        for (Deliverys deliverys:deliverysList){
            if(deliverys.id==idDelivery){
                return deliverys;
            }
        }
        return null;
    }

    public static void createDeliverysList(){
        for(DeliverysDao deliverysDao:DeliverysDao.getDeliveryDaoList()){
            Deliverys deliverys = new Deliverys(deliverysDao);
            deliverysList.add(deliverys);
        }
    }
}
