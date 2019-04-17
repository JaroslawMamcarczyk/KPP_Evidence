package pl.kpp.converters.materials;



import javafx.util.StringConverter;
import pl.kpp.materials.Materials;

public class MaterialConverter extends StringConverter<Materials> {
    @Override
    public String toString(Materials materials) {
        return materials.getType();
    }

    @Override
    public Materials fromString(String s) {

        for (Materials materials : Materials.getMaterialsList()) {
            if (materials.getName().equals(s)) {
                return materials;
            }
        }
        return  null;
    }
}
