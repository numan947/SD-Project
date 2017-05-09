package com.example.repository;

import com.example.DetailsProduct;
import com.example.ListProduct;
import com.example.exception.ErrorBundle;

import java.util.Collection;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * Represents ProductRepository in the Domain Layer.
 * It's implemented in the DataLayer.
 * The related use cases use this repository to pull data from
 * the data layer.
 *
 **/

public interface ProductRepository {

    /**
     * Callbacks related to
     * loading product list.
     * */
    interface ProductListCallback{
        /**
         * What to do when product list is loaded successfully.
         * */
        void onProductListLoaded(Collection<ListProduct> listProducts);

        /**
         * What to do when there's error in loading.
         * */
        void onError(ErrorBundle errorBundle);
    }



    /**
     * Callbacks related to
     * loading product details.
     * */
    interface ProductDetailsCallback{

        /**
         * What to do when product details is loaded successfully.
         * */
        void onProductDetailsLoaded(DetailsProduct detailsProduct);
        /**
         * What to do when there's error in loading.
         * */
        void onError(ErrorBundle errorBundle);
    }


    /**
     * loader method for the product list.
     * */
    void getProductList(final ProductListCallback callback);

    /**
     * loader method for single product.
     * */
    void getSingleProduct(final int productId, final int shopId, final ProductDetailsCallback callback);
}
