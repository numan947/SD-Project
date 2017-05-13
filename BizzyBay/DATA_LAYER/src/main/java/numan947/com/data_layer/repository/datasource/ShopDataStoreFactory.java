package numan947.com.data_layer.repository.datasource;

import android.content.Context;

import numan947.com.data_layer.cache.ShopCache;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public class ShopDataStoreFactory {
    private Context context;
    private ShopCache shopCache;


    public ShopDataStoreFactory(Context context, ShopCache shopCache) {
        if(context==null||shopCache==null)
            throw new IllegalArgumentException("PARAMETER TO CONSTRUCTOR CAN'T BE NULL");
        this.context = context;
        this.shopCache = shopCache;
    }


    public ShopDataStore createTestDataStore()
    {
        return new DiskShopDataStore(shopCache);
    }
}
