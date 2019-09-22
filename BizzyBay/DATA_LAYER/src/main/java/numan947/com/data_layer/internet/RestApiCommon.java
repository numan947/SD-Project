/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package numan947.com.data_layer.internet;


import java.util.Collection;

import numan947.com.data_layer.entity.DetailsProductEntity;
import numan947.com.data_layer.entity.ListProductEntity;
import numan947.com.data_layer.entity.ShopListEntity;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApiCommon {
    /**
     * Callback used to be notified when either a user list has been loaded or an error happened.
     */
    interface ProductListCallback {
        void onProductListLoaded(Collection<ListProductEntity> productCollection);

        void onError(Exception exception);
    }

    /**
     * Callback to be notified when getting a user from the network.
     */
    interface ProductDetailsCallback {
        void onProductEntityLoaded(DetailsProductEntity detailsProductEntity);

        void onError(Exception exception);
    }


    public interface ShopListCallback{
        void onShopListLoaded(Collection<ShopListEntity>shopListEntities);
        void onError(Exception exception);
    }

    static final String API_BASE_URL = "http://192.168.1.3:8000/";

    /** Api url for getting all users */
    static final String API_URL_GET_PRODUCT_LIST = API_BASE_URL + "allproducts/";
    /** Api url for getting a user profile: Remember to concatenate id + 'json' */
    static final String API_URL_GET_PRODUCT_DETAILS = API_BASE_URL + "product/";//todo

    static final String API_URL_GET_SHOP_LIST=API_BASE_URL+"allshops/";

    void getProductList(int page,ProductListCallback listProductEntityCallback);


    void getProductById(final int productId, final ProductDetailsCallback detailsProductEntityCallback);


    void getShopList(int page,ShopListCallback listShopCallback);
}
