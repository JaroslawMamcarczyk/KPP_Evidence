package pl.kpp.dao.productDao;


import java.math.BigDecimal;

public class ComputerDao extends ProductDao {
    private int computerDaoID;
    private int computerDaoType;
    private String computerDaoIP;
    private String computerDaoMask;
    private String computerDaoGate;
    private String computerDaoMAC;
    private String compuetrDaoKind;
    private String computerDaoName;
    private String computerDaoWorkGroup;
    private String computerDaoSystem;
    private int computerDaoWorker;
    private String computerDaoSwitch;
    private String computerDaoPort;
    private String compuetrDaoSocket;
    private String computerDaoKey;

    public ComputerDao(int idDao, int kindDao, String nameDao, String serialNumberDao, String inventoryNumberDao, String evidentaialNumberDao, BigDecimal priceDao, int productionYearDao, int typeDao, int roomNumberDao, int departamentDao, String commentsDao, String categoryDao, int computerDaoID, int computerDaoType, String computerDaoIP, String computerDaoMask, String computerDaoGate, String computerDaoMAC, String compuetrDaoKind, String computerDaoName, String computerDaoWorkGroup, String computerDaoSystem, int computerDaoWorker, String computerDaoSwitch, String computerDaoPort, String compuetrDaoSocket, String computerDaoKey) {
        super(idDao, kindDao, nameDao, serialNumberDao, inventoryNumberDao, evidentaialNumberDao, priceDao, productionYearDao, typeDao, roomNumberDao, departamentDao, commentsDao, categoryDao);
        this.computerDaoID = computerDaoID;
        this.computerDaoType = computerDaoType;
        this.computerDaoIP = computerDaoIP;
        this.computerDaoMask = computerDaoMask;
        this.computerDaoGate = computerDaoGate;
        this.computerDaoMAC = computerDaoMAC;
        this.compuetrDaoKind = compuetrDaoKind;
        this.computerDaoName = computerDaoName;
        this.computerDaoWorkGroup = computerDaoWorkGroup;
        this.computerDaoSystem = computerDaoSystem;
        this.computerDaoWorker = computerDaoWorker;
        this.computerDaoSwitch = computerDaoSwitch;
        this.computerDaoPort = computerDaoPort;
        this.compuetrDaoSocket = compuetrDaoSocket;
        this.computerDaoKey = computerDaoKey;
    }

    public ComputerDao(int kindDao, String nameDao, String serialNumberDao, String inventoryNumberDao, String evidentaialNumberDao, BigDecimal priceDao, int productionYearDao, int typeDao, int roomNumberDao, int departamentDao, String commentsDao, String categoryDao, int computerDaoID, int computerDaoType, String computerDaoIP, String computerDaoMask, String computerDaoGate, String computerDaoMAC, String compuetrDaoKind, String computerDaoName, String computerDaoWorkGroup, String computerDaoSystem, int computerDaoWorker, String computerDaoSwitch, String computerDaoPort, String compuetrDaoSocket, String computerDaoKey) {
        super(kindDao, nameDao, serialNumberDao, inventoryNumberDao, evidentaialNumberDao, priceDao, productionYearDao, typeDao, roomNumberDao, departamentDao, commentsDao, categoryDao);
        this.computerDaoID = computerDaoID;
        this.computerDaoType = computerDaoType;
        this.computerDaoIP = computerDaoIP;
        this.computerDaoMask = computerDaoMask;
        this.computerDaoGate = computerDaoGate;
        this.computerDaoMAC = computerDaoMAC;
        this.compuetrDaoKind = compuetrDaoKind;
        this.computerDaoName = computerDaoName;
        this.computerDaoWorkGroup = computerDaoWorkGroup;
        this.computerDaoSystem = computerDaoSystem;
        this.computerDaoWorker = computerDaoWorker;
        this.computerDaoSwitch = computerDaoSwitch;
        this.computerDaoPort = computerDaoPort;
        this.compuetrDaoSocket = compuetrDaoSocket;
        this.computerDaoKey = computerDaoKey;
    }
}
