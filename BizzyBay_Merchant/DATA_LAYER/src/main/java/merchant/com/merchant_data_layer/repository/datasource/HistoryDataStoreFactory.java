package merchant.com.merchant_data_layer.repository.datasource;

import android.content.Context;

import merchant.com.merchant_data_layer.cache.HistoryCache;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/


//product data store factory will create/provide a data store depending on the data being cached or not
public class HistoryDataStoreFactory {
    private final Context context;
    private final HistoryCache historyCache;


    //we'll need context when we use a REAL data store and not a FREAKING test Datastore
    //ProductCache will always be needed to provide cached data

    public HistoryDataStoreFactory(Context context, HistoryCache historyCache) {
        if(context==null||historyCache==null)
            throw new IllegalArgumentException("Constructor Parameters MUST NOT BE NULL");
        this.context = context;
        this.historyCache = historyCache;
    }


    //todo for test only, replace with Cloud and Cached data store later
    public HistoryDataStore createTestDataStore()
    {

        return new DiskHistoryDataStore(historyCache);

    }







}
