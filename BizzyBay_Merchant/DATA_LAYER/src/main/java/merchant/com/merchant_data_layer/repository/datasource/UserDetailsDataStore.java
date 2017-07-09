package merchant.com.merchant_data_layer.repository.datasource;

import merchant.com.merchant_data_layer.entity.UserDetailsEntity;

/**
 * @author numan947
 * @since 7/7/17.<br>
 **/

public interface UserDetailsDataStore {
    public interface UserDetailsCallback{
        void onUserDetailsLoaded(UserDetailsEntity userDetailsEntity);
        void onError(Exception exception);
    }
    void getUserDetails(int userId, UserDetailsCallback providedCallback);
}
