package com.merchant_example.repository;

import com.merchant_example.CartList;
import com.merchant_example.WishList;
import com.merchant_example.exception.ErrorBundle;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public interface CartListWishListRepository {
    public interface CartListCallback{
        void onCartListLoaded(int page, ArrayList<CartList>cartList);
        void onError(ErrorBundle errorBundle);
    }

    public interface WishListCallback{
        void onWishListLoaded(int page, ArrayList<WishList> wishLists);
        void onError(ErrorBundle errorBundle);
    }


    void loadCartList(int page, CartListCallback providedCallback);

    void loadWishList(int page, WishListCallback providedCallback);


}
