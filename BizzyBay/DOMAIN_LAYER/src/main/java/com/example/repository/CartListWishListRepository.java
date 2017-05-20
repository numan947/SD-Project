package com.example.repository;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public interface CartListWishListRepository {
    public interface CartListCallback{
        void onCartListLoaded(int page);
    }

    public interface WishListCallback{
        void onWishListLoaded(int page);
    }


    void loadCartList(int page, CartListCallback providedCallback);

    void loadWishList(int page, WishListCallback providedCallback);


}
