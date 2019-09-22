package numan947.com.bizzybay.view;

import java.util.ArrayList;

import numan947.com.bizzybay.model.ShopListModel;

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
