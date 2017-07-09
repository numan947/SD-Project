package merchant.com.merchant_data_layer.repository.datasource;

import java.util.ArrayList;

import merchant.com.merchant_data_layer.cache.HistoryCache;
import merchant.com.merchant_data_layer.entity.HistoryDetailsEntity;
import merchant.com.merchant_data_layer.entity.HistoryListEntity;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class DiskHistoryDataStore implements HistoryDataStore {

    private HistoryCache historyCache;

    public DiskHistoryDataStore(HistoryCache historyCache) {
        this.historyCache = historyCache;
    }

    @Override
    public void getHistoryList(int pageNumber, final HistoryListCallback providedCallback) {

        HistoryCache.HistoryListCallback createdCallback = new HistoryCache.HistoryListCallback() {
            @Override
            public void onHistoryListLoaded(int pageNumber, ArrayList<HistoryListEntity> historyListEntities) {
                providedCallback.onHistoryListLoaded(pageNumber,historyListEntities);
            }

            @Override
            public void onError(Exception exception) {
                providedCallback.onError(exception);
            }
        };





        historyCache.getHistoryList(pageNumber,createdCallback);
    }

    @Override
    public void getHistoryDetails(int orderId, int shopId, final int productId, final HistoryDetailsCallback providedCallback) {
        HistoryCache.HistoryDetailsCallback createdCallback = new HistoryCache.HistoryDetailsCallback() {
            @Override
            public void onHistoryDetailsLoaded(HistoryDetailsEntity detailsEntity) {
                providedCallback.onHistoryDetailsLoaded(detailsEntity);
            }

            @Override
            public void onError(Exception exception) {
                providedCallback.onError(exception);
            }
        };


        historyCache.getHistoryDetails(orderId,shopId,productId,createdCallback);
    }
}
