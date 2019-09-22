package com.example.interactor;

import com.example.HistoryDetails;
import com.example.exception.ErrorBundle;

/**
 * @author numan947
 * @since 5/10/17.<br>
 *
 * Interface for executing GetHistoryDetails Use Case
 **/

public interface GetHistoryDetailsUseCase extends BaseUseCase {

    interface Callback {
        void onHistoryDetailsLoaded(HistoryDetails historyDetails);
        void onError(ErrorBundle errorBundle);
    }

    void execute(int orderId,int shopId,int productId,Callback providedCallback);

}
