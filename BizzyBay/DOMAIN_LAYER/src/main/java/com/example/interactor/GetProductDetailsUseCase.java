package com.example.interactor;

import com.example.DetailsProduct;
import com.example.exception.ErrorBundle;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * Interface for implementing the GetProductDetails Use Case
 **/

public interface GetProductDetailsUseCase extends BaseUseCase {
    /**
     * Call back which MUST be provided by any of the Presenters
     * using the use case
     * */
    public interface Callback{

        /**
         * What to do when the use case is successfully executed.
         * */
        void onProductDetailsLoaded(DetailsProduct product);

        /**
         * what to do when the use case couldn't complete successfully.
         * */
        void onError(ErrorBundle errorBundle);
    }

    /**
     *  executes the use case.
     * */
    void execute(int productId,int shopId,Callback callback);
}
