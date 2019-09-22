package numan947.com.data_layer.repository.datasource;

import java.util.ArrayList;

import numan947.com.data_layer.entity.CartListEntity;
import numan947.com.data_layer.entity.WishListEntity;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public interface CartListWishListDataStore {

    public interface CartListCallback{
        void onCartListLoaded(int page, ArrayList<CartListEntity> cartListEntities);
        void onError(Exception exception);
    }

    public interface WishListCallback{
        void onWishListLoaded(int page, ArrayList<WishListEntity> wishListEntities);
        void onError(Exception exception);
    }

    void loadCartList(int page,CartListCallback providedCallback);

    void loadWishList(int page, WishListCallback providedCallback);

}
