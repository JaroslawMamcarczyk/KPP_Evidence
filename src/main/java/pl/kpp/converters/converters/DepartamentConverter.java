package pl.kpp.converters.converters;


import javafx.util.StringConverter;
import pl.kpp.workers.Departament;

public class DepartamentConverter extends StringConverter<Departament> {

    @Override
    public String toString(Departament departament) {
        return departament.getName();
    }

    @Override
    public Departament fromString(String s) {
        return null;
    }
}
