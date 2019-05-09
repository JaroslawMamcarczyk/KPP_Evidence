package pl.kpp.dao;

import pl.kpp.Works;
import pl.kpp.dao.workersDao.RangeDao;
import pl.kpp.workers.Range;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorksDao {
    private int idDao;
    private String jobDao;
    private int statusDao;
    private static List<Works> worksList = new ArrayList<Works>();

    public int getIdDao() { return idDao; }
    public String getJobDao() { return jobDao; }
    public int getStatusDao(){return statusDao;}
    public static List<Works> getWorksList() { return worksList; }

    public WorksDao( int gid, String gjob, int gstatus){
        this.jobDao = gjob;
        this.idDao = gid;
        this.statusDao = gstatus;
    }
    public WorksDao(String job){
        this.jobDao = job;
    }

    public static void readWorks(Database date){
        try (ResultSet result = date.select("SELECT * from works")) {
            while (result.next()) {
                WorksDao worksDao = new WorksDao(result.getInt("id"),result.getString("job"),result.getInt("status"));
                Works works= new Works(worksDao);
                worksList.add(works);
            }
        }catch (SQLException e){
            System.out.println("błąd odczytu tabeli");
        }
    }


    public  void saveWorks(){
        Database date = new Database();
        try {
            PreparedStatement statement = date.getCon().prepareStatement("INSERT INTO works (job)"+" VALUES (?)");
            statement.setString(1,this.jobDao);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("błąd zapytania");
        }
    }
}

