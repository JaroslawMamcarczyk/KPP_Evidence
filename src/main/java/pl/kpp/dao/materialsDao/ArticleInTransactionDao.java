package pl.kpp.dao.materialsDao;

import pl.kpp.dao.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleInTransactionDao {
    private int daoId;
    private int daoTransactionId;
    private int daoMaterialId;
    private int daoArticleInTransactionCount;
    private static PreparedStatement statement;
    private static List<ArticleInTransactionDao> articleInTransactionDaoList = new ArrayList<>();

    public int getDaoId() {
        return daoId;
    }

    public int getDaoTransactionId() {
        return daoTransactionId;
    }

    public int getDaoMaterialId() {
        return daoMaterialId;
    }

    public int getDaoArticleInTransactionCount() {
        return daoArticleInTransactionCount;
    }

    public static List<ArticleInTransactionDao> getArticleInTransactionDaoList() {
        return articleInTransactionDaoList;
    }

    public ArticleInTransactionDao(int gid, int gtransaction, int gproduckt, int gcount){
        this.daoArticleInTransactionCount = gcount;
        this.daoTransactionId = gtransaction;
        this.daoMaterialId = gproduckt;
        this.daoId = gid;
    }

    public ArticleInTransactionDao(int gtransaction, int gproduckt, int gcount){
        this.daoArticleInTransactionCount = gcount;
        this.daoMaterialId = gproduckt;
        this.daoTransactionId = gtransaction;
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
            statement.setInt(1, this.daoTransactionId);
            statement.setInt(2, this.daoMaterialId);
            statement.setInt(3, this.daoArticleInTransactionCount);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Nie zapisałem - cos nie pykło");
        }
        date.closeDatabase();
    }
}
