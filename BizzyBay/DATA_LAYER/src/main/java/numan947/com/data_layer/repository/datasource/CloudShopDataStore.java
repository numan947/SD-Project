package numan947.com.data_layer.repository.datasource;

import android.content.Context;

import java.util.Collection;
import java.util.List;

import numan947.com.data_layer.entity.ShopListEntity;
import numan947.com.data_layer.internet.RestApiCommon;

/**
 * @author numan947
 * @since 7/10/17.<br>
 **/

public class CloudShopDataStore implements ShopDataStore {

    private Context context;

    RestApiCommon restApiCommon;

    public CloudShopDataStore(RestApiCommon restApiCommon)
    {
        this.restApiCommon = restApiCommon;
    }


    @Override
    public void getShopEntityList(final int pageNumber, final ShopListCallback providedCallback) {
        restApiCommon.getShopList(pageNumber, new RestApiCommon.ShopListCallback() {
            @Override
            public void onShopListLoaded(Collection<ShopListEntity> shopListEntities) {
                providedCallback.OnShopListLoaded(pageNumber, (List<ShopListEntity>) shopListEntities);
            }

            @Override
            public void onError(Exception exception) {
                providedCallback.OnError(exception);
            }
        });
    }

    @Override
    public void getShopEntityDetails(int shopId, ShopDetailsCallback providedCallback) {
        //// TODO: 7/10/17

    }
}
