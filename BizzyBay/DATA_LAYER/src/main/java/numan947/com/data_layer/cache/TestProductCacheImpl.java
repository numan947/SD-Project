package numan947.com.data_layer.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import numan947.com.data_layer.entity.DetailsProductEntity;
import numan947.com.data_layer.entity.ListProductEntity;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * Test Product Cache, //todo replace with actual cache
 **/

public class TestProductCacheImpl implements ProductCache {
    private Collection<ListProductEntity>productEntities;
    private Random random = new Random();

    private static int cnt=0;
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
        public static final TestProductCacheImpl INSTANCE = new TestProductCacheImpl();
    }

    public static synchronized TestProductCacheImpl getInstance(){return LAZYHOLDER.INSTANCE;}



    private TestProductCacheImpl() {
        //initialize the productEntities here
        productEntities = new ArrayList<>();
    }

    @Override
    public void get(final int productId,final int shopId, ProductEntityCacheCallback callback) {
        //todo load demo details here
        DetailsProductEntity detailsProductEntity = new DetailsProductEntity();

        ArrayList<String>cat = new ArrayList<>();
        cat.add("Sale1");
        cat.add("Sale2");
        cat.add("Sale3");
        cat.add("Sale4");
        cat.add("Sale5");


        detailsProductEntity.setCarted((productId+shopId)%2==0);
        detailsProductEntity.setLiked((productId+shopId)%2==0&&productId%2==0&&shopId%2==0);
        detailsProductEntity.setProductCategory(cat);
        detailsProductEntity.setProductDetails("Lorem Ipsum is simply dummy text of the printing and " +
                "typesetting industry. Lorem Ipsum has been the industry's " +
                "standard dummy text ever since the 1500s, when an unknown printer t" +
                "ook a galley of type and scrambled it to make a type specimen book. " +
                "It has survived not only five centuries, but also the leap into electronic " +
                "typesetting, remaining essentially unchanged. It was popularised in the" +
                " 1960s with the release of Letraset sheets containing Lorem Ipsum passages, a" +
                "nd more recently with desktop publishing " +
                "software like Aldus PageMaker including versions of Lorem Ipsum");

        detailsProductEntity.setProductId(productId+shopId+random.nextInt(1234));
        detailsProductEntity.setShopId(productId+shopId+random.nextInt(1234));
        detailsProductEntity.setShopName("THIS IS SPARTA");
        detailsProductEntity.setShopLocation("SPARTA,SPAIN");
        detailsProductEntity.setProductPrice(random.nextInt(3000));
        detailsProductEntity.setProductTitle("What is Lorem Ipsum?");
        detailsProductEntity.setProductImages(new ArrayList<>(Arrays.asList(placeHolders)));

        //give the illusion of loading
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        callback.onProductEntityLoaded(detailsProductEntity);
    }

    @Override
    public void put(final int productId,final int shopId) {
        //todo cache here
    }

    @Override
    public void get(int pageNumber,int shopId,ProductEntityListCacheCallback callback) {

        //loading


        productEntities.clear();
        if(cnt>=3&&pageNumber>=3){
            System.out.println("CNT ==3 ");
            callback.onProductEntityListLoaded(-1,productEntities);
            return;
        }
        cnt++;

        if (pageNumber==0) try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i=0;i<20;i++){
            int pos = 20*(pageNumber+1);
            ListProductEntity a;
            if(shopId==-1){
                a= new ListProductEntity(pos+i+1,
                        pos+i+1,"ProductTitle"+pos+" "+i,(pos+i+1)*100,
                        "SHOP details here",
                        ((pos+i)%2)==1,placeHolders[(pos+i)%placeHolders.length]);

            }
            else{
                a= new ListProductEntity(pos+i+1,
                        pos+i+1,"ProductTitle "+shopId+" "+i,(shopId+i+1)*100,
                        "SHOP details here:....shopId "+shopId,
                        ((pos+i)%2)==1,placeHolders[(pos+i)%placeHolders.length]);

            }
            productEntities.add(a);
        }

        callback.onProductEntityListLoaded(pageNumber,this.productEntities);
    }


    @Override
    public void put(Collection<ListProductEntity> productEntities) {
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
