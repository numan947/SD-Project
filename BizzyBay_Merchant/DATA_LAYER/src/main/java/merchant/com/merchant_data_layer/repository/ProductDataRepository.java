package merchant.com.merchant_data_layer.repository;

import com.merchant_example.ProductDetails;
import com.merchant_example.ProductList;
import com.merchant_example.repository.ProductRepository;

import java.util.Collection;

import merchant.com.merchant_data_layer.entity.DetailsProductEntity;
import merchant.com.merchant_data_layer.entity.ListProductEntity;
import merchant.com.merchant_data_layer.entity.mapper.ProductEntityDataMapper;
import merchant.com.merchant_data_layer.exception.RepositoryErrorBundle;
import merchant.com.merchant_data_layer.repository.datasource.ProductDataStore;
import merchant.com.merchant_data_layer.repository.datasource.ProductDataStoreFactory;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * Class represents implementation of the {@link ProductRepository}
 * It's a singleton. It holds a {@link ProductEntityDataMapper} and
 * {@link ProductDataStoreFactory}.
 * It creates {@link ProductDataStore} using the {@link ProductDataStoreFactory}
 * and loads data.
 **/

public class ProductDataRepository implements ProductRepository {

    //todo remove these debug thingy
    static int cnt=0;
    static int cnt2=0;


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
    public void getProductList(int pageNumber, int shopId, final ProductListCallback providedCallback) {
        //todo create a real data store and load
        System.out.println("Product REQ....1  "+cnt);
        cnt++;

        final ProductDataStore productDataStore = this.productDataStoreFactory.createTestDataStore();


        final ProductDataStore.ProductListCallback createdCallback = new ProductDataStore.ProductListCallback() {
            @Override
            public void onProductListLoaded(int pageNumber, Collection<ListProductEntity> productEntities) {
                Collection<ProductList> productLists = ProductDataRepository.this.productEntityDataMapper.transform(productEntities);

                providedCallback.onProductListLoaded(pageNumber, productLists);
            }

            @Override
            public void onError(Exception exception) {
                providedCallback.onError(new RepositoryErrorBundle(exception));
            }
        };


        productDataStore.getProductsEntityList(pageNumber,shopId,createdCallback);



    }

    @Override
    public void getSingleProduct(int productId, int shopId, final ProductDetailsCallback providedCallback) {
        System.out.println("Product REQ....2  "+cnt2);
        cnt2++;
        //todo create real data store and load

        final ProductDataStore productDataStore = productDataStoreFactory.createTestDataStore();

        final ProductDataStore.ProductDetailsCallback createdCallback = new ProductDataStore.ProductDetailsCallback() {
            @Override
            public void onProductDetailsLoaded(DetailsProductEntity detailsProductEntity) {
                ProductDetails productDetails =ProductDataRepository.this.productEntityDataMapper.transform(detailsProductEntity);
                if(productDetails !=null)providedCallback.onProductDetailsLoaded(productDetails);
            }

            @Override
            public void onError(Exception exception) {
                providedCallback.onError(new RepositoryErrorBundle(exception));
            }
        };


        productDataStore.getProductEntityDetails(productId,shopId,createdCallback);

    }
}
