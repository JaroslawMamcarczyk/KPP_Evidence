package pl.kpp.dao.workersDao;


import pl.kpp.dao.Database;
import pl.kpp.workers.Range;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RangeDao {

    private String rangeName;
    private String path;
    private int id;
    private static List<RangeDao> rangeDaoList = new ArrayList<>();

    public String getRangeName() {
        return rangeName;
    }
    public String getPath() {
        return path;
    }
    public int getId(){
        return id;
    }

    public static List<RangeDao> getRangeDaoList() {
        return rangeDaoList;
    }

    /**
     * Constructor
     * @param gname range name
     * @param gpagons path to image with pagons
     * @param gid evidential number
     */
    public RangeDao( int gid, String gname, String gpagons){
        this.rangeName = gname;
        this.path = gpagons;
        this.id = gid;
    }

    /**
     * loading data from the table
     */
    public static void readRange(){
        Database date = new Database();
        try (ResultSet result = date.select("SELECT * from range")) {
            while (result.next()) {
                RangeDao range = new RangeDao(result.getInt("id"),result.getString("range_name"),result.getString("pagons"));
                rangeDaoList.add(range);
            }
        }catch (SQLException e){
            System.out.println("błąd odczytu tabeli");
        }
        date.closeDatabase();
    }

    /**
     * Saving new Range on database
     * @param range new object to saving
     */
    public void saveRange(Range range){
        Database date = new Database();
        try {
            PreparedStatement statement = date.getCon().prepareStatement("INSERT INTO range (range_name, pagons)"+" VALUES (?,?)");
            statement.setString(1,this.rangeName);
            statement.setString(2,this.path);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("błąd zapytania");
        }
        date.closeDatabase();
    }
}
