package com.example.repository;

import com.example.UserDetails;
import com.example.exception.ErrorBundle;

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
