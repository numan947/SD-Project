package com.merchant_example.interactor;

import com.merchant_example.WishList;
import com.merchant_example.exception.ErrorBundle;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public interface GetWishListUseCase extends BaseUseCase {

    public interface Callback{
        void onWishListLoaded(int page, ArrayList<WishList> wishLists);
        void onError(ErrorBundle errorBundle);
    }

    void getWishList(int page, Callback providedCallback);
}
