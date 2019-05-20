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
