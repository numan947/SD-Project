package com.example.interactor;

import com.example.Product;
import com.example.exception.ErrorBundle;

import java.util.Collection;

/**
 * Created by numan947 on 5/1/17.
 */

public interface BrowseProducts extends BaseUseCase {
    interface Callback{
        void onProductsListLoaded(Collection<Product>products);
        void onError(ErrorBundle errorBundle);
    }

    void execute(Callback callback);
}
