package numan947.com.data_layer.repository.datasource;

import com.example.DetailsProduct;

import java.util.Collection;

import numan947.com.data_layer.cache.ProductCache;
import numan947.com.data_layer.entity.DetailsProductEntity;
import numan947.com.data_layer.entity.ListProductEntity;

/**
 * Created by numan947 on 5/1/17.
 */

public class DiskProductDataStore implements ProductDataStore {

    private ProductCache productCache;

    public DiskProductDataStore(ProductCache productCache) {
        this.productCache = productCache;
    }

    @Override
    public void getProductsEntityList(final ProductListCallback callback) {

        productCache.get(new ProductCache.ProductEntityListCacheCallback() {
            @Override
            public void onProductEntityListLoaded(Collection<ListProductEntity> productEntities) {
                callback.onProductListLoaded(productEntities);
            }

            @Override
            public void onError(Exception exception) {
                callback.onError(exception);
            }
        });

    }

    @Override
    public void getProductEntityDetails(int productId,int shopID, final ProductDetailsCallback callback) {
        productCache.get(productId, new ProductCache.ProductEntityCacheCallback() {
            @Override
            public void onProductEntityLoaded(DetailsProductEntity detailsProductEntity) {
                callback.onProductDetailsLoaded(listProductEntity);
            }

            @Override
            public void onError(Exception exception) {
                callback.onError(exception);
            }
        });
    }

}
