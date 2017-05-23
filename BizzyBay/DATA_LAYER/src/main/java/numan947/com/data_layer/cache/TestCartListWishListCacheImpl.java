package numan947.com.data_layer.cache;

import java.util.ArrayList;
import java.util.Random;

import numan947.com.data_layer.entity.CartListEntity;
import numan947.com.data_layer.entity.CartProductEntity;
import numan947.com.data_layer.entity.WishListEntity;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class TestCartListWishListCacheImpl implements CartListWishListCache {


    private ArrayList<CartListEntity> cartListEntities;
    private ArrayList<WishListEntity> wishListEntities;


    Random random = new Random();

    private String[] placeHolders =  new String[]{
            "http://placeimg.com/640/480/animals",
            "http://placeimg.com/640/480/arch",
            "http://placeimg.com/640/480/nature",
            "http://placeimg.com/640/480/people",
            "http://placeimg.com/640/480/tech",
            "https://placebear.com/640/480",
            "https://placebear.com/200/300",
            "http://placebear.com/640/400",
            "http://placebear.com/300/400",
            "http://placebear.com/400/500",
            "http://placebear.com/400/400"

    };


    public static class LAZYHOLDER{
        public static final TestCartListWishListCacheImpl INSTANCE =  new TestCartListWishListCacheImpl();
    }

    public static synchronized TestCartListWishListCacheImpl getInstance(){return TestCartListWishListCacheImpl.LAZYHOLDER.INSTANCE;}

    private TestCartListWishListCacheImpl()
    {
        this.cartListEntities = new ArrayList<>();
        this.wishListEntities  = new ArrayList<>();
    }

    @Override
    public void getCartList(int page, CartListCallback providedCallback) {

        this.cartListEntities.clear();
        

        int t=0;

        for(int i=0;i<12;i++){

            ArrayList<CartProductEntity>cartProductEntities = new ArrayList<>();
            for(int j=0;j<5;j++){
                CartProductEntity cartProductEntity = new CartProductEntity("Product Name: "+page+" "+j,
                        random.nextInt(123450)%123,random.nextInt(123450)%123,
                        random.nextInt(123450)%123,placeHolders[random.nextInt(1234)%placeHolders.length],
                        random.nextInt(12235));

                cartProductEntities.add(cartProductEntity);
                t++;
            }

            CartListEntity cartListEntity = new CartListEntity("ShopName "+page+" "+i,
                    cartProductEntities,random.nextInt(123456),
                    random.nextInt(1234),random.nextInt(1234));

            this.cartListEntities.add(cartListEntity);
        }

        System.out.println("WHY IS TTTTTTTTTTTT "+t);
        providedCallback.onCartListLoaded(page,cartListEntities);

    }

    @Override
    public void getWishList(int pager, WishListCallback providedCallback) {
        //todo implement later
    }
}
