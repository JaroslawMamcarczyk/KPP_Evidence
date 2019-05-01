package pl.kpp.workers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.kpp.dao.workersDao.WorkerDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Worker {

    private int id;
    private StringProperty name = new SimpleStringProperty();
    private StringProperty surrname = new SimpleStringProperty();
    private StringProperty ewidential = new SimpleStringProperty();
    private StringProperty pesel = new SimpleStringProperty();
    private Range policemanRange;
    private Departament policemanDepartament;
    private StringProperty namePolicedepartament = new SimpleStringProperty();
    private Ranks policemanRanks;
    private  int policemanIntranet;
    private  int policemanIntradok;
    private  int policemanLotus;
    private  int policemanExchange;
    private  int policemanCryptomail;
    private int policemanSWD;
    private static List<Worker> worekrList = new ArrayList<>();

    public Range getPolicemanRange() {
        return policemanRange;
    }
    public static List<Worker> getWorekrList() {
        return worekrList;
    }
    public static void addToList(Worker gpoliceman){
        worekrList.add(gpoliceman);
    }
    public String getName() {
        return name.get();
    }
    public void setName(String gname) {
        this.name.set(gname);
    }
    public String getSurrname() {
        return surrname.get();
    }
    public String getEwidential() {
        return ewidential.get();
    }
    public String getPesel() {
        return pesel.get();
    }
    public int getId(){return id; }
    public String getNamePoliceDepartament(){ return namePolicedepartament.get();}
    public Departament getPolicemanDepartament() { return policemanDepartament;}
    public Ranks getPolicemanRanks(){return  policemanRanks;}

    public int getPolicemanIntranet() {
        return policemanIntranet;
    }

    public int getPolicemanIntradok() {
        return policemanIntradok;
    }

    public int getPolicemanLotus() {
        return policemanLotus;
    }

    public int getPolicemanExchange() {
        return policemanExchange;
    }

    public int getPolicemanCryptomail() {
        return policemanCryptomail;
    }

    public int getPolicemanSWD() {
        return policemanSWD;
    }

    /**
     * Constructor
     * @param workerDao - object reading from database
     */
    public Worker(WorkerDao workerDao){
        name.set(workerDao.getDaoName());
        surrname.set(workerDao.getDaoSurname());
        ewidential.set(workerDao.getDaoEwidential());
        pesel.set(workerDao.getDaoPesel());
        id = workerDao.getDaoId();
        if(workerDao.getDaoRange() != 0) {
            policemanRange = Range.searchRange(workerDao.getDaoRange());
        }
        else policemanRange = null;
        if (workerDao.getDaoDepartament()!=0){
            for (Departament departament:Departament.getDepartamentList()){
                if(departament.getId()== workerDao.getDaoDepartament()){
                    this.policemanDepartament=departament;
                   this.namePolicedepartament.set(departament.getName());
                }
            }
        }else{
            this.policemanDepartament=null;
            this.namePolicedepartament.set(" ");
        }
        if (workerDao.getDaoRanks()!=0){
            for (Ranks ranks:Ranks.getRanksList()){
                if (ranks.getRanksId()== workerDao.getDaoRanks()){
                    this.policemanRanks=ranks;
                }
            }
        }
        else
            this.policemanRanks = null;
        this.policemanIntranet= workerDao.getDaoIntranet();
        this.policemanIntradok= workerDao.getDaoIntradok();
        this.policemanLotus= workerDao.getDaoLotus();
        this.policemanExchange= workerDao.getDaoExchange();
        this.policemanCryptomail= workerDao.getDaoCryptomail();
        this.policemanSWD= workerDao.getDaoSWD();
    }

    /**
     * Serching Worker
     * @param idPoliceman - id searched worker
     * @return - searched object or null
     */
    public static Worker findWorker(int idPoliceman) {
        for (Worker worker : worekrList){
            if (idPoliceman== worker.getId()){
                return worker;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return id == worker.id &&
                Objects.equals(name, worker.name) &&
                Objects.equals(surrname, worker.surrname) &&
                Objects.equals(ewidential, worker.ewidential) &&
                Objects.equals(pesel, worker.pesel) &&
                Objects.equals(policemanRange, worker.policemanRange);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, surrname, ewidential, pesel, policemanRange, id);
    }
}
