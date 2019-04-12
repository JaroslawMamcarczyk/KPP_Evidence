package pl.kpp.converters.converters;


import javafx.util.StringConverter;
import pl.kpp.workers.Departament;
import pl.kpp.workers.Ranks;

public class RanksConverter extends StringConverter<Ranks> {
    @Override
    public String toString(Ranks ranks) {
        return ranks.getNameRanks()+"( "+ Departament.findDepartament(ranks.getDepartamentRanks()).getName()+" )";
    }

    @Override
    public Ranks fromString(String s) {
        for (Ranks ranks:Ranks.getRanksList()){
            if(ranks.getNameRanks().equals(s)){
                return ranks;
            }
        }
        return  null;
    }
}
