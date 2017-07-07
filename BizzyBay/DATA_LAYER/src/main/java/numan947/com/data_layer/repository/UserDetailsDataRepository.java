package numan947.com.data_layer.repository;

import com.example.repository.UserDetailsRepository;

import numan947.com.data_layer.entity.mapper.UserDetailsEntityDataMapper;
import numan947.com.data_layer.repository.datasource.ShopDataStore;
import numan947.com.data_layer.repository.datasource.UserDetailsDataStore;
import numan947.com.data_layer.repository.datasource.UserDetailsDataStoreFactory;

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
        System.out.println("ShopREQ....2  "+cnt2);
        cnt2++;

        UserDetailsDataStore userDetailsDataStore = userDetailsDataStoreFactory.createTestDataStore();


    }
}
