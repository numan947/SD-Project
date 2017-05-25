package numan947.com.bizzybay.view;

import java.util.ArrayList;

import numan947.com.bizzybay.model.WishListModel;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public interface WishListView extends DataToLoad {
    void renderWishList(int pageNumber,ArrayList<WishListModel>wishListModels);
    void hideWishList();
    void showWishList();
    void hideEmpty();
    void showEmpty();

    void onLikeButtonClicked(WishListModel wishListModel);
    void onShopClicked(int shopId);
    void onProductClicked(int productId,int shopId);
}
