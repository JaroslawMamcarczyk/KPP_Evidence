package pl.kpp.dao;

import java.sql.*;

public class Database {
        private final static String DBURL = "jdbc:sqlite:D:\\KPP_Evidence\\src\\main\\evidence.db";
        private final static String DBDRIVER = "org.sqlite.JDBC";
        private Connection con = null;
        private Statement stmt = null;

        public Connection getCon() {
            return con;
        }


        public Database() {
            try {
                Class.forName(DBDRIVER);
            } catch ( ClassNotFoundException e) {
                System.out.println("bład sterownika");
            }
            try {
                con = DriverManager.getConnection(DBURL);
                stmt = con.createStatement();
                System.out.println("podłączyłem bazę");
            }catch (SQLException e){
                System.out.println("bład zapytania");
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
    public void createTable(){
        try {
            PreparedStatement statement = con.prepareStatement("CREATE TABLE IF NOT EXISTS workers(id INTEGER not null constraint workers_pk primary key, " +
                    "name VARCHAR(50), surname VARCHAR(100), ewidential_number VARCHAR(6)," +
                    "pesel int,range int constraint workers_range_id_fk references range," +
                    "departament int constraint workers_departament_id_fk references departament," +
                    "ranks int constraint workers_ranks_id_fk references ranks)");
            statement.execute();
            System.out.println("utworzyłem tabele");
            statement = con.prepareStatement("CREATE TABLE IF NOT EXISTS departament(id INTEGER" +
                    "constraint departament_pk\n" +
                    "primary key, departament_name VARCHAR(200))");
            statement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
