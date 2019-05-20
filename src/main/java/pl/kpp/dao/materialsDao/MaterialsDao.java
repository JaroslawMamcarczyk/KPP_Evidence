package pl.kpp.dao.materialsDao;


import pl.kpp.dao.Database;
import pl.kpp.materials.Materials;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MaterialsDao {
    private int daoId;
    private String daoName;
    private int daoAmount = 0;
    private String daoType;
    private static PreparedStatement statement;


    public int getDaoId() {
        return daoId;
    }
    public String getDaoName() {
        return daoName;
    }
    public int getDaoAmount() {
        return daoAmount;
    }
    public String getDaoType() {
        return daoType;
    }
    public void setDaoId(int daoId){ this.daoId = daoId; }


    public MaterialsDao(int gid, String gname, int gamount, String gtype){
        this.daoId = gid;
        this.daoName = gname;
        this.daoAmount = gamount;
        this.daoType = gtype;
    }

    public MaterialsDao(String gname, int gamount, String gtype){
        this.daoName = gname;
        this.daoAmount = gamount;
        this.daoType = gtype;
    }
    public static void readEquipment(){
        Database date = new Database();
        Materials.getMaterialsList().clear();
        try (ResultSet result = date.select("SELECT * from materials")) {
            while (result.next()) {
                MaterialsDao equipment = new MaterialsDao(result.getInt("id"),result.getString("materials_name"),
                        result.getInt("count"),result.getString("materials_type"));
                Materials materials= new Materials(equipment);
                Materials.getMaterialsList().add(materials);
            }
        }catch (SQLException e){
            System.out.println("błąd odczytu tabeli");
        }
        date.closeDatabase();
    }

    public void saveEquipment(){
        Database date = new Database();
        try {
            statement = date.getCon().prepareStatement("INSERT INTO materials (materials_name, count, materials_type) VALUES (?,?,?)");
            statement.setString(1, this.daoName);
            statement.setInt(2, this.daoAmount);
            statement.setString(3, this.daoType);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Nie zapisałem - cos nie pykło");
        }
        date.closeDatabase();
    }



    public static void deleteEquipment(Materials equipment){
        Database date = new Database();
        try {
            statement = date.getCon().prepareStatement("DELETE FROM materials WHERE id="+searchMaterialDao(equipment));
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nie usunąłęm - bład zapytania");
        }
        date.closeDatabase();
    }

    public static void updateEquipment(MaterialsDao equipment){
        Database date = new Database();
        String sql = "UPDATE materials SET materials_name=?, count=?, materials_type=?"+"WHERE id=?";
        try{
            statement = date.getCon().prepareStatement(sql);
            statement.setString(1,equipment.getDaoName());
            statement.setInt(2,equipment.getDaoAmount());
            statement.setString(3,equipment.getDaoType());
            statement.setInt(4,equipment.getDaoId());
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Nie nadpisałem - błąd zapytania");
        }
        date.closeDatabase();
    }

    public static void changeNumberOfEquipment(int number, int id){
        Database databases = new Database();
        String sql = "UPDATE materials SET count=? WHERE id=?";
        try{
            statement = databases.getCon().prepareStatement(sql);
            statement.setInt(1,number);
            statement.setInt(2,id);
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Nie zaktualizowałem ilośći");
        }
        databases.closeDatabase();
    }

    public static int checkNumberOfEquipment(int id){
        Database databases = new Database();
        int actualyNumberofEquipment =0;
        String sqlSelect = "SELECT count FROM materials WHERE id=?";
        try {
            statement = databases.getCon().prepareStatement(sqlSelect);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                actualyNumberofEquipment=result.getInt(1);
            }
        }catch (SQLException e){
            System.out.println("Nie pobrałem wartości z tabeli");
        }return  actualyNumberofEquipment;
    }

    public static int searchMaterialDao(Materials materials){
        int searchingId = 0;
        for(Materials material:Materials.getMaterialsList()){
           if(materials.getId()==material.getId())
               searchingId=material.getId();
        }return searchingId;
    }
}
