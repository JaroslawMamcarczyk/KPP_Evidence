package pl.kpp.dao;

import java.sql.*;

public class Database {
        private final static String DBURL = "jdbc:sqlite:evidence.db";
        private final static String DBDRIVER = "org.sqlite.JDBC";
        private Connection con = null;
        private Statement stmt = null;

        public Connection getCon() {
            return con;
        }


        public  Database() {
            try {
                Class.forName(DBDRIVER);
            } catch ( ClassNotFoundException e) {
                System.out.println("bład sterownika");
            }
            try {
                con = DriverManager.getConnection(DBURL);
                stmt = con.createStatement();
            }catch (SQLException e){
                createTable("create table IF NOT EXISTS workers(id INTEGER not null constraint workers_pk primary key," +
                        "worker_name VARCHAR(200) not null,worker_surname VARCHAR(200) not null," +
                        "worker_evidential  VARCHAR(6),worker_pesel VARCHAR(11),worker_range int" +
                        "references range,worker_departament int references departament," +
                        "worker_ranks int references ranks,intranet int,intradok int,lotus int,exchange int,"+
                        "cryptomail int,ksip int sespol int,swd int,column_17 int);" +
                        "create unique index workers_id_uindex on workers (id);");
                createTable("create table if not exists range(id INTEGER not null constraint range_pk primary key,"+
                        "range_name VARCHAR(200) not null, pagons     VARCHAR(200) );"+
                        "create unique index range_id_uindex on range (id);");
                createTable("create table if not exists ranks(id INTEGER not null constraint ranks_pk primary key,"+
                        "ranks_name VARCHAR(200) not null,ranks_departament int references departament on delete set null );"+
                        "create unique index ranks_id_uindex on ranks (id);");
                createTable("create table if not exists departament(id INTEGER not null constraint departament_pk primary key,"+
                        "departament_name VARCHAR(200) not null ); create unique index departament_id_uindex on departament (id);");
                createTable("create table if not exists transaction_list(id INTEGER not null constraint transaction_pk primary key,"+
                        "worker_id int references workers on delete set null, delivery_id int references deliverys on delete set null,"+
                        "transaction_number VARCHAR(100),transaction_date   DATE,type int);"+
                        "create unique index transaction_id_uindex on transaction_list (id);");
                createTable("create table if not exists materialsControllers(id INTEGER not null constraint materials_pk primary key,"+
                        "materials_name VARCHAR(100) not null, count int not null,materials_type VARCHAR(100) not null);"+
                        "create unique index materials_id_uindex on materialsControllers (id);");
                createTable("create table if not exists deliverys(id INTEGER not null constraint deliverys_pk primary key,"+
                        "delivery_name VARCHAR(200) not null,delivery_address VARCHAR(200));"+
                        "create unique index deliverys_id_uindex on deliverys (id);");
                createTable("create table if not exists article_in_transaction (id INTEGER not null constraint article_in_transaction_pk primary key,"+
                        "transaction_id int references transaction_list, material_id int references materialsControllers on delete set null,article_in_transaction_count int );"+
                        "create unique index article_in_transaction_id_uindex on article_in_transaction (id);");
                //    date.createTable("create table if not exists ksip_type(id INTEGER not null constraint ksip_type_pk primary key,"+
                //                 "ksip_name VARCHAR(100) not null ksip_descript VARCHAR(200) not null);");
                createTable("create table if not exists works(id INTEGER not null constraint works_pk primary key,job TEXT,status int,works_data date);");
                createTable("create table if not exists cards(id INTEGER not null constraint cards_pk primary key,card_number VARCHAR(16)," +
                        " card_worker int references workers on delete set null);");
                createTable("create table if not exists product(id INTEGER not null constraint product_pk primary key, product_kind int,product_name TEXT not null,"+
                        "product_serial     TEXT,product_inventory  TEXT,product_evidential int,product_price int,product_year int,product_type int,"+
                        "product_room VARCHAR(4),product_department int references departament on delete set null, product_comment TEXT);");
                createTable("create table if not exists building( id INTEGER not null constraint building_pk primary key,building_name TEXT not null,"+
                        "building_type int,building_parent int,building_x int,building_y int);");
                System.out.println("Nie znalazłem bazy danych");
            }
        }

    /**
     *
     * @param query - query (SELECT) in sql
     * @return - result of the query
     */
        public ResultSet select(String query) {
            ResultSet result = null;
            try{
                result = stmt.executeQuery(query);
            }catch (SQLException e){
                System.out.println("Błąd zapytania select z bazy " + e);
            }
            return result;
        }

        /**
         *
         * @param query - query (INSERT) in sql
         */
        public void insert(String query){
            try {
                stmt.execute(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    /**
     * closing connection
     */
        public void closeDatabase(){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    /**
     * creating table
     */
    public void createTable(String sql){
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
