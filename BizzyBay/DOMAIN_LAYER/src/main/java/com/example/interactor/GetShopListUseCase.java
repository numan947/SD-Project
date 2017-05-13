package com.example.interactor;

import com.example.ShopList;
import com.example.exception.ErrorBundle;

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
