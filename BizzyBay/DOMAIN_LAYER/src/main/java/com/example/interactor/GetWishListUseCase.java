package com.example.interactor;

import com.example.exception.ErrorBundle;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public interface GetWishListUseCase {

    public interface Callback{
        void onWishListLoaded(int page);
        void onError(ErrorBundle errorBundle);
    }

    void getWishList(int page, Callback providedCallback);
}
