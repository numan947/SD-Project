package com.example.interactor;

import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repository.ShopRepository;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public class GetShopListUseCaseImpl implements GetShopListUseCase {

    private Callback providedCallback;
    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;
    private ShopRepository shopRepository;

    private int pageNumber;


    public GetShopListUseCaseImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, ShopRepository shopRepository) {

        if(threadExecutor==null||postExecutionThread==null||shopRepository==null)
            throw  new IllegalArgumentException("Constructor parameter can't be null you dumbass -_-");

        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.shopRepository = shopRepository;
    }


    @Override
    public void execute(int pageNumber, Callback providedCallback) {
        this.providedCallback = providedCallback;
        this.pageNumber = pageNumber;

        if(providedCallback==null)throw  new IllegalArgumentException("AGAIN THESE PARAMETERS CAN'T BE NULL, DUMB-ASS");


        threadExecutor.execute(this);
    }



    @Override
    public void run() {
        
    }
}
