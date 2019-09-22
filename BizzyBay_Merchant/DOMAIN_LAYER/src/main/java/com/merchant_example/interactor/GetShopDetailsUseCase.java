package com.merchant_example.interactor;

import com.merchant_example.ShopDetails;
import com.merchant_example.exception.ErrorBundle;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public interface GetShopDetailsUseCase extends BaseUseCase {
    public interface Callback{
        void OnShopDetailsLoaded(ShopDetails shopDetails);
        void OnError(ErrorBundle errorBundle);
    }

    void execute(int shopId,Callback providedCallback);
}
