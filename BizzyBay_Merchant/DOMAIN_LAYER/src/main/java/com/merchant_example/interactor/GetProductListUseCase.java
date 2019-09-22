package com.merchant_example.interactor;

import com.merchant_example.ProductList;
import com.merchant_example.exception.ErrorBundle;

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
        void onProductsListLoaded(int pageNumber, Collection<ProductList> productLists);

        /**
         * what to do when the use case couldn't complete successfully.
         * */
        void onError(ErrorBundle errorBundle);
    }
    /**
     *  executes the use case.
     * */
    void execute(int pageNumber, int shopId, Callback callback);
}
