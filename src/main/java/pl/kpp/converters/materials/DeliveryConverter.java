package pl.kpp.converters.materials;


import javafx.util.StringConverter;
import pl.kpp.materials.Deliverys;

public class DeliveryConverter extends StringConverter<Deliverys> {
@Override
public String toString(Deliverys deliverys) {
        return deliverys.getName()+" "+deliverys.getAddress();
        }

@Override
public Deliverys fromString(String s) {
        return null;
        }
}
