package pl.kpp.dao.buildingDao;

import pl.kpp.building.Building;
import pl.kpp.dao.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuildingDao {
    private int idDao;
    private String nameDao;
    private int buildingTypeDao;
    private int buildingParentDao;

    public int getIdDao() {
        return idDao;
    }

    public String getNameDao() {
        return nameDao;
    }

    public int getBuildingTypeDao() {
        return buildingTypeDao;
    }

    public int getBuildingParentDao() {
        return buildingParentDao;
    }

    public BuildingDao(int idDao, String nameDao, int buildingTypeDao, int buildingParentDao) {
        this.idDao = idDao;
        this.nameDao = nameDao;
        this.buildingTypeDao = buildingTypeDao;
        this.buildingParentDao = buildingParentDao;
    }

    public BuildingDao(String nameDao, int buildingTypeDao, int buildingParentDao) {
        this.nameDao = nameDao;
        this.buildingTypeDao = buildingTypeDao;
        this.buildingParentDao = buildingParentDao;
    }

    public static void readBuilding(Database date){
        try (ResultSet result = date.select("SELECT * from building")) {
            Building.getBuildingList().clear();
            Building.getFloorList().clear();
            Building.getRoomList().clear();
            while (result.next()) {
                BuildingDao buildingDao = new BuildingDao(result.getInt("id"),result.getString("building_name"),result.getInt("building_type"), result.getInt("building_parent"));
             Building building = new Building(buildingDao);
             switch (buildingDao.getBuildingTypeDao()){
                 case 1:{
                     Building.getBuildingList().add(building);
                     break;
                 }
                 case 2:{
                     Building.getFloorList().add(building);
                     break;
                 }
                 case 3:{
                     Building.getRoomList().add(building);
                     break;
                 }
             }
            }
        }catch (SQLException e){
            System.out.println("błąd odczytu tabeli");
        }
    }


    public  void saveBuilding(){
        Database date = new Database();
        try {
            PreparedStatement statement = date.getCon().prepareStatement("INSERT INTO building (building_name, building_type, building_parent)"+" VALUES (?,?,?)");
            statement.setString(1,this.nameDao);
            statement.setInt(2,this.buildingTypeDao);
            statement.setInt(3,this.buildingParentDao);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("błąd zapytania");
        }
    }
}
