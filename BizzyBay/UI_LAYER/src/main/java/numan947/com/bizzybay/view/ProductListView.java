package numan947.com.bizzybay.view;

import java.util.Collection;

import numan947.com.bizzybay.model.ListProductModel;

/**
 * Created by numan947 on 5/1/17.
 */

public interface ProductListView extends DataToLoad {
    void renderProductList(Collection<ListProductModel>products);
    void viewProduct(ListProductModel product);
    void showProductAddedToCart(ListProductModel product);
    void showProductAddedToWishList(ListProductModel product);
    void buyProduct(ListProductModel product);
}
