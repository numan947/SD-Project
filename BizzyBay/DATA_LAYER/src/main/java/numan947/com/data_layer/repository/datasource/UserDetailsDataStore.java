package numan947.com.data_layer.repository.datasource;

import numan947.com.data_layer.entity.UserDetailsEntity;

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
