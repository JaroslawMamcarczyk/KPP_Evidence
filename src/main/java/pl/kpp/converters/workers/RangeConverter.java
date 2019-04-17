package pl.kpp.converters.workers;


import javafx.util.StringConverter;
import pl.kpp.workers.Range;

public class RangeConverter extends StringConverter<Range> {

    @Override
    public String toString(Range range) {
        return range.getRangeName();
    }

    @Override
    public Range fromString(String s) {
        return null;
    }
}
