package numan947.com.bizzybay.view;

import numan947.com.bizzybay.model.DetailsProductModel;

/**
 * Created by numan947 on 5/7/17.
 */

public interface ProductDetailsView extends DataToLoad {
    void renderProduct(DetailsProductModel model);
    void viewShop(DetailsProductModel model);
    void showProductLiked(DetailsProductModel model);
    void showProductAddedToCart(DetailsProductModel model);
    void buyProduct(DetailsProductModel model);
    void viewShopLocation(DetailsProductModel model);
    void viewProductsByCategory(String category);

}
