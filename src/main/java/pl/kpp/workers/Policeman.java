package pl.kpp.workers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.kpp.dao.workersDao.PolicemanDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Policeman {

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
    private static List<Policeman> policemanList = new ArrayList<>();

    public Range getPolicemanRange() {
        return policemanRange;
    }
    public static List<Policeman> getPolicemanList() {
        return policemanList;
    }
    public static void addToList(Policeman gpoliceman){
        policemanList.add(gpoliceman);
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
     * @param policemanDao - object reading from database
     */
    public Policeman(PolicemanDao policemanDao){
        name.set(policemanDao.getDaoName());
        surrname.set(policemanDao.getDaoSurname());
        ewidential.set(policemanDao.getDaoEwidential());
        pesel.set(policemanDao.getDaoPesel());
        id = policemanDao.getDaoId();
        if(policemanDao.getDaoRange() != 0) {
            policemanRange = Range.searchRange(policemanDao.getDaoRange());
        }
        else policemanRange = null;
        if (policemanDao.getDaoDepartament()!=0){
            for (Departament departament:Departament.getDepartamentList()){
                if(departament.getId()==policemanDao.getDaoDepartament()){
                    this.policemanDepartament=departament;
                   this.namePolicedepartament.set(departament.getName());
                }
            }
        }else{
            this.policemanDepartament=null;
            this.namePolicedepartament.set(" ");
        }
        if (policemanDao.getDaoRanks()!=0){
            for (Ranks ranks:Ranks.getRanksList()){
                if (ranks.getRanksId()==policemanDao.getDaoRanks()){
                    this.policemanRanks=ranks;
                }
            }
        }
        else
            this.policemanRanks = null;
        this.policemanIntranet=policemanDao.getDaoIntranet();
        this.policemanIntradok=policemanDao.getDaoIntradok();
        this.policemanLotus=policemanDao.getDaoLotus();
        this.policemanExchange=policemanDao.getDaoExchange();
        this.policemanCryptomail=policemanDao.getDaoCryptomail();
        this.policemanSWD=policemanDao.getDaoSWD();
    }

    /**
     * Serching Policeman
     * @param idPoliceman - id searched worker
     * @return - searched object or null
     */
    public static Policeman findPoliceman(int idPoliceman) {
        for (Policeman policeman: policemanList){
            if (idPoliceman==policeman.getId()){
                return policeman;
            }
        }
        return null;
    }

    /**
     * Create workers list
     * @return workers list
     */
    public static List<Policeman> createList(){
        policemanList.clear();
        for(PolicemanDao dao:PolicemanDao.getPolicemanDAOList()){
            Policeman policeman = new Policeman(dao);
            policemanList.add(policeman);
        }
        return  policemanList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policeman policeman = (Policeman) o;
        return id == policeman.id &&
                Objects.equals(name, policeman.name) &&
                Objects.equals(surrname, policeman.surrname) &&
                Objects.equals(ewidential, policeman.ewidential) &&
                Objects.equals(pesel, policeman.pesel) &&
                Objects.equals(policemanRange, policeman.policemanRange);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, surrname, ewidential, pesel, policemanRange, id);
    }
}
