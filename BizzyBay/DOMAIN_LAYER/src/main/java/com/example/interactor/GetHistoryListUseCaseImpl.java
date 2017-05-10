package com.example.interactor;

import com.example.HistoryList;
import com.example.exception.ErrorBundle;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repository.HistoryRepository;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class GetHistoryListUseCaseImpl implements GetHistoryListUseCase {
    private HistoryRepository historyRepository;
    private PostExecutionThread postExecutionThread;
    private ThreadExecutor threadExecutor;

    private int pageNumber;
    private Callback providedCallback;

    public GetHistoryListUseCaseImpl(HistoryRepository historyRepository, PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor) {

        if(historyRepository==null||postExecutionThread==null||threadExecutor==null)
            throw new IllegalArgumentException("ARGUMENTS CAN NOT BE NULL HERE DUMBASS");

        this.historyRepository = historyRepository;
        this.postExecutionThread = postExecutionThread;
        this.threadExecutor = threadExecutor;
    }

    @Override
    public void execute(int pageNumber, Callback providedCallback) {
        this.pageNumber= pageNumber;
        this.providedCallback = providedCallback;
        threadExecutor.execute(this);
    }

    @Override
    public void run() {
        historyRepository.getHistoryList(pageNumber,createdCallback);
    }



    /**
     * Repository callback created so that the repository can call back to let the usecase know about loading
     * status
     * */
    private final HistoryRepository.HistoryListCallback createdCallback = new HistoryRepository.HistoryListCallback() {
        @Override
        public void onHistoryListLoaded(int pageNumber, ArrayList<HistoryList> historyList) {
            GetHistoryListUseCaseImpl.this.successfullyLoaded(pageNumber,historyList);
        }

        @Override
        public void onError(ErrorBundle errorBundle) {
            GetHistoryListUseCaseImpl.this.loadingFailed(errorBundle);
        }
    };

    /**
     * Private method for chaining the loading result to UI layer
     * */
    private void loadingFailed(final ErrorBundle errorBundle) {
        postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                providedCallback.onError(errorBundle);
            }
        });
    }


    /**
     * Private method for chaining the loading result to UI layer
     * */
    private void successfullyLoaded(final int pageNumber, final ArrayList<HistoryList> historyList) {
       postExecutionThread.post(new Runnable() {
           @Override
           public void run() {
               providedCallback.onHistoryListLoaded(pageNumber,historyList);
           }
       });
    }

}
