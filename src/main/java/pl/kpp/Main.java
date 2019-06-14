package pl.kpp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.kpp.dao.Database;
import pl.kpp.dao.buildingDao.BuildingDao;
import pl.kpp.dao.licenseDao.KsipTypeDao;
import pl.kpp.dao.productDao.ComputerDao;
import pl.kpp.dao.productDao.ProductDao;
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
                "cryptomail int,ksip int,sespol int,swd int);" +
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
                "transaction_id int references transaction_list, material_id int references materialsControllers on delete set null,article_in_transaction_count int );"+
                "create unique index article_in_transaction_id_uindex on article_in_transaction (id);");
        //    date.createTable("create table if not exists ksip_type(id INTEGER not null constraint ksip_type_pk primary key,"+
        //                 "ksip_name VARCHAR(100) not null ksip_descript VARCHAR(200) not null);");
        date.createTable("create table if not exists works(id INTEGER not null constraint works_pk primary key,job TEXT,status int,works_data date);");
        date.createTable("create table if not exists cards(id INTEGER not null constraint cards_pk primary key,card_number VARCHAR(16)," +
                " card_worker int references workers on delete set null);");
        date.createTable("create table if not exists product(id INTEGER not null constraint product_pk primary key, product_kind int,product_name TEXT not null,"+
                "product_serial TEXT,product_inventory  TEXT,product_evidential TEXT,product_price NUMERIC,product_year int,product_type int,product_category TEXT,"+
                "product_room VARCHAR(4),product_department int references departament on delete set null, product_comment TEXT);");
        date.createTable("create table if not exists building( id INTEGER not null constraint building_pk primary key,building_name TEXT not null,"+
                "building_type int,building_parent int,building_x int,building_y int);");
        date.createTable("create table if not exists computers(id INTEGER not null constraint computers_pk primary key,\n" +
                "computer_type int,computer_product int references product on delete set null,computer_ip TEXT,computer_mask TEXT,\n" +
                "computer_gate TEXT,computer_mac TEXT,computer_name TEXT,computer_work_group TEXT,computer_system TEXT,computer_worker int\n" +
                "references workers on delete set null,computer_switch TEXT,computer_port TEXT,computer_socket TEXT,computer_key TEXT);");
        DepartamentDao.readDepartament(date);
        RanksDao.readRanks(date);
        RangeDao.readRange(date);
        Range.createRangeList();
        WorkerDao.readWorkers(date);
        BuildingDao.readBuilding(date);
        ProductDao.readProduckt();
        ComputerDao.readComputer();
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
