package com.example.interactor;

import com.example.DetailsProduct;
import com.example.exception.ErrorBundle;

/**
 * Created by numan947 on 5/1/17.
 */

public interface GetProductDetailsUseCase extends BaseUseCase {
    public interface Callback{
        void onProductDetailsLoaded(DetailsProduct product);
        void onError(ErrorBundle errorBundle);
    }

    void execute(int productId,int shopId,Callback callback);
}
