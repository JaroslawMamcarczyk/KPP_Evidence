package pl.kpp.dao;

import pl.kpp.Works;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorksDao {
    private int idDao;
    private String jobDao;
    private int statusDao;
    private Date worksDate;
    private static List<Works> worksList = new ArrayList<Works>();

    public int getIdDao() { return idDao; }
    public String getJobDao() { return jobDao; }
    public int getStatusDao(){return statusDao;}
    public Date getWorksDate() { return worksDate; }

    public static List<Works> getWorksList() { return worksList; }

    public WorksDao( int gid, String gjob, int gstatus, Date gdate){
        this.jobDao = gjob;
        this.idDao = gid;
        this.statusDao = gstatus;
        this.worksDate = gdate;
    }
    public WorksDao(String job){
        this.jobDao = job;
    }

    public static void readWorks(Database date){
        try (ResultSet result = date.select("SELECT * from works")) {
            worksList.clear();
            while (result.next()) {
                WorksDao worksDao = new WorksDao(result.getInt("id"),result.getString("job"),result.getInt("status"), result.getDate("works_data"));
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
            PreparedStatement statement = date.getCon().prepareStatement("INSERT INTO works (job, status,works_data)"+" VALUES (?,0,?)");
            statement.setString(1,this.jobDao);
            long time = System.currentTimeMillis();
            java.sql.Date currentDate = new java.sql.Date(time);
            statement.setDate(2,currentDate);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("błąd zapytania");
        }
    }

    public static void deleteWorks(int idDao){
        Database database = new Database();
        try {
            PreparedStatement statement = database.getCon().prepareStatement("UPDATE works SET status=1 WHERE id = ? ");
            statement.setInt(1,idDao);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

