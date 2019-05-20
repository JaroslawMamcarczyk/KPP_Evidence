package pl.kpp.converters.product;

import javafx.util.StringConverter;
import pl.kpp.product.Product;

public class ProductKindConverter extends StringConverter <Product.ProductKind> {

    @Override
    public String toString(Product.ProductKind productKind) {
        return productKind.getText();
    }

    @Override
    public Product.ProductKind fromString(String s) {
        return null;
    }
}
