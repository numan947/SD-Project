package numan947.com.data_layer.cache;

import java.util.Collection;

import numan947.com.data_layer.entity.ProductEntity;

/**
 * Created by numan947 on 5/1/17.
 */

public interface ProductCache {
    interface ProductEntityCacheCallback{
        void onProductEntityLoaded(ProductEntity productEntity);
        void onError(Exception exception);
    }

    interface ProductEntityListCacheCallback{
        void onProductEntityListLoaded(Collection<ProductEntity>productEntities);
        void onError(Exception exception);
    }

    void get(final int productId,final ProductEntityCacheCallback callback);

    void put(final int productId);

    void get(ProductEntityListCacheCallback callback);

    void put(final Collection<ProductEntity>productEntities);

    boolean isCachecd(final int productId);

    boolean isExpired();

    void eraseAllCache();

}
