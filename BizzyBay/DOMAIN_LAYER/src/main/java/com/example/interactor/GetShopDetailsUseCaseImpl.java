package com.example.interactor;

import com.example.ShopDetails;
import com.example.exception.ErrorBundle;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repository.ShopRepository;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public class GetShopDetailsUseCaseImpl implements GetShopDetailsUseCase {
    private PostExecutionThread postExecutionThread;
    private ThreadExecutor threadExecutor;
    private ShopRepository shopRepository;

    private int shopId;
    private Callback providedCallback;

    public GetShopDetailsUseCaseImpl(PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor, ShopRepository shopRepository) {
        this.postExecutionThread = postExecutionThread;
        this.threadExecutor = threadExecutor;
        this.shopRepository = shopRepository;
    }

    private ShopRepository.ShopDetailsCallback createdCallback = new ShopRepository.ShopDetailsCallback() {
        @Override
        public void OnShopDetailsLoaded(ShopDetails shopDetails) {
            GetShopDetailsUseCaseImpl.this.successfulLoad(shopDetails);
        }

        @Override
        public void OnError(ErrorBundle errorBundle) {
            GetShopDetailsUseCaseImpl.this.errorLoad(errorBundle);
        }
    };

    private void errorLoad(final ErrorBundle errorBundle) {
        postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                providedCallback.OnError(errorBundle);
            }
        });
    }

    private void successfulLoad(final ShopDetails shopDetails) {
        postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                providedCallback.OnShopDetailsLoaded(shopDetails);
            }
        });
    }


    @Override
    public void execute(int shopId, Callback providedCallback) {
        this.shopId = shopId;
        this.providedCallback  = providedCallback;
        this.threadExecutor.execute(this);
    }
    @Override
    public void run() {
        shopRepository.getShopDetails(shopId,createdCallback);
    }

}
