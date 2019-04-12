package pl.kpp.dao.workersDao;

import pl.kpp.dao.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RanksDao {
    private int idRanksDao;
    private String nameRanksDao;
    private int departamentRanksDao;
    private static List<RanksDao> ranksDaoList = new ArrayList<>();

    public int getIdRanksDao() {
        return idRanksDao;
    }
    public String getNameRanksDao() {
        return nameRanksDao;
    }
    public int getDepartamentRanksDao() {
        return departamentRanksDao;
    }
    public static List<RanksDao> getRanksDaoList(){ return ranksDaoList;}

    public RanksDao(int idRanksDao, String nameRanksDao, int departamentRanksDao) {
        this.idRanksDao = idRanksDao;
        this.nameRanksDao = nameRanksDao;
        this.departamentRanksDao = departamentRanksDao;
    }
    public RanksDao(String nameRanksDao, int departamentRanksDao){
        this.nameRanksDao = nameRanksDao;
        this.departamentRanksDao = departamentRanksDao;
    }
    public void saveRanks(){
        Database date = new Database();
        try {
            PreparedStatement statement = date.getCon().prepareStatement("INSERT INTO ranks (name, departament)"+" VALUES (?,?)");
            statement.setString(1,this.nameRanksDao);
            statement.setInt(2,this.departamentRanksDao);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("błąd zapytania");
        }
        date.closeDatabase();
    }

    public static void readRanks(){
        Database date = new Database();
        try (ResultSet read = date.select("SELECT * from ranks")) {
            ranksDaoList.clear();
            while (read.next()) {
               RanksDao ranksDAO = new RanksDao(read.getInt("id"),read.getString("name"),read.getInt("departament"));
                ranksDaoList.add(ranksDAO);
            }
        }catch (SQLException e){
            System.out.println("błąd odczytu tabeli");
        }
        date.closeDatabase();
    }

    public static void deleteRanks(int ranksToDelete){
        Database date = new Database();
        try {
            PreparedStatement statemen = date.getCon().prepareStatement("DELETE FROM ranks where id=?");
            statemen.setInt(1,ranksToDelete);
            if(statemen.execute()) System.out.println("usunięte");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editRanks(int old)
    {
        Database date = new Database();
        try{
            PreparedStatement statement = date.getCon().prepareStatement("UPDATE ranks SET name =?, departament = ? WHERE id =?");
            statement.setString(1,this.nameRanksDao);
            statement.setInt(2,this.departamentRanksDao);
            statement.setInt(3,old);
            statement.execute();
        }catch (SQLException e){
            System.out.println("błąd aktualizacji rekordu "+e);
        }
    }
}
