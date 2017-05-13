package numan947.com.data_layer.cache;

import java.util.ArrayList;
import java.util.Random;

import numan947.com.data_layer.entity.ShopListEntity;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public class TestShopCacheImpl implements ShopCache {

    private ArrayList<ShopListEntity> shopEntities;

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
        public static final TestShopCacheImpl INSTANCE = new TestShopCacheImpl();
    }

    public static synchronized TestShopCacheImpl getInstance(){return TestShopCacheImpl.LAZYHOLDER.INSTANCE;}

    private TestShopCacheImpl()
    {
        this.shopEntities = new ArrayList<>();

    }


    @Override
    public void getShopDetails(int shopId, ShopDetailsCallback providedCallback) {
        //todo
    }

    @Override
    public void getShopList(int pageNumber, ShopListCallback providedCallback) {
        //todo
    }
}
