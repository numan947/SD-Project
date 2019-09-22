package merchant.com.merchant_data_layer.repository;

import com.merchant_example.CartList;
import com.merchant_example.WishList;
import com.merchant_example.repository.CartListWishListRepository;

import java.util.ArrayList;

import merchant.com.merchant_data_layer.entity.CartListEntity;
import merchant.com.merchant_data_layer.entity.WishListEntity;
import merchant.com.merchant_data_layer.entity.mapper.CartListWishListEntityDataMapper;
import merchant.com.merchant_data_layer.exception.RepositoryErrorBundle;
import merchant.com.merchant_data_layer.repository.datasource.CartListWishListDataStore;
import merchant.com.merchant_data_layer.repository.datasource.CartListWishListDataStoreFactory;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class CartListWishListDataRepository implements CartListWishListRepository {

    //todo remove these debug thingy
    private static int cnt=0;
    private static int cnt2=0;

    private static CartListWishListDataRepository INSTANCE;

    private CartListWishListEntityDataMapper mapper;
    private CartListWishListDataStoreFactory dataStoreFactory;


    private CartListWishListDataRepository(CartListWishListEntityDataMapper mapper, CartListWishListDataStoreFactory dataStoreFactory) {
        this.mapper = mapper;
        this.dataStoreFactory = dataStoreFactory;
    }

    public static synchronized CartListWishListDataRepository getInstance(CartListWishListEntityDataMapper mapper,
                                                                   CartListWishListDataStoreFactory dataStore)
    {
        //todo add null check
        if(INSTANCE==null)INSTANCE = new CartListWishListDataRepository(mapper,dataStore);
        return INSTANCE;
    }


    @Override
    public void loadCartList(int page, final CartListCallback providedCallback) {
        System.out.println("Cart REQ....1  "+cnt);
        cnt++;

        CartListWishListDataStore dataStore = dataStoreFactory.createTestDataStore();

        CartListWishListDataStore.CartListCallback createdCallback = new CartListWishListDataStore.CartListCallback() {
            @Override
            public void onCartListLoaded(int page, ArrayList<CartListEntity> cartListEntities) {

                ArrayList<CartList>cartLists = mapper.transformCartList(cartListEntities);

                providedCallback.onCartListLoaded(page,cartLists);
            }

            @Override
            public void onError(Exception exception) {
                providedCallback.onError(new RepositoryErrorBundle(exception));
            }
        };


        dataStore.loadCartList(page,createdCallback);

    }

    @Override
    public void loadWishList(int page, final WishListCallback providedCallback) {
        System.out.println("Wish REQ....1  "+cnt2);
        cnt2++;

        CartListWishListDataStore dataStore = dataStoreFactory.createTestDataStore();

        CartListWishListDataStore.WishListCallback createdCallback = new CartListWishListDataStore.WishListCallback() {
            @Override
            public void onWishListLoaded(int page, ArrayList<WishListEntity> wishListEntities) {
                ArrayList<WishList>wishLists = mapper.transformWishList(wishListEntities);

                providedCallback.onWishListLoaded(page,wishLists);
            }

            @Override
            public void onError(Exception exception) {
                providedCallback.onError(new RepositoryErrorBundle(exception));
            }
        };

        dataStore.loadWishList(page,createdCallback);
    }
}
