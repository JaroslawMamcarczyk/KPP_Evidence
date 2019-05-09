package pl.kpp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.kpp.dao.Database;
import pl.kpp.dao.licenseDao.KsipTypeDao;
import pl.kpp.dao.workersDao.DepartamentDao;
import pl.kpp.dao.workersDao.WorkerDao;
import pl.kpp.dao.workersDao.RangeDao;
import pl.kpp.dao.workersDao.RanksDao;
import pl.kpp.workers.Range;


import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Database date = new Database();
        date.createTable("create table IF NOT EXISTS workers(id INTEGER not null constraint workers_pk primary key," +
                        "worker_name VARCHAR(200) not null,worker_surname VARCHAR(200) not null," +
                        "worker_evidential  VARCHAR(6),worker_pesel VARCHAR(11),worker_range int" +
                        "references range,worker_departament int references departament," +
                        "worker_ranks int references ranks,intranet int,intradok int,lotus int,exchange int,"+
                        "cryptomail int,ksip int sespol int,swd int,column_17 int);" +
                        "create unique index workers_id_uindex on workers (id);");
        date.createTable("create table if not exists range(id INTEGER not null constraint range_pk primary key,"+
                        "range_name VARCHAR(200) not null, pagons     VARCHAR(200) );"+
                        "create unique index range_id_uindex on range (id);");
        date.createTable("create table if not exists ranks(id INTEGER not null constraint ranks_pk primary key,"+
                            "ranks_name VARCHAR(200) not null,ranks_departament int references departament on delete set null );"+
                            "create unique index ranks_id_uindex on ranks (id);");
        date.createTable("create table if not exists departament(id INTEGER not null constraint departament_pk primary key,"+
                         "departament_name VARCHAR(200) not null ); create unique index departament_id_uindex on departament (id);");
        date.createTable("create table if not exists transaction_list(id INTEGER not null constraint transaction_pk primary key,"+
                        "worker_id int references workers on delete set null, delivery_id int references deliverys on delete set null,"+
                        "transaction_number VARCHAR(100),transaction_date   DATE,type int);"+
                        "create unique index transaction_id_uindex on transaction_list (id);");
        date.createTable("create table if not exists materials(id INTEGER not null constraint materials_pk primary key,"+
                            "materials_name VARCHAR(100) not null, count int not null,materials_type VARCHAR(100) not null);"+
                            "create unique index materials_id_uindex on materials (id);");
        date.createTable("create table if not exists deliverys(id INTEGER not null constraint deliverys_pk primary key,"+
                         "delivery_name VARCHAR(200) not null,delivery_address VARCHAR(200));"+
                        "create unique index deliverys_id_uindex on deliverys (id);");
        date.createTable("create table if not exists article_in_transaction (id INTEGER not null constraint article_in_transaction_pk primary key,"+
                            "transaction_id int references transaction_list, material_id int references materials on delete set null,article_in_transaction_count int );"+
                            "create unique index article_in_transaction_id_uindex on article_in_transaction (id);");
    //    date.createTable("create table if not exists ksip_type(id INTEGER not null constraint ksip_type_pk primary key," +
           //                 "ksip_name VARCHAR(100) not null ksip_descript VARCHAR(200) not null);");
        date.createTable("create table if not exists works(\n" +
                "    id         INTEGER not null\n" +
                "        constraint works_pk\n" +
                "            primary key,\n" +
                "    job        TEXT,\n" +
                "    status     int,\n" +
                "    works_data date\n" +
                ");");
        DepartamentDao.readDepartament(date);
        RanksDao.readRanks(date);
        RangeDao.readRange(date);
        Range.createRangeList();
        WorkerDao.readWorkers(date);
      //  KsipTypeDao.readKsip();

        date.closeDatabase();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/FXML/MainScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("KPP Ewidential");
        primaryStage.setScene(new Scene(root, 1366, 768));
        String cssPath = this.getClass().getResource("/css/mainScreenCss.css").toExternalForm();
        root.getStylesheets().add(cssPath);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
