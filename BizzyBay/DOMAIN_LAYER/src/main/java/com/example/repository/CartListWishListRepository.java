package com.example.repository;

import com.example.CartList;
import com.example.WishList;
import com.example.exception.ErrorBundle;

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
