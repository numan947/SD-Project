package merchant.com.bizzybay_merchant.view;

import merchant.com.bizzybay_merchant.model.ShopDetailsModel;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public interface ShopDetailsView extends DataToLoad {
    void renderShopDetails(ShopDetailsModel shopDetailsModel);
    void hideShopDetails();
    void showShopDetails();

}
