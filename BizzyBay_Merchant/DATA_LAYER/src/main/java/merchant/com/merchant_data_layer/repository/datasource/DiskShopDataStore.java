package merchant.com.merchant_data_layer.repository.datasource;

import java.util.List;

import merchant.com.merchant_data_layer.cache.ShopCache;
import merchant.com.merchant_data_layer.entity.ShopDetailsEntity;
import merchant.com.merchant_data_layer.entity.ShopListEntity;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public class DiskShopDataStore implements ShopDataStore {


    private ShopCache shopCache;

    public DiskShopDataStore(ShopCache shopCache) {
        this.shopCache = shopCache;
    }

    @Override
    public void getShopEntityList(int pageNumber, final ShopListCallback providedCallback) {
        shopCache.getShopList(pageNumber, new ShopCache.ShopListCallback() {
            @Override
            public void onShopListLoaded(int pageNumber, List<ShopListEntity> shopListEntities) {
                providedCallback.OnShopListLoaded(pageNumber,shopListEntities);
            }

            @Override
            public void onError(Exception exception) {
                providedCallback.OnError(exception);
            }
        });
    }

    @Override
    public void getShopEntityDetails(int shopId, final ShopDetailsCallback providedCallback) {
        shopCache.getShopDetails(shopId, new ShopCache.ShopDetailsCallback() {
            @Override
            public void onShopDetailsLoaded(ShopDetailsEntity shopDetailsEntity) {
                providedCallback.OnShopDetailsLoaded(shopDetailsEntity);
            }

            @Override
            public void onError(Exception exception) {
                providedCallback.OnError(exception);
            }
        });
    }
}
