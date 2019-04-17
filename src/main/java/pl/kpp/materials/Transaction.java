package pl.kpp.materials;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.kpp.dao.materialsDao.TransactionDao;
import pl.kpp.workers.Policeman;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Transaction {
    private int idTransaction;
    private Policeman transactionPoliceman;
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
            this.transactionPoliceman = Policeman.findPoliceman(transactionDao.getCustomerDao());
        }else if(type==2){
            this.transactionPoliceman = null;
            this.transactionDelivery = Deliverys.findDelivery(transactionDao.getDeliveryIdDao());
       }
        if(transactionPoliceman!=null){
            customerName.set(transactionPoliceman.getName()+" "+transactionPoliceman.getSurrname());
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
        transactionList.clear();
        for (TransactionDao transactionDao:TransactionDao.getTransactionDaoList()){
            Transaction transaction = new Transaction((transactionDao));
            transactionList.add(transaction);
        }
    }

    public static Transaction findTransaction(int idTransaction){
        for (Transaction transaction:transactionList){
            if(transaction.getIdTransaction()==idTransaction)
                return transaction;
        }
        return null;
    }
}
