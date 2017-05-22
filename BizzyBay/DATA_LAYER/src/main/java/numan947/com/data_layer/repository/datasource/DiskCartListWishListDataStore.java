package numan947.com.data_layer.repository.datasource;

import java.util.ArrayList;

import numan947.com.data_layer.cache.CartListWishListCache;
import numan947.com.data_layer.entity.CartListEntity;
import numan947.com.data_layer.entity.WishListEntity;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class DiskCartListWishListDataStore implements CartListWishListDataStore {

    private CartListWishListCache cache;


    public DiskCartListWishListDataStore(CartListWishListCache cache) {
        this.cache = cache;
    }

    @Override
    public void loadCartList(int page, final CartListCallback providedCallback) {
        CartListWishListCache.CartListCallback createdCallback = new CartListWishListCache.CartListCallback() {
            @Override
            public void onCartListLoaded(int page, ArrayList<CartListEntity> cartListEntities) {
                providedCallback.onCartListLoaded(page,cartListEntities);
            }

            @Override
            public void onError(Exception exception) {
                providedCallback.onError(exception);
            }
        };

        cache.getCartList(page,createdCallback);
    }

    @Override
    public void loadWishList(int page, final WishListCallback providedCallback) {

        CartListWishListCache.WishListCallback createdCallback = new CartListWishListCache.WishListCallback() {
            @Override
            public void onWishListLoaded(int page, ArrayList<WishListEntity> wishListEntities) {
                providedCallback.onWishListLoaded(page,wishListEntities);
            }

            @Override
            public void onError(Exception exception) {

            }
        };


        cache.getWishList(page,createdCallback);
    }
}
