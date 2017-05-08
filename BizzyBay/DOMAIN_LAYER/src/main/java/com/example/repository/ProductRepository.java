package com.example.repository;

import com.example.DetailsProduct;
import com.example.ListProduct;
import com.example.exception.ErrorBundle;

import java.util.Collection;

/**
 * Created by numan947 on 5/1/17.
 */

public interface ProductRepository {

    interface ProductListCallback{
        void onProductListLoaded(Collection<ListProduct> listProducts);
        void onError(ErrorBundle errorBundle);
    }

    interface ProductDetailsCallback{
        void onProductDetailsLoaded(DetailsProduct detailsProduct);
        void onError(ErrorBundle errorBundle);
    }


    void getProductList(final ProductListCallback callback);

    void getProductById(final int productId,final int shopId,final ProductDetailsCallback callback);


}
