package merchant.com.merchant_data_layer.cache;

import java.util.List;

import merchant.com.merchant_data_layer.entity.ShopDetailsEntity;
import merchant.com.merchant_data_layer.entity.ShopListEntity;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public interface ShopCache {

    public interface ShopDetailsCallback{
        void onShopDetailsLoaded(ShopDetailsEntity shopDetailsEntity);
        void onError(Exception exception);
    }

    public interface ShopListCallback{
        void onShopListLoaded(int pageNumber, List<ShopListEntity> shopListEntities);
        void onError(Exception exception);
    }

    void getShopDetails(int shopId,ShopDetailsCallback providedCallback);

    void getShopList(int pageNumber,ShopListCallback providedCallback);

}
