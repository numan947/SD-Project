package com.example.interactor;

import com.example.ShopDetails;
import com.example.exception.ErrorBundle;

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
