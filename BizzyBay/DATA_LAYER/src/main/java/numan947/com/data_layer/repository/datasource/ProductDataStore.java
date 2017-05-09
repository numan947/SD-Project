package numan947.com.data_layer.repository.datasource;

import java.util.Collection;

import numan947.com.data_layer.entity.DetailsProductEntity;
import numan947.com.data_layer.entity.ListProductEntity;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * Interface defining the ProductData Store. The implementation of {@link com.example.repository.ProductRepository}
 * uses this to load data.
 **/

public interface ProductDataStore {

    /**
     * Callbacks for loading product list.
     * */
    interface ProductListCallback{
        void onProductListLoaded(Collection<ListProductEntity>productEntities);
        void onError(Exception exception);
    }


    /**
     * Callbacks for loading a product details.
     * */
    interface ProductDetailsCallback{
        void onProductDetailsLoaded(DetailsProductEntity detailsProductEntity);
        void onError(Exception exception);
    }

    /**
     * loader method for product entity list.
     * */
    void getProductsEntityList(final ProductListCallback callback);

    /**
     * loader method for product entity details.
     * */
    void getProductEntityDetails(final int productId,final int shopId,final ProductDetailsCallback callback);

}
