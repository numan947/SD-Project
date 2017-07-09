package merchant.com.merchant_data_layer.repository.datasource;

import merchant.com.merchant_data_layer.cache.UserDetailsCache;
import merchant.com.merchant_data_layer.entity.UserDetailsEntity;

/**
 * @author numan947
 * @since 7/7/17.<br>
 **/

public class DiskUserDetailsDataStore implements UserDetailsDataStore {

    private UserDetailsCache userDetailsCache;

    public DiskUserDetailsDataStore(UserDetailsCache userDetailsCache) {
        this.userDetailsCache = userDetailsCache;
    }

    @Override
    public void getUserDetails(int userId, final UserDetailsCallback providedCallback) {

        userDetailsCache.loadUserDetails(userId, new UserDetailsCache.UserDetailsCallback() {
            @Override
            public void onUserDetailsLoaded(UserDetailsEntity userDetailsEntity) {
                providedCallback.onUserDetailsLoaded(userDetailsEntity);
            }

            @Override
            public void onError(Exception exception) {
                providedCallback.onError(exception);
            }
        });
    }
}
