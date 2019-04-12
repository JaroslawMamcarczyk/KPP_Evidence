package pl.kpp.dao.workersDao;

import pl.kpp.dao.Database;
import pl.kpp.workers.Policeman;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PolicemanDao {

    private String daoName;
    private String daoSurname;
    private String daoEwidential;
    private String daoPesel;
    private int daoRange;
    private int daoId;
    private int daoDepartament;
    private int daoRanks;
    private static List<PolicemanDao> policemanDaoList = new ArrayList<>();

    public int getDaoId() {
        return daoId;
    }
    public String getDaoName() {
        return daoName;
    }
    public String getDaoSurname() {
        return daoSurname;
    }
    public String getDaoEwidential() {
        return daoEwidential;
    }
    public String getDaoPesel() {
        return daoPesel;
    }
    public int getDaoRange() {
        return daoRange;
    }
    public int getDaoDepartament(){return daoDepartament;}
    public int getDaoRanks(){return daoRanks;}
    public static List<PolicemanDao> getPolicemanDAOList() {
        return policemanDaoList;
    }


    public PolicemanDao(int daoid, String daoName, String daoSurname, String daoEwidential, String daoPesel, int daoRange, int daoDepartament, int daoRanks) {
        this.daoId = daoid;
        this.daoName = daoName;
        this.daoSurname = daoSurname;
        this.daoEwidential = daoEwidential;
        this.daoPesel = daoPesel;
        this.daoRange = daoRange;
        this.daoDepartament = daoDepartament;
        this.daoRanks = daoRanks;
    }
    public PolicemanDao(String daoName, String daoSurname, String daoEwidential, String daoPesel, int daoRange, int daoDepartament, int daoRanks) {
        this.daoName = daoName;
        this.daoSurname = daoSurname;
        this.daoEwidential = daoEwidential;
        this.daoPesel = daoPesel;
        this.daoRange = daoRange;
        this.daoDepartament = daoDepartament;
        this.daoRanks = daoRanks;
    }


    /**
     *Reading all workers from Database and create list
     */
    public static void readPoliceman(){
        Database date = new Database();
        policemanDaoList.clear();
        try (ResultSet result = date.select("SELECT * FROM workers")) {
            while (result.next()) {
                PolicemanDao policeman = new PolicemanDao(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),
                        result.getString(5),result.getInt(6),result.getInt(7),
                        result.getInt(8));
                policemanDaoList.add(policeman);
            }
        }catch (SQLException e){
            System.out.println("błąd odczytu tabeli");
        }
        date.closeDatabase();
    }

    /**
     * saving worker in database
     */
    public void savePoliceman(){
        PreparedStatement statement;
        Database date = new Database();
        try {
            statement = date.getCon().prepareStatement("INSERT INTO policeman (name, Surname, ewidential, Pesel, Range,Departament, ranks) " +
                    "VALUES (?,?,?,?,?,?,?)");
            statement.setString(1, this.daoName);
            statement.setString(2, this.daoSurname);
            statement.setString(3, this.daoEwidential);
            statement.setString(4, this.daoPesel);
            statement.setInt(5, this.daoRange);
            statement.setInt(6,this.daoDepartament);
            statement.setInt(7,this.daoRanks);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("błąd zapytania sql");
        }
        date.closeDatabase();
    }


    /**
     * Delete worker from database
     * @param policeman  - object to delete
     */
    public static void deletePoliceman(Policeman policeman){
        Database date = new Database();
        try {
            PreparedStatement statement = date.getCon().prepareStatement("DELETE FROM workers WHERE id="+policeman.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("błąd zapytania");
        }
        date.closeDatabase();
    }

    /**
     * modify worker from database
     * @param policeman object to be saved instead of the original
     */
    public static void updatePoliceman(PolicemanDao policeman){
        Database date = new Database();
        String sql = "UPDATE workers SET name=?, surname = ?, pesel = ?, ewidential = ?, range = ?,departament = ?, ranks = ?"+"WHERE id=?";
        try{
            PreparedStatement statement = date.getCon().prepareStatement(sql);
            statement.setString(1,policeman.getDaoName());
            statement.setString(2,policeman.getDaoSurname());
            statement.setString(3,policeman.getDaoPesel());
            statement.setString(4,policeman.getDaoEwidential());
            statement.setInt(5,policeman.getDaoRange());
            statement.setInt(6,policeman.getDaoDepartament());
            statement.setInt(7,policeman.getDaoRanks());
            statement.setInt(8,policeman.getDaoId());
            statement.executeUpdate();
            System.out.println("zapisałem nowego pracownika");
        }catch (SQLException e){
            System.out.println("błąd zapytania");
        }
        date.closeDatabase();
    }
}
