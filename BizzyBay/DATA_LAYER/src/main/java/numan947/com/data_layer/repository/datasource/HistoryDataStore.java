package numan947.com.data_layer.repository.datasource;

import java.util.ArrayList;

import numan947.com.data_layer.entity.HistoryDetailsEntity;
import numan947.com.data_layer.entity.HistoryListEntity;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public interface HistoryDataStore {

    interface HistoryListCallback{
        void onHistoryListLoaded(int pageNumber, ArrayList<HistoryListEntity>historyDetails);
        void onError(Exception exception);
    }


    interface HistoryDetailsCallback{
        void onHistoryDetailsLoadedd(HistoryDetailsEntity historyDetailsEntity);
        void onError(Exception exception);
    }

    void getHistoryList(int pageNumber,HistoryListCallback providedCallback);

    void getHistoryDetails(int orderId,int shopId,int productId,HistoryDetailsCallback providedCallback);

}
