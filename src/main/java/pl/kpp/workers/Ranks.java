package pl.kpp.workers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pl.kpp.dao.workersDao.RanksDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ranks {
    private int ranksId;
    private StringProperty nameRanks = new SimpleStringProperty();
    private Departament departamentRanks;
    private static List<Ranks> ranksList = new ArrayList<>();

    public int getRanksId() {
        return ranksId;
    }
    public String getNameRanks() {
        return nameRanks.get();
    }
    public Departament getDepartamentRanks() {
        return departamentRanks;
    }
    public static List<Ranks> getRanksList() { return ranksList; }

    public Ranks(RanksDao ranksDAO) {
        this.ranksId = ranksDAO.getIdRanksDao();
        this.departamentRanks = Departament.findDepartament(ranksDAO.getDepartamentRanksDao());
        this.nameRanks.set(ranksDAO.getNameRanksDao());
    }

    public static void createRanksList(){
        ranksList.clear();
        for(RanksDao dao: RanksDao.getRanksDaoList()){
            ranksList.add(new Ranks(dao));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ranks)) return false;
        Ranks ranks = (Ranks) o;
        return getRanksId() == ranks.getRanksId() &&
                getDepartamentRanks() == ranks.getDepartamentRanks() &&
                getNameRanks().equals(ranks.getNameRanks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRanksId(), getNameRanks(), getDepartamentRanks());
    }
}
