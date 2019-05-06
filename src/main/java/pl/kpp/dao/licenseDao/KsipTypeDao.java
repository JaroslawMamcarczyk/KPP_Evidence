package pl.kpp.dao.licenseDao;

import pl.kpp.dao.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KsipTypeDao {
    private int daoId;
    private String daoName;
    private String daoDescrib;

    public int getDaoId() {
        return daoId;
    }
    public String getDaoName() {
        return daoName;
    }
    public String getDaoDescrib() {
        return daoDescrib;
    }


    public KsipTypeDao(int gid, String gname, String gdescrib){
     this.daoId = gid;
     this.daoName = gname;
     this.daoDescrib = gdescrib;
    }
    public static void readKsip(){
        Database date = new Database();
        try (ResultSet result = date.select("SELECT * from ksip_type")) {
            while (result.next()) {
                KsipTypeDao ksipdao = new KsipTypeDao(result.getInt("id"),result.getString("ksip_name"), result.getString("ksip_descript"));
            }
        }catch (SQLException e){
            System.out.println("błąd odczytu tabeli");
        }
        date.closeDatabase();
    }

    public void saveKsip(){
        Database date = new Database();
        try {
            PreparedStatement statement = date.getCon().prepareStatement("INSERT INTO ksip_type VALUES (?,?,?)");
            statement.setInt(1,this.daoId);
            statement.setString(2,this.daoName);
            statement.setString(3,this.daoDescrib);
            statement.execute();
            System.out.println(statement);
        } catch (SQLException e) {
            System.out.println("błąd zapytania coś nie pykło");
        }
        date.closeDatabase();
    }

//    public static void deleteKsip(KsipTypeDao gksip){
//        Database date = new Database();
//        String sql = "DELETE FROM ksip WHERE number="+gksip.daoId;
//        try {
//            PreparedStatement statement = date.getCon().prepareStatement(sql);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("błąd zapytania");
//        }
//        date.closeDatabase();
//    }

    public static void updateKsip(KsipTypeDao gksip){
        Database date = new Database();
        String sql = "UPDATE ksip_type SET ksip_name=?, ksip_descript = ? WHERE id=?";
        System.out.println(sql);
        try{
            PreparedStatement statement = date.getCon().prepareStatement(sql);
            statement.setString(1,gksip.daoName);
            statement.setString(2,gksip.daoDescrib);
            statement.setInt(3,gksip.daoId);
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println("błąd zapytania");
        }
        date.closeDatabase();
    }
}
