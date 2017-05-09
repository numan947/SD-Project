package com.example.interactor;

import com.example.ListProduct;
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

    public GetProductListUseCaseImpl(ProductRepository productRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {

        if(productRepository==null||threadExecutor==null||postExecutionThread==null)
            throw new IllegalArgumentException("Constructor Parameters MUST NOT be NULL");

        this.productRepository = productRepository;
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }



    @Override
    public void execute(int pageNumber, Callback callback) {
        if(callback == null)
            throw new IllegalArgumentException("Interactor Callback MUST NOT BE NULL");

        this.pageNumber = pageNumber;
        this.callback = callback;
        this.threadExecutor.execute(this);
    }


    @Override
    public void run() {
        this.productRepository.getProductList(pageNumber,this.repositoryCallback);
    }

    private final ProductRepository.ProductListCallback repositoryCallback = new ProductRepository.ProductListCallback() {
        @Override
        public void onProductListLoaded(int pageNumber, Collection<ListProduct> listProducts) {
            GetProductListUseCaseImpl.this.notifyGetProductListSucceccsufully(pageNumber,listProducts);
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

    private void notifyGetProductListSucceccsufully(final int pageNumber, final Collection<ListProduct> listProducts)
    {
        this.postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                GetProductListUseCaseImpl.this.callback.onProductsListLoaded(pageNumber,listProducts);
            }
        });
    }


}
