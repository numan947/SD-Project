package numan947.com.bizzybay.view;

import numan947.com.bizzybay.model.ShopDetailsModel;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public interface ShopDetailsView extends DataToLoad {
    void renderShopDetails(ShopDetailsModel shopDetailsModel);
    void hideShopDetails();
    void showShopDetails();

}
