package merchant.com.merchant_data_layer.repository;

import com.merchant_example.UserDetails;
import com.merchant_example.repository.UserDetailsRepository;

import merchant.com.merchant_data_layer.entity.UserDetailsEntity;
import merchant.com.merchant_data_layer.entity.mapper.UserDetailsEntityDataMapper;
import merchant.com.merchant_data_layer.exception.RepositoryErrorBundle;
import merchant.com.merchant_data_layer.repository.datasource.UserDetailsDataStore;
import merchant.com.merchant_data_layer.repository.datasource.UserDetailsDataStoreFactory;

/**
 * @author numan947
 * @since 7/7/17.<br>
 **/

public class UserDetailsDataRepository implements UserDetailsRepository {

    //todo remove these debug thingy
    static int cnt=0;
    static int cnt2=0;


    private static UserDetailsDataRepository INSTANCE;

    //DataRepositories are Singleton and static, they'll always exist from the start of the application
    // needs dataStroreFactory to create dataStore and dataMapper to map the data from data-layer to domain-layer

    public static synchronized UserDetailsDataRepository getInstance(UserDetailsDataStoreFactory dataStoreFactory, UserDetailsEntityDataMapper userDetailsEntityDataMapper){
        if(INSTANCE==null)INSTANCE = new UserDetailsDataRepository(userDetailsEntityDataMapper, dataStoreFactory);
        return INSTANCE;
    }


    private final UserDetailsEntityDataMapper userDetailsEntityDataMapper;
    private final UserDetailsDataStoreFactory userDetailsDataStoreFactory;

    private UserDetailsDataRepository(UserDetailsEntityDataMapper userDetailsEntityDataMapper, UserDetailsDataStoreFactory userDetailsDataStoreFactory) {
        if(userDetailsDataStoreFactory ==null|| userDetailsEntityDataMapper ==null)
            throw new IllegalArgumentException("Constructor Parameters MUSTN'T BE NULL");
        this.userDetailsEntityDataMapper = userDetailsEntityDataMapper;
        this.userDetailsDataStoreFactory = userDetailsDataStoreFactory;
    }


    @Override
    public void loadUserDetails(int userId, final UserDetailsCallback providedCallback) {
        System.out.println("UserDetailsReqREQ....2  "+cnt2);
        cnt2++;

        UserDetailsDataStore userDetailsDataStore = userDetailsDataStoreFactory.createTestDataStore();

        userDetailsDataStore.getUserDetails(userId, new UserDetailsDataStore.UserDetailsCallback() {
            @Override
            public void onUserDetailsLoaded(UserDetailsEntity userDetailsEntity) {
                UserDetails userDetails = userDetailsEntityDataMapper.transform(userDetailsEntity);
                providedCallback.onUserDetailsLoaded(userDetails);
            }

            @Override
            public void onError(Exception exception) {
                providedCallback.onError(new RepositoryErrorBundle(exception));
            }
        });
    }
}
