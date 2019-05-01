package pl.kpp.converters.workers;

import javafx.util.StringConverter;
import pl.kpp.workers.Worker;

public class PolicemanConverter extends StringConverter<Worker> {
    @Override
    public String toString(Worker worker) {
        return worker.getName()+" "+ worker.getSurrname();
    }

    @Override
    public Worker fromString(String s) {
        return null;
    }
}
