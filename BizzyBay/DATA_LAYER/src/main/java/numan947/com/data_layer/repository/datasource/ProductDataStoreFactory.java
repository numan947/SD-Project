package numan947.com.data_layer.repository.datasource;

import android.content.Context;

import numan947.com.data_layer.cache.ProductCache;

/**
 * Created by numan947 on 5/1/17.
 */

public class ProductDataStoreFactory {
    private final Context context;
    private final ProductCache productCache;


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







}
