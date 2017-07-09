package merchant.com.bizzybay_merchant.view;

import java.util.ArrayList;

import merchant.com.bizzybay_merchant.model.ShopListModel;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public interface ShopListView extends DataToLoad{
    void viewShop(int shopId);
    void renderList(int page, ArrayList<ShopListModel>shopListModels);
    void hideListView();
    void showListView();
}
