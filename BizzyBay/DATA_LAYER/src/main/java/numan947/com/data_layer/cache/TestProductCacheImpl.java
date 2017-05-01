package numan947.com.data_layer.cache;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import numan947.com.data_layer.entity.ProductEntity;

/**
 * Created by numan947 on 5/1/17.
 */

public class TestProductCacheImpl implements ProductCache {
    private Collection<ProductEntity>productEntities;
    private TestProductCacheImpl INSTANCE;


    public TestProductCacheImpl() {
        //initialize the productEntities here

        for(int i=0;i<20;i++){
            try {
                productEntities.add(new ProductEntity(i+1,"ProductTtitle "+(i+1),(i+1)*100,"ProductStore "+(i+1),i+1,new URL("http://www.google.com")));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void get(int productId, ProductEntityCacheCallback callback) {
        for(ProductEntity p:productEntities)
            if(p.getProductId()==productId){
                //load details here
                callback.onProductEntityLoaded(p);
            }
    }

    @Override
    public void put(int productId) {
        //todo implement later
    }

    @Override
    public void get(ProductEntityListCacheCallback callback) {
        callback.onProductEntityListLoaded(this.productEntities);
    }

    @Override
    public void put(Collection<ProductEntity> productEntities) {
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
