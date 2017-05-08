package numan947.com.bizzybay.view;

import numan947.com.bizzybay.model.DetailsProductModel;

/**
 * Created by numan947 on 5/7/17.
 */

public interface ProductDetailsView extends DataToLoad {
    void renderProduct(DetailsProductModel model);
    void viewShop(int shopId);
    void showProductLiked(int productId,int shopId);
    void showProductAddedToCart(int productId, int shopId);
    void buyProduct(int productId, int shopId);
    void viewShopLocation(int shopId);
    void viewProductsByCategory(String category);
    void showDetailsView();
    void hideDetailsView();

}
