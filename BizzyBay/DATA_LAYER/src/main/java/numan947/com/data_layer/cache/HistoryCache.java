package numan947.com.data_layer.cache;

import java.util.ArrayList;

import numan947.com.data_layer.entity.HistoryDetailsEntity;
import numan947.com.data_layer.entity.HistoryListEntity;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public interface HistoryCache {
    interface  HistoryListCallback{
        void onHistoryListLoaded(int pageNumber, ArrayList<HistoryListEntity>historyListEntities);
        void onError(Exception exception);
    }

    interface HistoryDetailsCallback{
        void onHistoryDetailsLoaded(HistoryDetailsEntity detailsEntity);
        void onError(Exception exception);
    }


    void getHistoryList(int pageNumber,HistoryListCallback providedCallback);

    void putHistoryList(ArrayList<HistoryListEntity>historyListEntities);

    void getHistoryDetails(int orderId,int shopId,int productId,HistoryDetailsCallback providedCallback);

    void putHistoryDetails(HistoryDetailsEntity historyDetailsEntity);

}
