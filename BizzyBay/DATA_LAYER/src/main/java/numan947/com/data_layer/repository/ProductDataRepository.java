package numan947.com.data_layer.repository;

import com.example.DetailsProduct;
import com.example.ListProduct;
import com.example.repository.ProductRepository;

import java.util.Collection;

import numan947.com.data_layer.entity.DetailsProductEntity;
import numan947.com.data_layer.entity.ListProductEntity;
import numan947.com.data_layer.entity.mapper.ProductEntityDataMapper;
import numan947.com.data_layer.exception.RepositoryErrorBundle;
import numan947.com.data_layer.repository.datasource.ProductDataStore;
import numan947.com.data_layer.repository.datasource.ProductDataStoreFactory;

/**
 * Created by numan947 on 5/1/17.
 */

public class ProductDataRepository implements ProductRepository {


    private static ProductDataRepository INSTANCE;

    //DataRepositories are Singleton and static, they'll always exist from the start of the application
    // needs dataStroreFactory to create dataStore and dataMapper to map the data from data-layer to domain-layer

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
    public void getProductList(final ProductListCallback providedCallback) {
        //todo create a real data store and load


        final ProductDataStore productDataStore = this.productDataStoreFactory.createTestDataStore();


        final ProductDataStore.ProductListCallback createdCallback = new ProductDataStore.ProductListCallback() {
            @Override
            public void onProductListLoaded(Collection<ListProductEntity> productEntities) {
                Collection<ListProduct> listProducts = ProductDataRepository.this.productEntityDataMapper.transform(productEntities);

                providedCallback.onProductListLoaded(listProducts);
            }

            @Override
            public void onError(Exception exception) {
                providedCallback.onError(new RepositoryErrorBundle(exception));
            }
        };


        productDataStore.getProductsEntityList(createdCallback);



    }

    @Override
    public void getProductById(int productId,int shopId,final ProductDetailsCallback providedCallback) {

        //todo create real data store and load

        final ProductDataStore productDataStore = productDataStoreFactory.createTestDataStore();

        final ProductDataStore.ProductDetailsCallback createdCallback = new ProductDataStore.ProductDetailsCallback() {
            @Override
            public void onProductDetailsLoaded(DetailsProductEntity detailsProductEntity) {
                DetailsProduct detailsProduct=ProductDataRepository.this.productEntityDataMapper.transform(detailsProductEntity);
                if(detailsProduct!=null)providedCallback.onProductDetailsLoaded(detailsProduct);
            }

            @Override
            public void onError(Exception exception) {
                providedCallback.onError(new RepositoryErrorBundle(exception));
            }
        };


        productDataStore.getProductEntityDetails(productId,shopId,createdCallback);

    }
}
