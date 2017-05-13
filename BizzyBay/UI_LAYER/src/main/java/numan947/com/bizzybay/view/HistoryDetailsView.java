package numan947.com.bizzybay.view;

import numan947.com.bizzybay.model.HistoryDetailsModel;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public interface HistoryDetailsView extends DataToLoad {
    void showDetails();
    void hideDetails();
    void renderHistoryDetails(HistoryDetailsModel model);
    void onProductNameClicked(int productId,int shopId);
    void onShopNameClicked(int shopId);

}
