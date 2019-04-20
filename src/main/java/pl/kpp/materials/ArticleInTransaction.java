package pl.kpp.materials;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.kpp.dao.materialsDao.ArticleInTransactionDao;
import java.util.ArrayList;
import java.util.List;

public class ArticleInTransaction {
    private int id;
    private int count;
    private StringProperty nameArticleInTransaction = new SimpleStringProperty();
    private Materials articleInTransactionMaterial;
    private Transaction articleInTransactionTransaction;
    private static List<ArticleInTransaction> articleInTransactionList = new ArrayList<>();

    public int getCount() {
        return count;
    }
    public static List<ArticleInTransaction> getArticleInTransactionList(){return articleInTransactionList;}
    public Transaction getArticleInTransactionTransaction(){return articleInTransactionTransaction;}
    public Materials getArticleInTransactionMaterial() {
        return articleInTransactionMaterial;
    }
    public String getNameArticleInTransaction() {return nameArticleInTransaction.get();}

    public ArticleInTransaction(ArticleInTransactionDao garticle){
        this.id = garticle.getDaoId();
        this.articleInTransactionMaterial = Materials.findmaterial(garticle.getDaoMaterialId());
        this.nameArticleInTransaction.set(this.articleInTransactionMaterial.getName());
        this.count = garticle.getDaoArticleInTransactionCount();
        this.articleInTransactionTransaction = Transaction.findTransaction(garticle.getDaoTransactionId());
    }

    public static void CreateArticleIntransactionList() {
        ArticleInTransactionDao.readArticleInTransaction();
        for (ArticleInTransactionDao articleInTransactionDao : ArticleInTransactionDao.getArticleInTransactionDaoList()) {
            ArticleInTransaction transaction = new ArticleInTransaction(articleInTransactionDao);
            articleInTransactionList.add(transaction);
        }
    }
}
