package pl.kpp.workers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.kpp.dao.workersDao.DepartamentDao;

import java.util.ArrayList;
import java.util.List;

public class Departament {
    private StringProperty name = new SimpleStringProperty();
    private int id;
    private static List<Departament> departamentList = new ArrayList<>();

    public String getName() {
        return name.get();
    }
    public int getId() {
        return id;
    }
    public void setName(String gname){this.name.set(gname);}

    public static List<Departament> getDepartamentList() { return departamentList; }
    public Departament(DepartamentDao dao){
        name.set(dao.getName());
        id = dao.getId();
    }

    public static Departament findDepartament(int serchingDepartament){
        for (Departament departament:getDepartamentList()){
            if(departament.getId()==serchingDepartament){
                return departament;
            }
        }return null;
    }
}
