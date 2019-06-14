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
    private StringProperty computerIP= new SimpleStringProperty();
    private StringProperty computerMask= new SimpleStringProperty();
    private StringProperty computerGate= new SimpleStringProperty();
    private StringProperty computerMAC= new SimpleStringProperty();
    private StringProperty computerName= new SimpleStringProperty();
    private StringProperty computerWorkGroup= new SimpleStringProperty();
    private StringProperty computerSystem= new SimpleStringProperty();
    private Worker computerWorker;
    private StringProperty computerSwitch= new SimpleStringProperty();
    private StringProperty computerPort= new SimpleStringProperty();
    private StringProperty computerSocket= new SimpleStringProperty();
    private StringProperty computerKey= new SimpleStringProperty();
    private StringProperty computerOwner = new SimpleStringProperty();
    private StringProperty computerEwidential = new SimpleStringProperty();
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
        return computerIP.get();
    }
    public String getComputerMask() {
        return computerMask.get();
    }
    public String getComputerGate() {
        return computerGate.get();
    }
    public String getComputerMAC() {
        return computerMAC.get();
    }
    public String getComputerName() {
        return computerName.get();
    }
    public String getComputerWorkGroup() {
        return computerWorkGroup.get();
    }
    public String getComputerSystem() {
        return computerSystem.get();
    }
    public String getComputerSwitch() {
        return computerSwitch.get();
    }
    public String getComputerPort() {
        return computerPort.get();
    }
    public String getComputerSocket() {
        return computerSocket.get();
    }
    public String getComputerKey() {
        return computerKey.get();
    }
    public String getComputerOwner(){
        return computerOwner.get();
    }
    public String getComputerEwidential(){return computerEwidential.get();}
    public static List<Computer> getComputerList(){
        return computerList;
    }

    public Computer(ProductDao productDao, ComputerDao computerDao) {
        super(productDao);
        this.computerID = computerDao.getComputerDaoID();
        this.computerType = computerDao.getComputerDaoType();
        this.computerProduct = computerDao.getComputerDaoProduct();
        this.computerIP.set(computerDao.getComputerDaoIP());
        this.computerMask.set(computerDao.getComputerDaoMask());
        this.computerGate.set(computerDao.getComputerDaoGate());
        this.computerMAC.set(computerDao.getComputerDaoMAC());
        this.computerName.set(computerDao.getComputerDaoName());
        this.computerWorkGroup.set(computerDao.getComputerDaoWorkGroup());
        this.computerSystem.set(computerDao.getComputerDaoSystem());
        this.computerEwidential.set(productDao.getEvidentaialNumberDao());
        if(computerDao.getComputerDaoWorker()!=0){
          this.computerWorker= Worker.findWorker(computerDao.getComputerDaoWorker());
        }else {this.computerWorker=null;}
        this.computerSwitch.set(computerDao.getComputerDaoSwitch());
        this.computerPort.set(computerDao.getComputerDaoPort());
        this.computerSocket.set(computerDao.getCompuetrDaoSocket());
        this.computerKey.set(computerDao.getComputerDaoKey());
        this.computerOwner.set(this.computerWorker.getName()+" "+this.computerWorker.getSurrname());
    }


}


