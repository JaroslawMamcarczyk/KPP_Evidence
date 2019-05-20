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
    private Integer positionX;
    private Integer getPositionY;
    private static List<Building> buildingList = new ArrayList<>();
    private static List<Building> roomList = new ArrayList<>();
    private  static  List<Building> floorList = new ArrayList<>();

    public String getName() {
        return name.get();
    }

    public int getId() {
        return id;
    }

    public Building getParent() {
        return parent;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public Integer getGetPositionY() {
        return getPositionY;
    }

    public static List<Building> getBuildingList() {
        return buildingList;
    }

    public static List<Building> getRoomList() {
        return roomList;
    }

    public static List<Building> getFloorList() {
        return floorList;
    }

    public Building(BuildingDao buildingDao){
        this.id = buildingDao.getIdDao();
        this.name.set(buildingDao.getNameDao());
        switch (buildingDao.getBuildingTypeDao()){
            case 1:{
                this.type.set("Budynek");
                this.parent = null;
                break;
            }
            case 2:{
                this.type.set("Piętro");
                this.parent = searchBuilding(buildingDao.getBuildingParentDao(),buildingList);
                break;
            }
            case 3:{
                this.type.set("Pokój");
                this.parent = searchBuilding(buildingDao.getBuildingParentDao(),floorList);
                positionX = buildingDao.getPositionX();
                getPositionY = buildingDao.getPositionY();
                break;
            }
        }
    }

    public static Building searchBuilding(int id, List<Building> typeList){
        Building result = null;
        for(Building building:typeList){
            if(building.id==id)
               result = building;
        }
        return result;
    }
}
