package merchant.com.merchant_data_layer.cache;

import merchant.com.merchant_data_layer.entity.UserDetailsEntity;

/**
 * @author numan947
 * @since 7/7/17.<br>
 **/

public interface UserDetailsCache {

    public interface UserDetailsCallback{
        void onUserDetailsLoaded(UserDetailsEntity userDetailsEntity);
        void onError(Exception exception);
    }

    void loadUserDetails(int userId, UserDetailsCallback providedCallback);
}
