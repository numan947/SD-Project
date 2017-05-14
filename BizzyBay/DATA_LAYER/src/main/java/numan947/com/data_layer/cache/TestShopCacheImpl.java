package numan947.com.data_layer.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import numan947.com.data_layer.entity.ShopListEntity;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public class TestShopCacheImpl implements ShopCache {

    private List<ShopListEntity> shopEntities;

    Random random = new Random();
    static  int cnt=0;

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
        public static final TestShopCacheImpl INSTANCE = new TestShopCacheImpl();
    }

    public static synchronized TestShopCacheImpl getInstance(){return TestShopCacheImpl.LAZYHOLDER.INSTANCE;}

    private TestShopCacheImpl()
    {
        this.shopEntities = Collections.synchronizedList(new ArrayList<ShopListEntity>());

    }


    @Override
    public void getShopDetails(int shopId, ShopDetailsCallback providedCallback) {
        //todo
    }


    @Override
    public void getShopList(int pageNumber, ShopListCallback providedCallback) {
        shopEntities.clear();

        if(pageNumber==0) try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(pageNumber>3){
            providedCallback.onShopListLoaded(-1,shopEntities);
            return;
        }

        cnt++;

        for(int i=0;i<10;i++){
            ShopListEntity  shopListEntity = new ShopListEntity((10*pageNumber)+i,placeHolders[random.nextInt(100)%placeHolders.length],
                    "Shop small details here............................asdasdas.d.sd.a.d.asd.as.das.d.asd",
                    "ShopType","ShopLocation, Mars",i+1,"Shop name");
            shopEntities.add(shopListEntity);
        }
        providedCallback.onShopListLoaded(pageNumber,shopEntities);
    }
}
