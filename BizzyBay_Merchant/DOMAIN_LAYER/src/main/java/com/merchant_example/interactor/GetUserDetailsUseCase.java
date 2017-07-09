package com.merchant_example.interactor;

import com.merchant_example.UserDetails;
import com.merchant_example.exception.ErrorBundle;

/**
 * @author numan947
 * @since 7/7/17.<br>
 **/

public interface GetUserDetailsUseCase extends BaseUseCase {
    public interface Callback{
        void onUserDetailsLoaded(UserDetails userDetails);
        void onError(ErrorBundle errorBundle);
    }

    public void getUserDetails(int userId,Callback providedCallback);
}
