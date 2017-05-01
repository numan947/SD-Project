package numan947.com.data_layer.repository.datasource;

import java.util.Collection;

import numan947.com.data_layer.entity.ProductEntity;

/**
 * Created by numan947 on 5/1/17.
 */

public interface ProductDataStore {

    interface ProductListCallback{
        void onProductListLoaded(Collection<ProductEntity>productEntities);
        void onError(Exception exception);
    }

    interface ProductDetailsCallback{
        void onProductDetailsLoaded(ProductEntity productEntity);
        void onError(Exception exception);
    }

    void getProductsEntityList(final ProductListCallback callback);

    void getProductEntityDetails(final int productId,final ProductDetailsCallback callback);

}
