package numan947.com.data_layer.repository.datasource;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public interface CartListWishListDataStore {

    public interface CartListCallback{
        void onCartListLoaded(int page);
        void onError(Exception exception);
    }

    public interface WishListCallback{
        void onWishListLoaded(int page);
        void onError(Exception exception);
    }

    void loadCartList(int page,CartListCallback providedCallback);

    void loadWishList(int page, WishListCallback providedCallback);

}
