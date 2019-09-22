package com.example.interactor;

import com.example.WishList;
import com.example.exception.ErrorBundle;

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
