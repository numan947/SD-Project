package numan947.com.bizzybay.view;

import java.util.Collection;

import numan947.com.bizzybay.model.ProductModelMultiple;

/**
 * Created by numan947 on 5/1/17.
 */

public interface ProductListView extends DataToLoad {
    void renderProductList(Collection<ProductModelMultiple>products);
    void viewProduct(ProductModelMultiple product);
    void showProductAddedToCart(ProductModelMultiple product);
    void showProductAddedToWishList(ProductModelMultiple product);
    void buyProduct(ProductModelMultiple product);
}
