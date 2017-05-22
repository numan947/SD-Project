package com.example.interactor;

import com.example.CartList;
import com.example.exception.ErrorBundle;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repository.CartListWishListRepository;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class GetCartListUseCaseImpl implements GetCartListUseCase {
    private PostExecutionThread postExecutionThread;
    private ThreadExecutor threadExecutor;
    private CartListWishListRepository repository;


    private Callback providedCallback;
    private int pageNumber;




    public GetCartListUseCaseImpl(PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor, CartListWishListRepository repository) {
        //todo add null check
        this.postExecutionThread = postExecutionThread;
        this.threadExecutor = threadExecutor;
        this.repository = repository;
    }

    @Override
    public void run() {
        repository.loadCartList(pageNumber,createdCallback);
    }

    @Override
    public void getCartList(int page, Callback providedCallback) {
        this.pageNumber = page;
        this.providedCallback = providedCallback;

        this.threadExecutor.execute(this);
    }

    private CartListWishListRepository.CartListCallback createdCallback = new CartListWishListRepository.CartListCallback() {
        @Override
        public void onCartListLoaded(int page, ArrayList<CartList> cartList) {
            GetCartListUseCaseImpl.this.successfulLoad(page,cartList);
        }

        @Override
        public void onError(ErrorBundle errorBundle) {
            GetCartListUseCaseImpl.this.failedLoad(errorBundle);
        }
    };

    private void failedLoad(final ErrorBundle errorBundle) {
        postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                providedCallback.onError(errorBundle);
            }
        });
    }

    private void successfulLoad(final int page, final ArrayList<CartList> cartList) {
        postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                providedCallback.onCartListLoaded(page,cartList);
            }
        });
    }


}
