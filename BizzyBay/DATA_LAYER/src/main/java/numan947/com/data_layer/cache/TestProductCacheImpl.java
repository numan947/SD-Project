package numan947.com.data_layer.cache;

import java.util.ArrayList;
import java.util.Collection;

import numan947.com.data_layer.entity.ListProductEntity;

/**
 * Created by numan947 on 5/1/17.
 */

public class TestProductCacheImpl implements ProductCache {
    private Collection<ListProductEntity>productEntities;

    private String[] placeHolders =  new String[]{
            "http://placeimg.com/640/480/animals",
            "http://placeimg.com/640/480/arch",
            "http://placeimg.com/640/480/nature",
            "http://placeimg.com/640/480/people",
            "http://placeimg.com/640/480/tech"
    };


    public static class LAZYHOLDER{
        public static final TestProductCacheImpl INSTANCE = new TestProductCacheImpl();
    }

    public static synchronized TestProductCacheImpl newInstance(){return LAZYHOLDER.INSTANCE;}



    private TestProductCacheImpl() {
        //initialize the productEntities here
        productEntities = new ArrayList<>();
    }

    @Override
    public void get(int productId, ProductEntityCacheCallback callback) {
        //todo load demo details here
    }

    @Override
    public void put(int productId) {
        //todo cache here
    }

    @Override
    public void get(ProductEntityListCacheCallback callback) {

        //loading

        productEntities.clear();

        for(int i=0;i<20;i++){
            ListProductEntity a= new ListProductEntity(i+1,
                    i+1,"ProductTitle"+i,(i+1)*100,
                    "SHOP details here",
                    (i%2)==1,placeHolders[i%5]);

            productEntities.add(a);
        }

        callback.onProductEntityListLoaded(this.productEntities);
    }


    @Override
    public void put(Collection<ListProductEntity> productEntities) {
        //todo implement later
    }

    @Override
    public boolean isCachecd(int productId) {
        return false;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void eraseAllCache() {

    }
}
