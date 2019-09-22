package merchant.com.merchant_data_layer.cache;

import java.util.ArrayList;

import merchant.com.merchant_data_layer.entity.CartListEntity;
import merchant.com.merchant_data_layer.entity.WishListEntity;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public interface CartListWishListCache {
    public interface CartListCallback{
        void onCartListLoaded(int page, ArrayList<CartListEntity>cartListEntities);
        void onError(Exception exception);
    }

    public interface WishListCallback{
        void onWishListLoaded(int page, ArrayList<WishListEntity> wishListEntities);
        void onError(Exception exception);
    }


    void getCartList(int page,CartListCallback providedCallback);

    void getWishList(int pager, WishListCallback providedCallback);


}
