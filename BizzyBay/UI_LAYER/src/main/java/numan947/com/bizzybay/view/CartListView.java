package numan947.com.bizzybay.view;

import java.util.ArrayList;

import numan947.com.bizzybay.model.CartListModel;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public interface CartListView extends DataToLoad {
    void renderCartList(ArrayList<CartListModel>cartListModels);
    void hideCartList();
    void showCartList();
    void onDeleteShopButtonClicked(int shopId,int orderId);
    void onDeleteProductButtonClicked(int shopId,int productId,int orderId);
    void onCheckoutButtonClicked(int shopId,int orderId);
    void onProductItemClicked(int shopId,int productId);
    void onShopNameClicked(int shopId);
}
