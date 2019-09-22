package numan947.com.data_layer.repository;

import com.example.HistoryDetails;
import com.example.HistoryList;
import com.example.repository.HistoryRepository;

import java.util.ArrayList;

import numan947.com.data_layer.entity.HistoryDetailsEntity;
import numan947.com.data_layer.entity.HistoryListEntity;
import numan947.com.data_layer.entity.mapper.HistoryEntityDataMapper;
import numan947.com.data_layer.exception.RepositoryErrorBundle;
import numan947.com.data_layer.repository.datasource.HistoryDataStore;
import numan947.com.data_layer.repository.datasource.HistoryDataStoreFactory;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryDataRepository implements HistoryRepository {
    //todo remove these debug thingy
    static int cnt=0;
    static int cnt2=0;


    private static HistoryDataRepository INSTANCE;

    //DataRepositories are Singleton and static, they'll always exist from the start of the application
    // needs dataStroreFactory to create dataStore and dataMapper to map the data from data-layer to domain-layer

    public static synchronized HistoryDataRepository getInstance(HistoryDataStoreFactory dataStoreFactory, HistoryEntityDataMapper historyEntityDataMapper){
        if(INSTANCE==null)INSTANCE = new HistoryDataRepository(historyEntityDataMapper, dataStoreFactory);
        return INSTANCE;
    }


    private final HistoryEntityDataMapper historyEntityDataMapper;
    private final HistoryDataStoreFactory historyDataStoreFactory;

    private HistoryDataRepository(HistoryEntityDataMapper mapper,HistoryDataStoreFactory factory)
    {
        this.historyDataStoreFactory = factory;
        this.historyEntityDataMapper = mapper;

    }




    @Override
    public void getHistoryList(int pageNumber, final HistoryListCallback providedCallback) {

        System.out.println("History REQ....1  "+cnt);
        cnt++;

        HistoryDataStore dataStore = this.historyDataStoreFactory.createTestDataStore();

        HistoryDataStore.HistoryListCallback createdCallback = new HistoryDataStore.HistoryListCallback() {
            @Override
            public void onHistoryListLoaded(int pageNumber, ArrayList<HistoryListEntity> listEntities) {
                ArrayList<HistoryList>historyList = historyEntityDataMapper.transform(listEntities);

                providedCallback.onHistoryListLoaded(pageNumber,historyList);

            }

            @Override
            public void onError(Exception exception) {
                providedCallback.onError(new RepositoryErrorBundle(exception));
            }
        };

        dataStore.getHistoryList(pageNumber,createdCallback);

    }

    @Override
    public void getHistoryDetails(int orderId, int shopId, int productId, final HistoryDetailsCallback providedCallback) {

        System.out.println("History REQ....2  "+cnt2);
        cnt2++;


        HistoryDataStore dataStore = historyDataStoreFactory.createTestDataStore();

        HistoryDataStore.HistoryDetailsCallback createdCallback = new HistoryDataStore.HistoryDetailsCallback() {
            @Override
            public void onHistoryDetailsLoaded(HistoryDetailsEntity historyDetailsEntity) {
                HistoryDetails details = historyEntityDataMapper.transform(historyDetailsEntity);
                providedCallback.onHistoryDetailsLoaded(details);
            }

            @Override
            public void onError(Exception exception) {
                providedCallback.onError(new RepositoryErrorBundle(exception));
            }
        };

        dataStore.getHistoryDetails(orderId,shopId,productId,createdCallback);
    }
}
