package com.merchant_example.repository;

import com.merchant_example.UserDetails;
import com.merchant_example.exception.ErrorBundle;

/**
 * @author numan947
 * @since 7/7/17.<br>
 **/

public interface UserDetailsRepository {
    public interface UserDetailsCallback{
        void onUserDetailsLoaded(UserDetails userDetails);
        void onError(ErrorBundle errorBundle);
    }

    public void loadUserDetails(int userId,UserDetailsCallback providedCallback);
}
