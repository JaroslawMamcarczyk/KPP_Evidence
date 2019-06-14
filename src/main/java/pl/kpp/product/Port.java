package pl.kpp.product;

import pl.kpp.dao.productDao.PortDao;

import java.util.ArrayList;
import java.util.List;

public class Port {
    private Product producktOnPort;
    private String Socket;
    private Product switchWithPort;
    private int idPort;
    private static List<Port> portList = new ArrayList<>();

    public static List<Port> getPortList(){
        return portList;
    }
    public Product getProducktOnPort() {
        return producktOnPort;
    }
    public String getSocket() {
        return Socket;
    }
    public Product getSwitchWithPort() {
        return switchWithPort;
    }
    public Port(PortDao portDao){
        this.idPort = portDao.getIdDao();
        this.producktOnPort = Product.findProduct(portDao.getProducktDao());
        this.Socket = portDao.getSocketDao();
        this.switchWithPort = Product.findProduct(portDao.getSwitchDao());
    }
}
