package pl.kpp.converters.building;

import javafx.util.StringConverter;
import pl.kpp.building.Building;

public class BuildingConverter extends StringConverter<Building> {
        @Override
        public String toString(Building building) {
            return building.getName();
        }

        @Override
        public Building fromString(String s) {
            return null;
        }
    }

