package pl.kpp.dao.materialsDao;
import pl.kpp.dao.Database;
import pl.kpp.materials.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDao {
    private int customerDao;
    private int idTransactionDao;
    private String numberTransactionDao;
    private Date dateTransactionDao;
    private int typeDao;
    private int deliveryIdDao;
    private PreparedStatement statement;

    public TransactionDao(int idTransactionDao, int customerDao, int deliveryDao, String numberTransactionDao, Date date, int typeDao) {
        this.customerDao = customerDao;
        this.idTransactionDao = idTransactionDao;
        this.numberTransactionDao = numberTransactionDao;
        this.dateTransactionDao = date;
        this.typeDao = typeDao;
        this.deliveryIdDao = deliveryDao;
    }

    public TransactionDao(int idcustomer, String number){
        this.customerDao=idcustomer;
        this.numberTransactionDao = number;
    }

    public TransactionDao(int customerDao, int deliverysDao, String numberTransactionDao, String date, int typeDao) {
        this.deliveryIdDao = deliverysDao;
        this.numberTransactionDao = numberTransactionDao;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd");
        try {
            this.dateTransactionDao = new java.sql.Date(f.parse(date).getTime());
        } catch (ParseException e) {
        e.printStackTrace();
    }
        this.typeDao = typeDao;
        this.customerDao = customerDao;
    }

    public int getCustomerDao() {
        return customerDao;
    }
    public int getIdTransactionDao() {
        return idTransactionDao;
    }
    public String getNumberTransactionDao() {
        return numberTransactionDao;
    }
    public Date getDateTransactionDao(){
        return dateTransactionDao;
    }
    public int getTypeDao(){return typeDao;}
    public int getDeliveryIdDao() {return deliveryIdDao;}

    public static void readTransaction(){
        Database date = new Database();
        Transaction.getTransactionList().clear();
        try (ResultSet result = date.select("SELECT * FROM transaction_list ORDER BY transaction_date DESC, transaction_number DESC")) {
           while (result.next()) {
                TransactionDao transactionDao = new TransactionDao(result.getInt(1),result.getInt(2),result.getInt(3),
                result.getString(4), result.getDate(5), result.getInt(6));
               Transaction transaction = new Transaction(transactionDao);
                Transaction.getTransactionList().add(transaction);
            }
        }catch (SQLException e){
            System.out.println("błąd odczytu tabeli transakcji");
        }
        date.closeDatabase();
    }

    public void saveTransaction(int gout){
        long milliseconds=0;
        Database date = new Database();
        try{
            statement = date.getCon().prepareStatement("INSERT INTO transaction_list(worker_id, transaction_number, transaction_date, type, delivery_id) VALUES (?,?,?,?,?)");
            statement.setString(2, this.numberTransactionDao);
            if (gout==1) {
                statement.setInt(1, this.customerDao);
                statement.setInt(5,0);
                statement.setInt(4, 1);
                long time = System.currentTimeMillis();
                java.sql.Date currentDate = new java.sql.Date(time);
                statement.setDate(3,currentDate);
            }else if(gout==2){
                statement.setInt(5, this.deliveryIdDao);
                statement.setInt(1,0);
                SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd");
                try {
                    Date dateTransaction = f.parse(this.dateTransactionDao.toString());
                    milliseconds = dateTransaction.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                statement.setDate(3,new java.sql.Date(milliseconds));
                statement.setInt(4,2);
            }
            statement.execute();
        }catch (SQLException e){
            System.out.println("Nie zapisałem, błąd w dostepie do tabeli TransactionList");
        }
        date.closeDatabase();
    }

    public static int getLastUsingId(){
        Database date=new Database();
        int lastGeneratedID=0;
        try{
        Statement stat = date.getCon().createStatement();
        ResultSet result= stat.executeQuery("SELECT MAX(id) from transaction_list DESC LIMIT 1");
                lastGeneratedID = result.getInt(1);
        }catch (SQLException e){
            System.out.println("Nie pobrałem ostatniego ID");
        }
        date.closeDatabase();
        return lastGeneratedID;

    }

    public static TransactionDao getLastSavedTransaction(){
        Database date = new Database();
        try{
            Statement stat = date.getCon().createStatement();
            ResultSet result= stat.executeQuery("SELECT * from transaction_list WHERE type=1 ORDER BY id DESC LIMIT 1");
            if(result!=null) {
                TransactionDao transaction = new TransactionDao(result.getInt(1), result.getInt(2), result.getInt(3),
                        result.getString(4), result.getDate(5), result.getInt(6));
                date.closeDatabase();
                return transaction;
            }
        }catch(SQLException s){
            System.out.println("Błąd zapytania - nie pobrano danych z tabeli");
        }
        date.closeDatabase();
        return null;
    }
}
