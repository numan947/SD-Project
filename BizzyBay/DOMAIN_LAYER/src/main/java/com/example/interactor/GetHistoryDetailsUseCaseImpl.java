package com.example.interactor;

import com.example.HistoryDetails;
import com.example.exception.ErrorBundle;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repository.HistoryRepository;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class GetHistoryDetailsUseCaseImpl implements GetHistoryDetailsUseCase {
    private PostExecutionThread postExecutionThread;
    private ThreadExecutor threadExecutor;
    private HistoryRepository historyRepository;


    private Callback providedCallback;
    private int orderId,shopId,productId;


    public GetHistoryDetailsUseCaseImpl(PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor, HistoryRepository historyRepository) {

        if(historyRepository==null||postExecutionThread==null||threadExecutor==null)
            throw new IllegalArgumentException("ARGUMENTS TO this method can't be null");

        this.postExecutionThread = postExecutionThread;
        this.threadExecutor = threadExecutor;
        this.historyRepository = historyRepository;
    }



    @Override
    public void execute(int orderId, int shopId, int productId, Callback providedCallback) {
        this.productId = productId;
        this.orderId = orderId;
        this.shopId = shopId;
        this.providedCallback = providedCallback;

        threadExecutor.execute(this);
    }

    @Override
    public void run() {
        historyRepository.getHistoryDetails(orderId,shopId,productId,createdCallback);
    }


    private HistoryRepository.HistoryDetailsCallback createdCallback = new HistoryRepository.HistoryDetailsCallback() {
        @Override
        public void onHistoryDetailsLoaded(HistoryDetails historyDetails) {
            GetHistoryDetailsUseCaseImpl.this.successfulLoading(historyDetails);
        }

        @Override
        public void onError(ErrorBundle errorBundle) {
            GetHistoryDetailsUseCaseImpl.this.errorLoading(errorBundle);
        }
    };

    private void errorLoading(final ErrorBundle errorBundle) {
        postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                providedCallback.onError(errorBundle);
            }
        });
    }

    private void successfulLoading(final HistoryDetails historyDetails) {
        postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                providedCallback.onHistoryDetailsLoaded(historyDetails);
            }
        });
    }

}
