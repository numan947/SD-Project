package com.merchant_example.interactor;

import com.merchant_example.CartList;
import com.merchant_example.exception.ErrorBundle;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public interface GetCartListUseCase  extends BaseUseCase{
    public interface Callback{
        void onCartListLoaded(int page, ArrayList<CartList> cartList);
        void onError(ErrorBundle errorBundle);
    }

    void getCartList(int page,Callback providedCallback);

}
