package pl.kpp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.kpp.dao.WorksDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Works {
    private int id;
    private StringProperty job = new SimpleStringProperty();
    private int status;
    private Date worksDate;
    private List<Works> worksList = new ArrayList<>();

    public int getId() { return id; }
    public String getJob() { return job.get(); }
    public StringProperty jobProperty() { return job; }
    public int getStatus() { return status; }
    public Date getWorksDate() { return worksDate; }

    public List<Works> getWorksList() {
        return worksList;
    }

    public Works(WorksDao worksDao) {
        this.id = worksDao.getIdDao();
        this.job.set(worksDao.getJobDao());
        this.status = worksDao.getStatusDao();
        this.worksDate=worksDao.getWorksDate();
    }
}
