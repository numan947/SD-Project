package merchant.com.bizzybay_merchant.view;

import java.util.ArrayList;

import merchant.com.bizzybay_merchant.model.CartListModel;
import merchant.com.bizzybay_merchant.model.CartProductModel;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public interface CartListView extends DataToLoad {
    void renderCartList(int pageNumber,ArrayList<CartListModel>cartListModels);
    void hideCartList();
    void showCartList();
    void showEmpty();
    void hideEmpty();

    void onDeleteShopButtonClicked(int position);
    void onDeleteProductButtonClicked(CartListModel cartListModel, CartProductModel cartProductModel,int position);
    void onCheckoutButtonClicked(CartListModel cartListModel);
    void onProductItemClicked(int shopId,int productId);
    void onShopNameClicked(int shopId);

}
