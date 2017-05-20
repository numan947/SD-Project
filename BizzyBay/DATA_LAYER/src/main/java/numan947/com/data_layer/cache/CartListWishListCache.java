package numan947.com.data_layer.cache;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public interface CartListWishListCache {
    public interface CartListCallback{
        void onCartListLoaded(int page);
        void onError(Exception exception);
    }

    public interface WishListCallback{
        void onWishListLoaded(int page);
        void onError(Exception exception);
    }


    void getCartList(int page,CartListCallback providedCallback);

    void getWishList(int pager, WishListCallback providedCallback);


}
