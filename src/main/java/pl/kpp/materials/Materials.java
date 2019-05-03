package pl.kpp.materials;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.kpp.dao.materialsDao.MaterialsDao;

import java.util.ArrayList;
import java.util.List;

public class Materials {
    private int id;
    private StringProperty name = new SimpleStringProperty();
    private int amount;
    private StringProperty type = new SimpleStringProperty();
    private static List<Materials> materialsList = new ArrayList<>();
    public static List<Materials> getMaterialsList() {
        return materialsList;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name.get();
    }
    public int getAmount() {
        return amount;
    }
    public String getType() {
        return type.get();
    }
    public void setAmount(int count){ this.amount=count;}


    public Materials(MaterialsDao dao){
        this.id = dao.getDaoId();
        this.name.set(dao.getDaoName());
        this.amount = dao.getDaoAmount();
        this.type.set(dao.getDaoType());
    }

    public static Materials SearchEquipment(int searchingId) {
        Materials eq=null;
        for (Materials materials : materialsList) {
            if (materials.id == searchingId) {
                eq= materials;
            }
        }
        return eq;
    }

    public static Materials findmaterial(int idMaterial){
        for (Materials material:materialsList){
            if (material.id==idMaterial)
                return material;
        } return null;
    }
}
