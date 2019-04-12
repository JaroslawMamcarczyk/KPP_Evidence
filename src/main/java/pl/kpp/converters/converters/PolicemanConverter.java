package pl.kpp.converters.converters;

import javafx.util.StringConverter;
import pl.kpp.workers.Policeman;

public class PolicemanConverter extends StringConverter<Policeman> {
    @Override
    public String toString(Policeman policeman) {
        return policeman.getName()+" "+policeman.getSurrname();
    }

    @Override
    public Policeman fromString(String s) {
        return null;
    }
}
