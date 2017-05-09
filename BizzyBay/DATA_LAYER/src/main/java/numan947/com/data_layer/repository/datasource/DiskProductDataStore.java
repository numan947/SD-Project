package numan947.com.data_layer.repository.datasource;

import java.util.Collection;

import numan947.com.data_layer.cache.ProductCache;
import numan947.com.data_layer.entity.DetailsProductEntity;
import numan947.com.data_layer.entity.ListProductEntity;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * Class represents one of the several implementations of {@link ProductDataStore}
 * As the class represent disk caching, it holds a {@link ProductCache} and loads data using that cache.
 *
 **/

class DiskProductDataStore implements ProductDataStore {

    private ProductCache productCache;

    //ProductCache is needed for loading the data from disk
    
    DiskProductDataStore(ProductCache productCache) {
        this.productCache = productCache;
    }

    @Override
    public void getProductsEntityList(int pageNumber, final ProductListCallback callback) {

        productCache.get(pageNumber,new ProductCache.ProductEntityListCacheCallback() {
            @Override
            public void onProductEntityListLoaded(int pageNumber, Collection<ListProductEntity> productEntities) {
                callback.onProductListLoaded(pageNumber,productEntities);
            }

            @Override
            public void onError(Exception exception) {
                callback.onError(exception);
            }
        });

    }

    @Override
    public void getProductEntityDetails(int productId,int shopID, final ProductDetailsCallback callback) {

        productCache.get(productId,shopID, new ProductCache.ProductEntityCacheCallback() {
            @Override
            public void onProductEntityLoaded(DetailsProductEntity detailsProductEntity) {
                callback.onProductDetailsLoaded(detailsProductEntity);
            }

            @Override
            public void onError(Exception exception) {
                callback.onError(exception);
            }
        });
    }

}
