package merchant.com.merchant_data_layer.repository.datasource;

import java.util.List;

import merchant.com.merchant_data_layer.entity.ShopDetailsEntity;
import merchant.com.merchant_data_layer.entity.ShopListEntity;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public interface ShopDataStore {
    public interface ShopListCallback{
        void OnShopListLoaded(int pageNumber, List<ShopListEntity> entities);
        void OnError(Exception exception);
    }

    public interface ShopDetailsCallback{
        void OnShopDetailsLoaded(ShopDetailsEntity shopDetailsEntity);
        void OnError(Exception exception);
    }

    void getShopEntityList(int pageNumber, ShopListCallback providedCallback);

    void getShopEntityDetails(int shopId,ShopDetailsCallback providedCallback);


}
