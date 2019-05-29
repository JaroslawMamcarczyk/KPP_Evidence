package pl.kpp.dao.productDao;


import pl.kpp.dao.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComputerDao  {
    private int computerDaoID;
    private int computerDaoType;
    private int computerDaoProduct;
    private String computerDaoIP;
    private String computerDaoMask;
    private String computerDaoGate;
    private String computerDaoMAC;
    private String computerDaoName;
    private String computerDaoWorkGroup;
    private String computerDaoSystem;
    private int computerDaoWorker;
    private String computerDaoSwitch;
    private String computerDaoPort;
    private String compuetrDaoSocket;
    private String computerDaoKey;
    private static List<ComputerDao> computerDaoList = new ArrayList<>();
    private PreparedStatement statement;

    public ComputerDao(int idDao, int computerDaoType,int computerDaoProduct, String computerDaoIP, String computerDaoMask, String computerDaoGate, String computerDaoMAC, String computerDaoName, String computerDaoWorkGroup, String computerDaoSystem, int computerDaoWorker, String computerDaoSwitch, String computerDaoPort, String computerDaoSocket, String computerDaoKey) {
        this.computerDaoID = idDao;
        this.computerDaoType = computerDaoType;
        this.computerDaoProduct = computerDaoProduct;
        this.computerDaoIP = computerDaoIP;
        this.computerDaoMask = computerDaoMask;
        this.computerDaoGate = computerDaoGate;
        this.computerDaoMAC = computerDaoMAC;
        this.computerDaoName = computerDaoName;
        this.computerDaoWorkGroup = computerDaoWorkGroup;
        this.computerDaoSystem = computerDaoSystem;
        this.computerDaoWorker = computerDaoWorker;
        this.computerDaoSwitch = computerDaoSwitch;
        this.computerDaoPort = computerDaoPort;
        this.compuetrDaoSocket = computerDaoSocket;
        this.computerDaoKey = computerDaoKey;
    }

    public ComputerDao(int computerDaoType,int computerDaoProduct, String computerDaoIP, String computerDaoMask, String computerDaoGate, String computerDaoMAC, String computerDaoName, String computerDaoWorkGroup, String computerDaoSystem, int computerDaoWorker, String computerDaoSwitch, String computerDaoPort, String computerDaoSocket, String computerDaoKey) {
        this.computerDaoType = computerDaoType;
        this.computerDaoProduct = computerDaoProduct;
        this.computerDaoIP = computerDaoIP;
        this.computerDaoMask = computerDaoMask;
        this.computerDaoGate = computerDaoGate;
        this.computerDaoMAC = computerDaoMAC;
        this.computerDaoName = computerDaoName;
        this.computerDaoWorkGroup = computerDaoWorkGroup;
        this.computerDaoSystem = computerDaoSystem;
        this.computerDaoWorker = computerDaoWorker;
        this.computerDaoSwitch = computerDaoSwitch;
        this.computerDaoPort = computerDaoPort;
        this.compuetrDaoSocket = computerDaoSocket;
        this.computerDaoKey = computerDaoKey;
    }

    public static void readComputer(){
        Database date = new Database();
        computerDaoList.clear();
        try (ResultSet result = date.select("SELECT * from computers")) {
            while (result.next()) {
                ComputerDao computerDao = new ComputerDao(result.getInt("id"),result.getInt("computer_type"),result.getInt("computer_product"),
                        result.getString("computer_ip"),result.getString("computer_mask"),result.getString("computer_gate"),result.getString("computer_mac"),
                        result.getString("computer_name"),result.getString("computer_work_group"),result.getString("computer_system"),result.getInt("computer_worker"),
                        result.getString("computer_switch"),result.getString("computer_port"),result.getString("computer_socket"),result.getString("computer_key"));
                computerDaoList.add(computerDao);
            }
        }catch (SQLException e){
            System.out.println("błąd odczytu tabeli");
        }
        date.closeDatabase();
    }

    public void saveComputer(){
        Database date = new Database();
        try {
            statement = date.getCon().prepareStatement("INSERT INTO computers (computer_type, computer_product, computer_ip, computer_mask, computer_gate, computer_mac, computer_name, computer_work_group, computer_system, computer_worker, computer_switch, computer_port, computer_socket, computer_key) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            statement.setInt(1,this.computerDaoType);
            statement.setInt(2,this.computerDaoProduct);
            statement.setString(3, this.computerDaoIP);
            statement.setString(4,this.computerDaoMask);
            statement.setString(5,this.computerDaoGate);
            statement.setString(6,this.computerDaoMAC);
            statement.setString(7,this.computerDaoName);
            statement.setString(8,this.computerDaoWorkGroup);
            statement.setString(9,this.computerDaoSystem);
            statement.setInt(10,this.computerDaoWorker);
            statement.setString(11,this.computerDaoSwitch);
            statement.setString(12,this.computerDaoPort);
            statement.setString(13,compuetrDaoSocket);
            statement.setString(14,computerDaoKey);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Nie zapisałem - cos nie pykło");
        }
        date.closeDatabase();
    }
}
