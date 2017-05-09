package com.example.interactor;

import com.example.ListProduct;
import com.example.exception.ErrorBundle;

import java.util.Collection;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * Interface for implementing GetProductList Use case.
 **/

public interface GetProductListUseCase extends BaseUseCase {

    /**
     * Call back which MUST be provided by any of the Presenters
     * using the use case
     * */
    interface Callback{
        /**
         * What to do when the use case is successfully executed.
         * */
        void onProductsListLoaded(Collection<ListProduct> listProducts);

        /**
         * what to do when the use case couldn't complete successfully.
         * */
        void onError(ErrorBundle errorBundle);
    }
    /**
     *  executes the use case.
     * */
    void execute(Callback callback);
}
