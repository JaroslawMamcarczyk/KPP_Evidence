package pl.kpp.dao.buildingDao;

import pl.kpp.building.Building;
import pl.kpp.dao.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuildingDao {
    private int idDao;
    private String nameDao;
    private int buildingTypeDao;
    private int buildingParentDao;
    private Integer positionX;
    private Integer positionY;

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

    public int getPositionX() {
        return positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public BuildingDao(int idDao, String nameDao, int buildingTypeDao, int buildingParentDao, Integer x, Integer y) {
        this.idDao = idDao;
        this.nameDao = nameDao;
        this.buildingTypeDao = buildingTypeDao;
        this.buildingParentDao = buildingParentDao;
        this.positionX = x;
        this.positionY = y;
    }

    public BuildingDao(String nameDao, int buildingTypeDao, int buildingParentDao,Integer x, Integer y) {
        this.nameDao = nameDao;
        this.buildingTypeDao = buildingTypeDao;
        this.buildingParentDao = buildingParentDao;
        this.positionX = x;
        this.positionY = y;
    }

    public static void readBuilding(Database date){
        try (ResultSet result = date.select("SELECT * from building")) {
            Building.getBuildingList().clear();
            Building.getFloorList().clear();
            Building.getRoomList().clear();
            while (result.next()) {
                BuildingDao buildingDao = new BuildingDao(result.getInt("id"),result.getString("building_name"),result.getInt("building_type"), result.getInt("building_parent"),
                        result.getInt("building_x"),result.getInt("building_y"));
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
            PreparedStatement statement = date.getCon().prepareStatement("INSERT INTO building (building_name, building_type, building_parent, building_x, building_y)"+" VALUES (?,?,?,?,?)");
            statement.setString(1,this.nameDao);
            statement.setInt(2,this.buildingTypeDao);
            statement.setInt(3,this.buildingParentDao);
            statement.setInt(4,this.positionX);
            statement.setInt(5,this.positionY);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("błąd zapytania");
        }
    }

    public static Integer getMaxPosition(int floor){
            Database date=new Database();
            int maxX=0;
            try{
                Statement stat = date.getCon().createStatement();
                ResultSet result= stat.executeQuery("SELECT MAX(building_x) from building DESC LIMIT 1");
                maxX = result.getInt(1);
            }catch (SQLException e){
                System.out.println("Nie pobrałem ostatniego ID");
            }
            date.closeDatabase();
            return maxX;
    }

    public static Integer getMaxPositionY(int floor){
        Database date=new Database();
        int maxY=0;
        try{
            Statement stat = date.getCon().createStatement();
            ResultSet result= stat.executeQuery("SELECT MAX(building_y) from building DESC LIMIT 1");
            maxY = result.getInt(1);
        }catch (SQLException e){
            System.out.println("Nie pobrałem ostatniego ID");
        }
        date.closeDatabase();
        return maxY;
    }
}
