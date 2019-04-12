package pl.kpp.license;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ksip {
    private int id;
    private StringProperty name = new SimpleStringProperty();
    private StringProperty synopsis = new SimpleStringProperty();
    private boolean acess;
    private boolean read;
    private boolean insert;
    private  boolean modify;
    private  boolean delete;
    private StringProperty unity = new SimpleStringProperty();

    public void setAcess(boolean gacess) {
        if(gacess) {
            this.read = false;
            this.modify = false;
            this.insert = false;
            this.acess = gacess;
            this.delete = false;
        }else
            this.read = gacess;
    }

    public void setRead(boolean gread) {
        if(gread) {
            this.read = gread;
            this.modify = false;
            this.insert = false;
            this.acess = false;
            this.delete = false;
        }else
            this.read = gread;
    }

    public void setInsert(boolean ginsert) {
        if(ginsert) {
            this.read = false;
            this.modify = false;
            this.insert = ginsert;
            this.acess = false;
            this.delete = false;
        }else
            this.read = ginsert;
    }

    public void setModify(boolean gmodify) {
        if(gmodify) {
            this.read = false;
            this.modify = gmodify;
            this.insert = false;
            this.acess = false;
            this.delete = false;
        }else
            this.read = gmodify;
    }

    public void setDelete(boolean gdelete) {
        if(gdelete) {
            this.read = false;
            this.modify = false;
            this.insert = false;
            this.acess = false;
            this.delete = gdelete;
        }else
            this.read = gdelete;
    }

    public boolean isRead(){
        return read;
    }

    public boolean isAcess() {
        return acess;
    }

    public boolean isInsert() {
        return insert;
    }

    public boolean isModify() {
        return modify;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setName(String gname){this.name.set(gname);}

    public String getName() {
        return name.get();
    }

    public String getSynopsis() {
        return synopsis.get();
    }

    public String getUnity() {
        return unity.get();
    }


    public Ksip(String gname, String gdescript){
        this.name.set(gname);
        this.synopsis.set(gdescript);
        this.acess = true;
        this.delete = false;
        this.insert = false;
        this.read = false;
        this.modify = false;
    }

    public void showKsip(){
        System.out.println(this.name);
        System.out.println("dostęp: "+this.acess);
        System.out.println("odczyt: "+this.read);
        System.out.println("zapis: "+this.insert);
        System.out.println("modyfikowanie: "+this.modify);
        System.out.println("usuwanie: "+this.delete);
    }
}
