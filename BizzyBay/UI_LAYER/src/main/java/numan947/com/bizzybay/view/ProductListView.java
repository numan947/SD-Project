package numan947.com.bizzybay.view;

import java.util.ArrayList;

import numan947.com.bizzybay.model.ListProductModel;

/**
 * Created by numan947 on 5/1/17.
 */

public interface ProductListView extends DataToLoad {
    void renderProductList(ArrayList<ListProductModel>products);
    void viewProduct(ListProductModel product);
    void showProductLiked(ListProductModel productModel, int position);
}
