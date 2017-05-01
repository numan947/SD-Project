package numan947.com.data_layer.repository;

import com.example.Product;
import com.example.repository.ProductRepository;

import java.util.Collection;

import numan947.com.data_layer.entity.ProductEntity;
import numan947.com.data_layer.entity.mapper.ProductEntityDataMapper;
import numan947.com.data_layer.exception.RepositoryErrorBundle;
import numan947.com.data_layer.repository.datasource.ProductDataStore;
import numan947.com.data_layer.repository.datasource.ProductDataStoreFactory;

/**
 * Created by numan947 on 5/1/17.
 */

public class ProductDataRepository implements ProductRepository {
    private static ProductDataRepository INSTANCE;
    public static synchronized ProductDataRepository getInstance(ProductDataStoreFactory dataStoreFactory, ProductEntityDataMapper productEntityDataMapper){
        if(INSTANCE==null)INSTANCE = new ProductDataRepository(productEntityDataMapper, dataStoreFactory);
        return INSTANCE;
    }

    private final ProductEntityDataMapper productEntityDataMapper;
    private final ProductDataStoreFactory productDataStoreFactory;

    private ProductDataRepository(ProductEntityDataMapper productEntityDataMapper, ProductDataStoreFactory productDataStoreFactory) {
        if(productDataStoreFactory==null||productEntityDataMapper==null)
            throw new IllegalArgumentException("Constructor Parameters MUSTN'T BE NULL");
        this.productEntityDataMapper = productEntityDataMapper;
        this.productDataStoreFactory = productDataStoreFactory;
    }

    @Override
    public void getProductList(final ProductListCallback callback) {
        //todo create data store and load
        final ProductDataStore productDataStore = this.productDataStoreFactory.createTestDataStore();

        productDataStore.getProductsEntityList(new ProductDataStore.ProductListCallback() {
            @Override
            public void onProductListLoaded(Collection<ProductEntity> productEntities) {
                Collection<Product> products = ProductDataRepository.this.productEntityDataMapper.transform(productEntities);

                callback.onProductListLoaded(products);
            }

            @Override
            public void onError(Exception exception) {
                callback.onError(new RepositoryErrorBundle(exception));
            }
        });



    }

    @Override
    public void getProductById(int productId,final ProductDetailsCallback callback) {
        //todo create data store and load
        final ProductDataStore productDataStore = productDataStoreFactory.createTestDataStore();

        productDataStore.getProductEntityDetails(productId, new ProductDataStore.ProductDetailsCallback() {
            @Override
            public void onProductDetailsLoaded(ProductEntity productEntity) {
                Product product = ProductDataRepository.this.productEntityDataMapper.transform(productEntity);
                callback.onProductDetailsLoaded(product);
            }

            @Override
            public void onError(Exception exception) {
                callback.onError(new RepositoryErrorBundle(exception));
            }
        });


    }
}
