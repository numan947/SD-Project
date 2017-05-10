package numan947.com.data_layer.cache;

import java.util.ArrayList;
import java.util.Random;

import numan947.com.data_layer.entity.HistoryDetailsEntity;
import numan947.com.data_layer.entity.HistoryListEntity;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class TestHistoryCacheImpl implements HistoryCache {

    private ArrayList<HistoryListEntity> historyEntities;
    Random random = new Random();

    private String[] placeHolders =  new String[]{
            "http://placeimg.com/640/480/animals",
            "http://placeimg.com/640/480/arch",
            "http://placeimg.com/640/480/nature",
            "http://placeimg.com/640/480/people",
            "http://placeimg.com/640/480/tech",
            "https://placebear.com/640/480",
            "https://placebear.com/200/300",
            "http://placebear.com/640/400",
            "http://placebear.com/300/400",
            "http://placebear.com/400/500",
            "http://placebear.com/400/400"

    };

    public static class LAZYHOLDER{
        public static final TestHistoryCacheImpl INSTANCE = new TestHistoryCacheImpl();
    }

    public static synchronized TestHistoryCacheImpl getInstance(){return TestHistoryCacheImpl.LAZYHOLDER.INSTANCE;}

    private TestHistoryCacheImpl()
    {
        this.historyEntities = new ArrayList<>();
    }


    @Override
    public void getHistoryList(int pageNumber, HistoryListCallback providedCallback) {
        historyEntities.clear();

        if(pageNumber==0) try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for(int i=0;i<10;i++){

            HistoryListEntity historyListEntity = new HistoryListEntity()


        }




    }

    @Override
    public void putHistoryList(ArrayList<HistoryListEntity> historyListEntities) {

    }

    @Override
    public void getHistoryDetails(int orderId, int shopId, int productId, HistoryDetailsCallback providedCallback) {

    }

    @Override
    public void putHistoryDetails(HistoryDetailsEntity historyDetailsEntity) {

    }
}
