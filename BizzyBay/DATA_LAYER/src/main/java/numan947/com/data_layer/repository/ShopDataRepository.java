package numan947.com.data_layer.repository;

import com.example.ShopDetails;
import com.example.ShopList;
import com.example.repository.ShopRepository;

import java.util.ArrayList;
import java.util.List;

import numan947.com.data_layer.entity.ShopDetailsEntity;
import numan947.com.data_layer.entity.ShopListEntity;
import numan947.com.data_layer.entity.mapper.ShopEntityDataMapper;
import numan947.com.data_layer.exception.RepositoryErrorBundle;
import numan947.com.data_layer.repository.datasource.ShopDataStore;
import numan947.com.data_layer.repository.datasource.ShopDataStoreFactory;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public class ShopDataRepository implements ShopRepository {

    //todo remove these debug thingy
    static int cnt=0;
    static int cnt2=0;


    private static ShopDataRepository INSTANCE;

    //DataRepositories are Singleton and static, they'll always exist from the start of the application
    // needs dataStroreFactory to create dataStore and dataMapper to map the data from data-layer to domain-layer

    public static synchronized ShopDataRepository getInstance(ShopDataStoreFactory dataStoreFactory, ShopEntityDataMapper shopEntityDataMapper){
        if(INSTANCE==null)INSTANCE = new ShopDataRepository(shopEntityDataMapper, dataStoreFactory);
        return INSTANCE;
    }


    private final ShopEntityDataMapper shopEntityDataMapper;
    private final ShopDataStoreFactory shopDataStoreFactory;

    private ShopDataRepository(ShopEntityDataMapper shopEntityDataMapper, ShopDataStoreFactory shopDataStoreFactory) {
        if(shopDataStoreFactory ==null|| shopEntityDataMapper ==null)
            throw new IllegalArgumentException("Constructor Parameters MUSTN'T BE NULL");
        this.shopEntityDataMapper = shopEntityDataMapper;
        this.shopDataStoreFactory = shopDataStoreFactory;
    }

    @Override
    public void getShopList(int pageNumber, final ShopListCallback providedCallback) {
        //todo create a real data store and load
        System.out.println("ShopREQ....1  "+cnt);
        cnt++;

        final ShopDataStore shopDataStore = this.shopDataStoreFactory.createTestDataStore();


        shopDataStore.getShopEntityList(pageNumber, new ShopDataStore.ShopListCallback() {
            @Override
            public void OnShopListLoaded(int pageNumber, List<ShopListEntity> entities) {
                ArrayList<ShopList>shopList = shopEntityDataMapper.transform(entities);
                providedCallback.OnShopListLoaded(pageNumber,shopList);
            }

            @Override
            public void OnError(Exception exception) {
                providedCallback.OnError(new RepositoryErrorBundle(exception));
            }
        });



    }

    @Override
    public void getShopDetails(int shopId, final ShopDetailsCallback providedCallback) {
        System.out.println("ShopREQ....2  "+cnt2);
        cnt2++;

        ShopDataStore shopDataStore = shopDataStoreFactory.createTestDataStore();

        shopDataStore.getShopEntityDetails(shopId, new ShopDataStore.ShopDetailsCallback() {
            @Override
            public void OnShopDetailsLoaded(ShopDetailsEntity shopDetailsEntity) {
                ShopDetails shopDetails = shopEntityDataMapper.transform(shopDetailsEntity);
                providedCallback.OnShopDetailsLoaded(shopDetails);
            }

            @Override
            public void OnError(Exception exception) {
                providedCallback.OnError(new RepositoryErrorBundle(exception));
            }
        });

    }
}
