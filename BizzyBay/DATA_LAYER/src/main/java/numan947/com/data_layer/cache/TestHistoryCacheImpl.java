package numan947.com.data_layer.cache;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import numan947.com.data_layer.entity.HistoryDetailsEntity;
import numan947.com.data_layer.entity.HistoryListEntity;
import numan947.com.data_layer.entity.HistoryPerProductEntity;
import numan947.com.data_layer.entity.HistoryPerShopEntity;

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

            ArrayList<HistoryPerShopEntity>perShopEntities = new ArrayList<>();
            for(int k=0;k<3;k++) {


                ArrayList<HistoryPerProductEntity>perProductEntities = new ArrayList<>();
                for (int j = 0; j < 5; j++) {
                    HistoryPerProductEntity historyPerProductEntity = new HistoryPerProductEntity(j, j, j, "ProductName " + j,
                            "ProductQuantity" + j, "ProductStatus" + j, "ProductPrice" + j, placeHolders[random.nextInt(3000) % placeHolders.length]);

                    perProductEntities.add(historyPerProductEntity);
                }
                HistoryPerShopEntity historyPerShopEntity = new HistoryPerShopEntity("ShopName "+k,perProductEntities);

                perShopEntities.add(historyPerShopEntity);
            }
            HistoryListEntity historyListEntity = new HistoryListEntity(this.getRandomDate(),perShopEntities);

            historyEntities.add(historyListEntity);
        }

        providedCallback.onHistoryListLoaded(pageNumber,historyEntities);
    }

    private String getRandomDate() {

        GregorianCalendar cal = new GregorianCalendar();
        int year = randBetween(1990,2016);

        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.DAY_OF_YEAR,randBetween(1,cal.getActualMaximum(Calendar.DAY_OF_YEAR)));

        return cal.toString();

    }
    private int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
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
