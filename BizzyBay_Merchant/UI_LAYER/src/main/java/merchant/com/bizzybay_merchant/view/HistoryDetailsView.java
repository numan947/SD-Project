package merchant.com.bizzybay_merchant.view;

import merchant.com.bizzybay_merchant.model.HistoryDetailsModel;

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
