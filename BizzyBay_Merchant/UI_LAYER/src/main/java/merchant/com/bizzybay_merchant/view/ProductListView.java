package merchant.com.bizzybay_merchant.view;

import java.util.ArrayList;

import merchant.com.bizzybay_merchant.model.ProductListModel;

/**
 *
 * @author numan947
 * @since 5/1/17.
 *
 *
 * Class for rendering ProductList to the view.
 * This is held by Presenter and implemented by the associated View.
 **/

public interface ProductListView extends DataToLoad {

    /**
     * Renders the list in the view.
     * */
    void renderProductList(int pageNumber, ArrayList<ProductListModel> products);

    /**
     * This is called by Presenter when a product of the list is clicked.
     * */
    void viewProduct(ProductListModel product);
    /**
     * Called by Presenter when the like button of on of the products is clicked.
     * */
    void showProductLiked(ProductListModel productModel, int position);
}
