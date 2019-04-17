package pl.kpp.dao.materialsDao;

import pl.kpp.dao.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleInTransactionDao {
    private int daoTransactionID;
    private int daoIdArticle;
    private int daoIdProduct;
    private int daoCountArticle;
    private static PreparedStatement statement;
    private static List<ArticleInTransactionDao> articleInTransactionDaoList = new ArrayList<>();

    public int getDaoTransactionID() {
        return daoTransactionID;
    }

    public int getDaoIdArticle() {
        return daoIdArticle;
    }

    public int getDaoIdProduckt() {
        return daoIdProduct;
    }

    public int getDaoCountArticle() {
        return daoCountArticle;
    }

    public static List<ArticleInTransactionDao> getArticleInTransactionDaoList() {
        return articleInTransactionDaoList;
    }

    public ArticleInTransactionDao(int gid, int gtransaction, int gproduckt, int gcount){
        this.daoCountArticle = gcount;
        this.daoIdArticle = gid;
        this.daoIdProduct = gproduckt;
        this.daoTransactionID = gtransaction;
    }

    public ArticleInTransactionDao(int gtransaction, int gproduckt, int gcount){
        this.daoCountArticle = gcount;
        this.daoIdProduct = gproduckt;
        this.daoTransactionID = gtransaction;
    }
    public static void readArticleInTransaction(){
        Database date = new Database();
        articleInTransactionDaoList.clear();
        try (ResultSet result = date.select("SELECT * from article_In_Transaction")) {
            while (result.next()) {
               ArticleInTransactionDao articleInTransactionDAO = new ArticleInTransactionDao(result.getInt("id"),result.getInt("transaction_id"),
                       result.getInt("material_id"), result.getInt("article_in_transaction_count"));
               articleInTransactionDaoList.add(articleInTransactionDAO);
            }
        }catch (SQLException e){
            System.out.println("błąd odczytu tabeli artykułu");
        }
        date.closeDatabase();
    }

    public void saveArticleInTransaction(){
        Database date = new Database();
        try {
            statement = date.getCon().prepareStatement("INSERT INTO article_in_transaction (transaction_id, material_id, article_in_transaction_count) VALUES (?,?,?)");
            statement.setInt(1, this.daoTransactionID);
            statement.setInt(2, this.daoIdProduct);
            statement.setInt(3, this.daoCountArticle);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Nie zapisałem - cos nie pykło");
        }
        date.closeDatabase();
    }
}
