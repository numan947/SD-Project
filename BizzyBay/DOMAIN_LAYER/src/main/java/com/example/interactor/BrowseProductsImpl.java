package com.example.interactor;

import com.example.Product;
import com.example.exception.ErrorBundle;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repository.ProductRepository;

import java.util.Collection;

/**
 * Created by numan947 on 5/1/17.
 */

public class BrowseProductsImpl implements BrowseProducts {
    private final ProductRepository productRepository;
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    private Callback callback;

    public BrowseProductsImpl(ProductRepository productRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {

        if(productRepository==null||threadExecutor==null||postExecutionThread==null)
            throw new IllegalArgumentException("Constructor Parameters MUST NOT be NULL");

        this.productRepository = productRepository;
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }



    @Override
    public void execute(Callback callback) {
        if(callback == null)
            throw new IllegalArgumentException("Interactor Callback MUST NOT BE NULL");

        this.callback = callback;
        this.threadExecutor.execute(this);
    }


    @Override
    public void run() {
        this.productRepository.getProductList(this.repositoryCallback);
    }

    private final ProductRepository.ProductListCallback repositoryCallback = new ProductRepository.ProductListCallback() {
        @Override
        public void onProductListLoaded(Collection<Product> products) {
            BrowseProductsImpl.this.notifyGetProductListSucceccsufully(products);
        }

        @Override
        public void onError(ErrorBundle errorBundle) {
            BrowseProductsImpl.this.notifyError(errorBundle);
        }
    };

    private void notifyError(final ErrorBundle errorBundle) {
        this.postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                BrowseProductsImpl.this.callback.onError(errorBundle);
            }
        });
    }

    private void notifyGetProductListSucceccsufully(final Collection<Product> products)
    {
        this.postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                BrowseProductsImpl.this.callback.onProductsListLoaded(products);
            }
        });
    }


}
