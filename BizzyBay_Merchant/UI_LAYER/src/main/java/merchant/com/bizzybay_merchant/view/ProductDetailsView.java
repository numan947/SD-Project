package merchant.com.bizzybay_merchant.view;

import merchant.com.bizzybay_merchant.model.ProductDetailsModel;
/**
 * @author numan947
 * @since 5/7/17.
 *
 * Interface for rendering Product Details to the associated view.
 * This is usually held by the Presenters and implemented by the view.
 */
public interface ProductDetailsView extends DataToLoad {
    /**
     * Renders a single product to the view.
     * */
    void renderProduct(ProductDetailsModel model);

    /**
     * Called by Presenter when the Shop Name is clicked.
     * */
    void viewShop(int shopId);
    /**
     * Called by the Presenter when the Like Button of the
     * product is clicked.
     * */
    void showProductLiked(ProductDetailsModel model);

    /**
     * Called by the Presenter when the Add to Cart Button of the
     * product is clicked.
     * */
    void showProductAddedToCart(ProductDetailsModel model);

    /**
     * Called by the Presenter when the Buy Button of the
     * product is clicked.
     * */
    void buyProduct(int productId, int shopId);

    /**
     * Called by the Presenter when the Shop Location of the
     * product is clicked.
     * */
    void viewShopLocation(int shopId);

    /**
     * Called by the Presenter when one of the Categories of the
     * product is clicked.
     * */
    void viewProductsByCategory(String category);

    /**
     * Called by the presenter to show the actual view.
     * */
    void showDetailsView();
    /**
     * Called by the presenter to hide the actual view.
     * */
    void hideDetailsView();

}
