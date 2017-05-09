package numan947.com.data_layer.cache;

import java.util.Collection;

import numan947.com.data_layer.entity.DetailsProductEntity;
import numan947.com.data_layer.entity.ListProductEntity;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * Product Cache interface.
 * This interface declares all the caching things that can be applied to product entity.
 * The implementation of this interface is the class that actually interacts with the disk to retrieve
 * data.
 **/


public interface ProductCache {

    /**
     * Callback for Single product entity loading.
     * */
    interface ProductEntityCacheCallback{
        void onProductEntityLoaded(DetailsProductEntity detailsProductEntity);
        void onError(Exception exception);
    }



    /**
     * Callback for List product entity loading.
     * */
    interface ProductEntityListCacheCallback{
        void onProductEntityListLoaded(Collection<ListProductEntity>productEntities);
        void onError(Exception exception);
    }

    /**
     * Loader method for single product.
     * */
    void get(final int productId,final int shopId,final ProductEntityCacheCallback callback);

    /**
     * updater/persistence maker method for single product.
     * */
    //todo will change depending on the action needed
    void put(final int productId, final int shopId);

    /**
     * Loader method for list product.
     * */
    void get(ProductEntityListCacheCallback callback);

    /**
     * updater/persistence maker method for list product.
     * */
    //todo will change depending on the action needed
    void put(final Collection<ListProductEntity>productEntities);


    /**
     * Checks if item is cached.
     * */

    //todo will change depending on the action needed
    boolean isCachecd(final int productId);

    /**
     * Checks if item's cache is expired.
     * */
    //todo will change depending on the action needed
    boolean isExpired();

    /**
     * removes all Caches.
     * */
    //todo will change depending on the action needed
    void eraseAllCache();

}
