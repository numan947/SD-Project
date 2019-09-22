package numan947.com.data_layer.repository.datasource;

import android.content.Context;

import numan947.com.data_layer.cache.UserDetailsCache;

/**
 * @author numan947
 * @since 7/7/17.<br>
 **/

public class UserDetailsDataStoreFactory {
    private Context context;
    private UserDetailsCache userDetailsCache;

    public UserDetailsDataStoreFactory(Context context, UserDetailsCache userDetailsCache) {
        if(context==null||userDetailsCache==null)throw new IllegalArgumentException("NOT NULL PARAM NEEDED");
        this.context = context;
        this.userDetailsCache = userDetailsCache;
    }

    public UserDetailsDataStore createTestDataStore() {
        return new DiskUserDetailsDataStore(userDetailsCache);
    }
}
