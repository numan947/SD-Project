package numan947.com.data_layer.repository.datasource;

import numan947.com.data_layer.cache.CartListWishListCache;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class CartListWishListDataStoreFactory {
    private CartListWishListCache cache;

    public CartListWishListDataStoreFactory(CartListWishListCache cache) {
        this.cache = cache;
    }

    public CartListWishListDataStore createTestDataStore() {
        return new DiskCartListWishListDataStore(cache);
    }
}
