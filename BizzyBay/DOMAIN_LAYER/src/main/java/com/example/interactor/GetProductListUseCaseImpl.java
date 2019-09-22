package com.example.interactor;

import com.example.ProductList;
import com.example.exception.ErrorBundle;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repository.ProductRepository;

import java.util.Collection;

/**
 * Created by numan947 on 5/1/17.
 */

public class GetProductListUseCaseImpl implements GetProductListUseCase {
    private final ProductRepository productRepository;
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    private int pageNumber;
    private Callback callback;
    private int shopId;

    public GetProductListUseCaseImpl(ProductRepository productRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {

        if(productRepository==null||threadExecutor==null||postExecutionThread==null)
            throw new IllegalArgumentException("Constructor Parameters MUST NOT be NULL");

        this.productRepository = productRepository;
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }



    @Override
    public void execute(int pageNumber, int shopId, Callback callback) {
        if(callback == null)
            throw new IllegalArgumentException("Interactor Callback MUST NOT BE NULL");

        this.pageNumber = pageNumber;
        this.shopId = shopId;
        this.callback = callback;
        this.threadExecutor.execute(this);
    }


    @Override
    public void run() {
        this.productRepository.getProductList(pageNumber,shopId,this.repositoryCallback);
    }

    private final ProductRepository.ProductListCallback repositoryCallback = new ProductRepository.ProductListCallback() {
        @Override
        public void onProductListLoaded(int pageNumber, Collection<ProductList> productLists) {
            GetProductListUseCaseImpl.this.notifyGetProductListSucceccsufully(pageNumber, productLists);
        }

        @Override
        public void onError(ErrorBundle errorBundle) {
            GetProductListUseCaseImpl.this.notifyError(errorBundle);
        }
    };

    private void notifyError(final ErrorBundle errorBundle) {
        this.postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                GetProductListUseCaseImpl.this.callback.onError(errorBundle);
            }
        });
    }

    private void notifyGetProductListSucceccsufully(final int pageNumber, final Collection<ProductList> productLists)
    {
        this.postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                GetProductListUseCaseImpl.this.callback.onProductsListLoaded(pageNumber, productLists);
            }
        });
    }


}
