package merchant.com.merchant_data_layer.cache;

import merchant.com.merchant_data_layer.entity.UserDetailsEntity;

/**
 * @author numan947
 * @since 7/7/17.<br>
 **/

public class TestUserDetailsCacheImpl implements UserDetailsCache {

    private UserDetailsEntity userDetailsEntity;

    public static class LAZYHOLDER{
        public static final TestUserDetailsCacheImpl INSTANCE = new TestUserDetailsCacheImpl();
    }

    public static synchronized TestUserDetailsCacheImpl getInstance(){return TestUserDetailsCacheImpl.LAZYHOLDER.INSTANCE;}

    private TestUserDetailsCacheImpl()
    {
        this.userDetailsEntity = new UserDetailsEntity(1234,"http://placeimg.com/640/480/nature","Geralt of Rivia",
                "@whitewolf","+880152120000000","whitewolfgeralt@kaermoren.com","123456789","+88015212000000","fb.com/geralt.rivia");
    }
    @Override
    public void loadUserDetails(int userId, UserDetailsCallback providedCallback) {

        //todo fetching from saved cache happens here
        providedCallback.onUserDetailsLoaded(userDetailsEntity);
    }
}
