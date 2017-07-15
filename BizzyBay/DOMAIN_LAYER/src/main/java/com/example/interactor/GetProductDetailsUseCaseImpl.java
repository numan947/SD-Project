package com.example.interactor;

import com.example.ProductDetails;
import com.example.exception.ErrorBundle;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repository.ProductRepository;

/**
 * Created by numan947 on 5/8/17.
 */

public class GetProductDetailsUseCaseImpl implements GetProductDetailsUseCase {

    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;
    private ProductRepository productRepository;


    private int productId;
    private int shopId;
    private GetProductDetailsUseCase.Callback providedCallback;



    //initiates the Usecase with necessary repository to 'fetch' data, thread executor to run on the background
    //and a post execution thread to run the ui-change in the ui-thread
    public GetProductDetailsUseCaseImpl(ProductRepository productRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {

        if(threadExecutor==null||postExecutionThread==null||productRepository==null)
            throw new IllegalArgumentException("Constructor Paramters can't be NULL");
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.productRepository = productRepository;
    }


    //entry point of the usecase, i.e. this is where the usecase starts working on business logic
    @Override
    public void execute(int productId, int shopId, Callback callback) {
        this.productId = productId;
        this.shopId = shopId;
        this.providedCallback = callback;
        this.threadExecutor.execute(this);
    }


    //this is where the actual 'fetch' of details happens and the usecase hands the control over to data layer
    @Override
    public void run() {
        productRepository.getSingleProduct(productId,shopId,createdCallback);
    }


    //data-layer uses this callback to return to domain layer and domain-layer uses the provided call back to return to
    //ui-layer, ui-layer logic should execute in the UI-Thread/Main thread
    private final ProductRepository.ProductDetailsCallback createdCallback = new ProductRepository.ProductDetailsCallback() {
        @Override
        public void onProductDetailsLoaded(final ProductDetails productDetails) {
            notifyProductDetailsLoaded(productDetails);
        }

        @Override
        public void onError(final ErrorBundle errorBundle) {
           notifyError(errorBundle);
        }
    };

    //self explanatory
    private void notifyError(final ErrorBundle errorBundle) {
        postExecutionThread.post(new Runnable() {
            @Override
            public void run() {
                GetProductDetailsUseCaseImpl.this.providedCallback.onError(errorBundle);
            }
        });
    }

    //self explanatory
    private void notifyProductDetailsLoaded(final ProductDetails productDetails) {
        postExecutionThread.post(new Runnable() {
            @Override
            public void run() {

                GetProductDetailsUseCaseImpl.this.providedCallback.onProductDetailsLoaded(productDetails);
            }
        });
    }
}
