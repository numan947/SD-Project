package com.merchant_example.interactor;

import com.merchant_example.ShopList;
import com.merchant_example.exception.ErrorBundle;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public interface GetShopListUseCase extends BaseUseCase {
    interface Callback{
        void onShopListLoaded(int pageNumber, ArrayList<ShopList>shopList);
        void onError(ErrorBundle errorBundle);
    }

    void execute(int pageNumber,Callback providedCallback);
}
