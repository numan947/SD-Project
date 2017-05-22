package numan947.com.bizzybay.view;

import java.util.ArrayList;

import numan947.com.bizzybay.model.CartListModel;
import numan947.com.bizzybay.model.CartProductModel;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public interface CartListView extends DataToLoad {
    void renderCartList(int pageNumber,ArrayList<CartListModel>cartListModels);
    void hideCartList();
    void showCartList();
    void onDeleteShopButtonClicked(int position);
    void onDeleteProductButtonClicked(CartListModel cartListModel, CartProductModel cartProductModel);
    void onCheckoutButtonClicked(CartListModel cartListModel);
    void onProductItemClicked(int shopId,int productId);
    void onShopNameClicked(int shopId);
}
