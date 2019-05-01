package pl.kpp.dao.workersDao;

import pl.kpp.dao.Database;
import pl.kpp.workers.Ranks;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RanksDao {
    private int idRanksDao;
    private String nameRanksDao;
    private int departamentRanksDao;

    public int getIdRanksDao() {
        return idRanksDao;
    }
    public String getNameRanksDao() {
        return nameRanksDao;
    }
    public int getDepartamentRanksDao() {
        return departamentRanksDao;
    }

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
            PreparedStatement statement = date.getCon().prepareStatement("INSERT INTO ranks (ranks_name, ranks_departament)"+" VALUES (?,?)");
            statement.setString(1,this.nameRanksDao);
            statement.setInt(2,this.departamentRanksDao);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("błąd zapytania");
        }
        date.closeDatabase();
    }

    public static void readRanks(Database date){
        try (ResultSet read = date.select("SELECT * from ranks")) {
            Ranks.getRanksList().clear();
            while (read.next()) {
               RanksDao ranksDAO = new RanksDao(read.getInt("id"),read.getString("ranks_name"),read.getInt("ranks_departament"));
                Ranks ranks = new Ranks(ranksDAO);
               Ranks.getRanksList().add(ranks);
            }
        }catch (SQLException e){
            System.out.println("błąd odczytu tabeli");
        }
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
            PreparedStatement statement = date.getCon().prepareStatement("UPDATE ranks SET ranks_name =?, ranks_departament = ? WHERE id =?");
            statement.setString(1,this.nameRanksDao);
            statement.setInt(2,this.departamentRanksDao);
            statement.setInt(3,old);
            statement.execute();
        }catch (SQLException e){
            System.out.println("błąd aktualizacji rekordu "+e);
        }
    }
}
