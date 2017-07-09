package com.merchant_example.interactor;

import com.merchant_example.WishList;
import com.merchant_example.exception.ErrorBundle;
import com.merchant_example.executor.PostExecutionThread;
import com.merchant_example.executor.ThreadExecutor;
import com.merchant_example.repository.CartListWishListRepository;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class GetWishListUseCaseImpl implements GetWishListUseCase {

    private PostExecutionThread postExecutionThread;
    private ThreadExecutor threadExecutor;
    private CartListWishListRepository cartListWishListRepository;

    private int pageNumber;
    private Callback providedCallback;


    private CartListWishListRepository.WishListCallback createdCallback = new CartListWishListRepository.WishListCallback() {
        @Override
        public void onWishListLoaded(int page, ArrayList<WishList> wishLists) {
            GetWishListUseCaseImpl.this.successfulLoad(page,wishLists);
        }

        @Override
        public void onError(ErrorBundle errorBundle) {
            GetWishListUseCaseImpl.this.failedLoad(errorBundle);
        }
    };



    public GetWishListUseCaseImpl(PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor, CartListWishListRepository cartListWishListRepository) {
        this.postExecutionThread = postExecutionThread;
        this.threadExecutor = threadExecutor;
        this.cartListWishListRepository = cartListWishListRepository;
    }





    @Override
    public void getWishList(int page, Callback providedCallback) {
        this.pageNumber = page;
        this.providedCallback = providedCallback;

        this.threadExecutor.execute(this);
    }

    @Override
    public void run() {
        this.cartListWishListRepository.loadWishList(pageNumber,createdCallback);
    }

    private void failedLoad(final ErrorBundle errorBundle) {
        postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                providedCallback.onError(errorBundle);
            }
        });
    }

    private void successfulLoad(final int page, final ArrayList<WishList> wishLists) {
        postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                providedCallback.onWishListLoaded(page,wishLists);
            }
        });
    }

}
