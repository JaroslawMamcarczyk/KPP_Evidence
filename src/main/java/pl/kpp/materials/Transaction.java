package pl.kpp.materials;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.kpp.dao.materialsDao.TransactionDao;
import pl.kpp.workers.Worker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Transaction {
    private int idTransaction;
    private Worker transactionWorker;
    private Deliverys transactionDelivery;
    private int type;
    private StringProperty customerName = new SimpleStringProperty();
    private StringProperty nameTransaction = new SimpleStringProperty();
    private Date dateTransaction;
    private static List<Transaction> transactionList = new ArrayList<>();




    public Transaction(TransactionDao transactionDao){
        this.idTransaction = transactionDao.getIdTransactionDao();
        this.dateTransaction = transactionDao.getDateTransactionDao();
        this.nameTransaction.set(transactionDao.getNumberTransactionDao());
        this.type = transactionDao.getTypeDao();
        if (type == 1){
            this.transactionDelivery = null;
            this.transactionWorker = Worker.findWorker(transactionDao.getCustomerDao());
        }else if(type==2){
            this.transactionWorker = null;
            this.transactionDelivery = Deliverys.findDelivery(transactionDao.getDeliveryIdDao());
       }
        if(transactionWorker !=null){
            customerName.set(transactionWorker.getName()+" "+ transactionWorker.getSurrname());
        }else if(transactionDelivery!=null){
            customerName.set(transactionDelivery.getName());
        }

    }
    public int getType() {return type;}
    public int getIdTransaction() {
        return idTransaction;
    }
    public String getCustomerName() {return customerName.get();}
    public String getNameTransaction() {
        return nameTransaction.get();
    }
    public Date getDateTransaction() {
        return dateTransaction;
    }
    public static List<Transaction> getTransactionList() {
        return transactionList;
    }

    public static void createTransactionList(){
        TransactionDao.readTransaction();
    }

    public static Transaction findTransaction(int idTransaction){
        for (Transaction transaction:transactionList){
            if(transaction.getIdTransaction()==idTransaction)
                return transaction;
        }
        return null;
    }
}
