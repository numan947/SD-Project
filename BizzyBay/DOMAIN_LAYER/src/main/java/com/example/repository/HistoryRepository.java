package com.example.repository;

import com.example.HistoryDetails;
import com.example.HistoryList;
import com.example.exception.ErrorBundle;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/10/17.<br>
 *
 * Represents HistoryRepository in the Domain Layer.
 * It's implemented in the DataLayer.
 * The related use cases use this repository to pull data from
 * the data layer.
 *
 **/

public interface HistoryRepository {

    interface HistoryListCallback{

        /**
         * What to do when history list is loaded successfully.
         * */
        void onHistoryListLoaded(int pageNumber, ArrayList<HistoryList>historyList);

        /**
         * What to do when history list is loaded unsuccessfully.
         * */
        void onError(ErrorBundle errorBundle);
    }

    interface HistoryDetailsCallback{
        /**
         * What to do when history details is loaded successfully.
         * */
        void onHistoryDetailsLoaded(HistoryDetails historyDetails);
        /**
         * What to do when history details is loaded unsuccessfully.
         * */
        void onError(ErrorBundle errorBundle);
    }

    /**
     * loader method for the history list.
     * */
    void getHistoryList(int pageNumber, HistoryListCallback providedCallback);

    /**
     * loader method for the single history item.
     * */
    void getHistoryDetails(int orderId,int shopId,int productId,HistoryDetailsCallback providedCallback);

}
