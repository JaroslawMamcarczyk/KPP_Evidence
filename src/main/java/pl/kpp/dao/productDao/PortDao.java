package pl.kpp.dao.productDao;

import javafx.scene.chart.PieChart;
import pl.kpp.dao.Database;
import pl.kpp.product.Port;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PortDao {
    private int producktDao;
    private String socketDao;
    private int switchDao;
    private int idDao;

    public int getProducktDao() {
        return producktDao;
    }

    public String getSocketDao() {
        return socketDao;
    }

    public int getSwitchDao() {
        return switchDao;
    }

    public int getIdDao() {
        return idDao;
    }

    public PortDao(int producktDao, String socketDao, int switchDao, int idDao) {
        this.producktDao = producktDao;
        this.socketDao = socketDao;
        this.switchDao = switchDao;
        this.idDao = idDao;
    }

    public PortDao(int producktDao, String socketDao, int switchDao) {
        this.producktDao = producktDao;
        this.socketDao = socketDao;
        this.switchDao = switchDao;
    }

    public static void readPort() {
        Database date = new Database();
        Port.getPortList().clear();
        try (ResultSet result = date.select("select * from port")) {
            while (result.next()) {
                PortDao portDao = new PortDao(result.getInt("port_product"), result.getString("port_socket"), result.getInt("port_switch"), result.getInt("port_id"));
                Port.getPortList().add(new Port(portDao));
            }
        } catch (SQLException e) {
            System.out.println("Błąd zapytania " + e);
        }
    }

    public void savePort(Port port) {
        Database date = new Database();
        PreparedStatement statement;
        try {
            statement = date.getCon().prepareStatement("INSERT INTO port (port_socket,port_product, port_switch) VALUES (?,?,?)");
         //   statement.setInt(1,port.getSocket());
        } catch (SQLException e) {
            System.out.println("Bład zapisu do bazy "+e);
        }
    }
}