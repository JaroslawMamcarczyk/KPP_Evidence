package pl.kpp.dao.workersDao;

import pl.kpp.dao.Database;
import pl.kpp.workers.Departament;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartamentDao {
    private String name;
    private int id;


    public DepartamentDao(String gname, int gid){
        this.id=gid;
        this.name=gname;
    }
    public DepartamentDao(String gname){
        this.name = gname;
    }

    public String getName() { return name; }
    public int getId() { return id; }

    public void saveDepartament(){
        Database date = new Database();
        try {
            PreparedStatement statement = date.getCon().prepareStatement("INSERT INTO departament (departament_name)"+" VALUES (?)");
            statement.setString(1,this.name);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("błąd zapytania");
        }
        date.closeDatabase();
    }

    public static void readDepartament(Database date){
        Departament.getDepartamentList().clear();
        try (ResultSet read = date.select("SELECT * from departament")) {
            while (read.next()) {
                DepartamentDao dao = new DepartamentDao(read.getString("departament_name"),read.getInt("id"));
                Departament departament = new Departament(dao);
                Departament.getDepartamentList().add(departament);
            }
        }catch (SQLException e){
            System.out.println("błąd odczytu tabeli");
        }
    }

    public static void deleteDepartament(String gfind){
        Database date = new Database();
        try {
            PreparedStatement statemen = date.getCon().prepareStatement("DELETE FROM departament where departament_name=?");
            statemen.setString(1,gfind);
            if(statemen.execute()) System.out.println("usunięte");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editDepartament(String newName, int idDepartamentToDelete)
    {
        Database date = new Database();
        try{
            PreparedStatement statement = date.getCon().prepareStatement("UPDATE departament SET departament_name =? WHERE id =?");
            statement.setString(1,newName);
            statement.setInt(2,idDepartamentToDelete);
            statement.execute();
        }catch (SQLException e){
            System.out.println("błąd aktualizacji rekordu "+e);
        }
    }
}
