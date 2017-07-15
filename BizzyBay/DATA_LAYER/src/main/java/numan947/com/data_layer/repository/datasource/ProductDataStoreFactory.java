package numan947.com.data_layer.repository.datasource;

import android.content.Context;

import numan947.com.data_layer.cache.ProductCache;
import numan947.com.data_layer.internet.RestApiCommon;
import numan947.com.data_layer.internet.RestApiCommonImpl;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * Class represents a data store factory.
 * Depending on different conditions, it selects data repository
 * without letting the repository know which of the data store is being selected.
 **/


//product data store factory will create/provide a data store depending on the data being cached or not
public class ProductDataStoreFactory {
    private final Context context;
    private final ProductCache productCache;


    //we'll need context when we use a REAL data store and not a FREAKING test Datastore
    //ProductCache will always be needed to provide cached data

    public ProductDataStoreFactory(Context context, ProductCache productCache) {
        if(context==null||productCache==null)
            throw new IllegalArgumentException("Constructor Parameters MUST NOT BE NULL");
        this.context = context;
        this.productCache = productCache;
    }


    //todo for test only, replace with Cloud and Cached data store later
    public ProductDataStore createTestDataStore()
    {

        return new DiskProductDataStore(productCache);

    }

    public ProductDataStore createCloudDataStore() {

        RestApiCommon restApi = new RestApiCommonImpl(this.context);

        return new CloudProductDataStore(restApi);
    }







}
