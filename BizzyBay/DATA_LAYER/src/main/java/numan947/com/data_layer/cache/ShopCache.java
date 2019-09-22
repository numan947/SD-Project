package numan947.com.data_layer.cache;

import java.util.List;

import numan947.com.data_layer.entity.ShopDetailsEntity;
import numan947.com.data_layer.entity.ShopListEntity;

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
