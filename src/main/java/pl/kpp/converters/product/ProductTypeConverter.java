package pl.kpp.converters.product;

import javafx.util.StringConverter;
import pl.kpp.product.Product;

public class ProductTypeConverter extends StringConverter<Product.ProductType> {

    @Override
    public String toString(Product.ProductType productType) {
        return productType.getCode()+" - "+productType.getName();
    }

    @Override
    public Product.ProductType fromString(String s) {
        return null;
    }
}
