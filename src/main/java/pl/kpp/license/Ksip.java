package pl.kpp.license;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.kpp.dao.licenseDao.KsipTypeDao;
import pl.kpp.workers.Worker;

import java.util.ArrayList;
import java.util.List;

public class Ksip {
    private int id;
    private StringProperty name = new SimpleStringProperty();
    private StringProperty synopsis = new SimpleStringProperty();
    private boolean acess;
    private boolean read;
    private boolean insert;
    private boolean modify;
    private boolean delete;
    private StringProperty unity = new SimpleStringProperty();
    private Worker ksipWorker;
    private static List<Ksip> ksipList = new ArrayList<>();

    public static List<Ksip> getKsipList() {
        return ksipList;
    }

    public Ksip(KsipTypeDao dao) {
        this.id = dao.getDaoId();
        this.name.set(dao.getDaoName());
        this.synopsis.set(dao.getDaoDescrib());
//        switch (dao.getDaoLevel()){
//            case 1:{
//                this.acess = true;
//                this.read = false;
//                this.insert = false;
//                this.modify = false;
//                this.delete = false;
//                break;
//            }
//            case 2:{
//                this.acess = false;
//                this.read = true;
//                this.insert = false;
//                this.modify = false;
//                this.delete = false;
//                break;
//            }
//            case 3:{
//                this.acess = false;
//                this.read = false;
//                this.insert = true;
//                this.modify = false;
//                this.delete = false;
//                break;
//            }
//            case 4:{
//                this.acess = false;
//                this.read = false;
//                this.insert = false;
//                this.modify = true;
//                this.delete = false;
//                break;
//            }
//            case 5:{
//                this.acess = false;
//                this.read = false;
//                this.insert = false;
//                this.modify = false;
//                this.delete = true;
//                break;
//            }
//        }
//        this.unity.set(dao.getDaoUnity());
//        this.ksipWorker=Worker.findWorker(dao.getDaoKsipWorkers());
//    }

//    public void setAcess(boolean gacess) {
//        if(gacess) {
//            this.read = false;
//            this.modify = false;
//            this.insert = false;
//            this.acess = gacess;
//            this.delete = false;
//        }else
//            this.read = gacess;
//    }
//
//    public void setRead(boolean gread) {
//        if(gread) {
//            this.read = gread;
//            this.modify = false;
//            this.insert = false;
//            this.acess = false;
//            this.delete = false;
//        }else
//            this.read = gread;
//    }
//
//    public void setInsert(boolean ginsert) {
//        if(ginsert) {
//            this.read = false;
//            this.modify = false;
//            this.insert = ginsert;
//            this.acess = false;
//            this.delete = false;
//        }else
//            this.read = ginsert;
//    }
//
//    public void setModify(boolean gmodify) {
//        if(gmodify) {
//            this.read = false;
//            this.modify = gmodify;
//            this.insert = false;
//            this.acess = false;
//            this.delete = false;
//        }else
//            this.read = gmodify;
//    }
//
//    public void setDelete(boolean gdelete) {
//        if(gdelete) {
//            this.read = false;
//            this.modify = false;
//            this.insert = false;
//            this.acess = false;
//            this.delete = gdelete;
//        }else
//            this.read = gdelete;
//    }
//
//    public boolean isRead(){
//        return read;
//    }
//
//    public boolean isAcess() {
//        return acess;
//    }
//
//    public boolean isInsert() {
//        return insert;
//    }
//
//    public boolean isModify() {
//        return modify;
//    }
//
//    public boolean isDelete() {
//        return delete;
//    }
//
//    public void setName(String gname){this.name.set(gname);}
//
//    public String getName() {
//        return name.get();
//    }
//
//    public String getSynopsis() {
//        return synopsis.get();
//    }
//
//    public String getUnity() {
//        return unity.get();
//    }
//
//
//    public Ksip(String gname, String gdescript){
//        this.name.set(gname);
//        this.synopsis.set(gdescript);
//        this.acess = true;
//        this.delete = false;
//        this.insert = false;
//        this.read = false;
//        this.modify = false;
//    }
//
//    public void showKsip(){
//        System.out.println(this.name);
//        System.out.println("dostęp: "+this.acess);
//        System.out.println("odczyt: "+this.read);
//        System.out.println("zapis: "+this.insert);
//        System.out.println("modyfikowanie: "+this.modify);
//        System.out.println("usuwanie: "+this.delete);
//    }
    }
}