package com.example.interactor;

import com.example.exception.ErrorBundle;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public interface GetCartListUseCase  extends BaseUseCase{
    public interface Callback{
        void onCartListLoaded(int page);
        void onError(ErrorBundle errorBundle);
    }

    void getCartList(int page,Callback providedCallback);

}
