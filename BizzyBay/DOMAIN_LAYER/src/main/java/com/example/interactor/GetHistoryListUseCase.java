package com.example.interactor;

import com.example.HistoryList;
import com.example.exception.ErrorBundle;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/10/17.<br>
 *
 *  Interface for implementing the GetHistoryList Use Case
 **/

public interface GetHistoryListUseCase extends BaseUseCase {

    /**
     * Call back which MUST be provided by any of the Presenters
     * using the use case
     * */
    interface Callback{

        /**
         * What to do when the use case is successfully executed.
         * */
        void onHistoryListLoaded(int pageNumber, ArrayList<HistoryList>historyList);

        /**
         * What to do when the use case not successfully executed.
         * */
        void onError(ErrorBundle errorBundle);
    }

    void execute(int pageNumber,Callback providedCallback);
}
