package com.example.repository;

import com.example.Product;
import com.example.exception.ErrorBundle;

import java.util.Collection;

/**
 * Created by numan947 on 5/1/17.
 */

public interface ProductRepository {

    interface ProductListCallback{
        void onProductListLoaded(Collection<Product>products);
        void onError(ErrorBundle errorBundle);
    }

    interface ProductDetailsCallback{
        void onProductDetailsLoaded(Product product);
        void onError(ErrorBundle errorBundle);
    }


    void getProductList(final ProductListCallback callback);

    void getProductById(final int productId,final ProductDetailsCallback callback);


}
