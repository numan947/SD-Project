package numan947.com.data_layer.repository.datasource;

import java.util.Collection;

import numan947.com.data_layer.entity.DetailsProductEntity;
import numan947.com.data_layer.entity.ListProductEntity;
import numan947.com.data_layer.internet.RestApiCommon;

/**
 * @author numan947
 * @since 7/10/17.<br>
 **/

public class CloudProductDataStore implements ProductDataStore {

    RestApiCommon restApiCommon;

    public CloudProductDataStore(RestApiCommon restApiCommon)
    {
        this.restApiCommon = restApiCommon;
    }


    @Override
    public void getProductsEntityList(final int pageNumber, int shopId, final ProductListCallback callback) {
        this.restApiCommon.getProductList(pageNumber,new RestApiCommon.ProductListCallback(){

            @Override
            public void onProductListLoaded(Collection<ListProductEntity> productCollection) {
                callback.onProductListLoaded(pageNumber,productCollection);
            }

            @Override
            public void onError(Exception exception) {
                callback.onError(exception);
            }
        });
    }

    @Override
    public void getProductEntityDetails(int productId, int shopId, final ProductDetailsCallback callback) {
        this.restApiCommon.getProductById(productId, new RestApiCommon.ProductDetailsCallback() {
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
