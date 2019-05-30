package pl.kpp.product;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.kpp.dao.productDao.ComputerDao;
import pl.kpp.dao.productDao.ProductDao;
import pl.kpp.workers.Worker;

import java.util.ArrayList;
import java.util.List;

public class Computer extends Product {
    private int computerID;
    private int computerType;
    private int computerProduct;
    private String computerIP;
    private String computerMask;
    private String computerGate;
    private String computerMAC;
    private String computerName;
    private String computerWorkGroup;
    private String computerSystem;
    private Worker computerWorker;
    private String computerSwitch;
    private String computerPort;
    private String compuetrSocket;
    private String computerKey;
    private StringProperty computerOwner = new SimpleStringProperty();
    private static List<Computer> computerList = new ArrayList<>();

    public int getComputerID() {
        return computerID;
    }
    public int getComputerType() {
        return computerType;
    }
    public int getComputerProduct() {
        return computerProduct;
    }
    public String getComputerIP() {
        return computerIP;
    }
    public String getComputerMask() {
        return computerMask;
    }

    public String getComputerGate() {
        return computerGate;
    }
    public String getComputerMAC() {
        return computerMAC;
    }
    public String getComputerName() {
        return computerName;
    }
    public String getComputerWorkGroup() {
        return computerWorkGroup;
    }
    public String getComputerSystem() {
        return computerSystem;
    }
    public String getComputerSwitch() {
        return computerSwitch;
    }
    public String getComputerPort() {
        return computerPort;
    }
    public String getCompuetrSocket() {
        return compuetrSocket;
    }
    public String getComputerKey() {
        return computerKey;
    }
    public String getComputerOwner(){
        return computerOwner.get();
    }
    public static List<Computer> getComputerList(){
        return computerList;
    }

    public Computer(ProductDao productDao, ComputerDao computerDao) {
        super(productDao);
        this.computerID = computerDao.getComputerDaoID();
        this.computerType = computerDao.getComputerDaoType();
        this.computerProduct = computerDao.getComputerDaoProduct();
        this.computerIP = computerDao.getComputerDaoIP();
        this.computerMask = computerDao.getComputerDaoMask();
        this.computerGate = computerDao.getComputerDaoGate();
        this.computerMAC = computerDao.getComputerDaoMAC();
        this.computerName = computerDao.getComputerDaoName();
        this.computerWorkGroup = computerDao.getComputerDaoWorkGroup();
        this.computerSystem = computerDao.getComputerDaoSystem();
        if(computerDao.getComputerDaoWorker()!=0){
          this.computerWorker= Worker.findWorker(computerDao.getComputerDaoWorker());
        }else {this.computerWorker=null;}
        this.computerSwitch = computerDao.getComputerDaoSwitch();
        this.computerPort = computerDao.getComputerDaoPort();
        this.compuetrSocket = computerDao.getCompuetrDaoSocket();
        this.computerKey = computerDao.getComputerDaoKey();
        this.computerOwner.set(this.computerWorker.getName()+" "+this.computerWorker.getSurrname());
    }

}


