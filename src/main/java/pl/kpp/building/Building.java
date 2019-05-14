package pl.kpp.building;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.kpp.dao.buildingDao.BuildingDao;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private int id;
    private StringProperty name = new SimpleStringProperty();
    private StringProperty type = new SimpleStringProperty();
    private Building parent;
    private List<Building> buildingList = new ArrayList<>();

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public Building(BuildingDao buildingDao){
        this.id = buildingDao.getIdDao();
        this.name.set(buildingDao.getNameDao());
        switch (buildingDao.getBuildingTypeDao()){
            case 1:{
                this.type.set("Budynek");
                break;
            }
            case 2:{
                this.type.set("Piętro");
                break;
            }
            case 3:{
                this.type.set("Pokój");
                break;
            }
        }
                this.parent = searchBuilding(buildingDao.getBuildingParentDao());
    }

    public Building searchBuilding(int id){
        Building result = null;
        for(Building building:buildingList){
            if(building.id==id)
               result = building;
        }
        return result;
    }
}
