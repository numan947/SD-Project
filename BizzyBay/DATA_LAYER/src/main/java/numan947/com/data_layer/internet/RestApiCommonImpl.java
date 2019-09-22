package numan947.com.data_layer.internet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Collection;

import numan947.com.data_layer.entity.DetailsProductEntity;
import numan947.com.data_layer.entity.ListProductEntity;
import numan947.com.data_layer.entity.ShopListEntity;
import numan947.com.data_layer.entity.mapper.ProductEntityJsonMapper;
import numan947.com.data_layer.entity.mapper.ShopEntityJsonMapper;
import numan947.com.data_layer.exception.NetworkConnectionException;

/**
 * @author numan947
 * @since 7/10/17.<br>
 **/

public class RestApiCommonImpl implements RestApiCommon {
    private final Context context;


    public RestApiCommonImpl(Context context) {
        if (context == null ) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
    }

    @Override
    public void getProductList(int page,RestApiCommon.ProductListCallback productListCallback) {
        ProductEntityJsonMapper jsonMapper = new ProductEntityJsonMapper();
        if (productListCallback == null) {
            throw new IllegalArgumentException("Callback cannot be null!!!");
        }

        if (isThereInternetConnection()) {
            try {

                ApiConnection getUserListConnection =
                        ApiConnection.createGET(RestApiCommon.API_URL_GET_PRODUCT_LIST);
                String responseUserList = getUserListConnection.requestSyncCall();
                Collection<ListProductEntity> productEntityList =
                        jsonMapper.transformProductEntityCollection(responseUserList);
                productListCallback.onProductListLoaded(productEntityList);
            } catch (Exception e) {
                productListCallback.onError(e);
            }
        } else {
            productListCallback.onError(new NetworkConnectionException());
        }
    }

    @Override
    public void getProductById(int productId, ProductDetailsCallback detailsProductEntityCallback) {

        ProductEntityJsonMapper jsonMapper = new ProductEntityJsonMapper();
        if (detailsProductEntityCallback == null) {
            throw new IllegalArgumentException("Callback cannot be null!!!");
        }

        if (isThereInternetConnection()) {
            try {
                String apiUrl = RestApiCommon.API_URL_GET_PRODUCT_DETAILS + productId; //// TODO: 7/10/17



                ApiConnection getUserDetailsConnection = ApiConnection.createGET(apiUrl);

                String responseUserDetails = getUserDetailsConnection.requestSyncCall();
                DetailsProductEntity detailsProductEntity = jsonMapper.transformDetailsProductEntity(responseUserDetails);



                detailsProductEntityCallback.onProductEntityLoaded(detailsProductEntity);
            } catch (Exception e) {
                detailsProductEntityCallback.onError(new NetworkConnectionException(e.getCause()));
                System.out.println(e.getMessage());
            }
        } else {
            detailsProductEntityCallback.onError(new NetworkConnectionException());
            System.out.println("NEtwork Error -- RestApiImpl");
        }
    }

    @Override
    public void getShopList(int page, ShopListCallback listShopCallback) {
        ShopEntityJsonMapper jsonMapper = new ShopEntityJsonMapper();
        if (listShopCallback == null) {
            throw new IllegalArgumentException("Callback cannot be null!!!");
        }

        if (isThereInternetConnection()) {
            try {

                ApiConnection getUserListConnection =
                        ApiConnection.createGET(RestApiCommon.API_URL_GET_SHOP_LIST);
                String responseUserList = getUserListConnection.requestSyncCall();
                Collection<ShopListEntity> shopEntityList =
                        jsonMapper.transformShopEntityCollection(responseUserList);
                listShopCallback.onShopListLoaded(shopEntityList);
            } catch (Exception e) {
                listShopCallback.onError(e);
            }
        } else {
            listShopCallback.onError(new NetworkConnectionException());
        }
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
