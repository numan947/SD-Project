package com.example.interactor;

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

    public GetProductDetailsUseCaseImpl(ProductRepository productRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.productRepository = productRepository;
    }
}
