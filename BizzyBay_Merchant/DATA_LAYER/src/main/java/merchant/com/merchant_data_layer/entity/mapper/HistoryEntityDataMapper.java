package merchant.com.merchant_data_layer.entity.mapper;

import com.merchant_example.HistoryDetails;
import com.merchant_example.HistoryList;
import com.merchant_example.HistoryPerProduct;
import com.merchant_example.HistoryPerShop;

import java.util.ArrayList;

import merchant.com.merchant_data_layer.entity.HistoryDetailsEntity;
import merchant.com.merchant_data_layer.entity.HistoryListEntity;
import merchant.com.merchant_data_layer.entity.HistoryPerProductEntity;
import merchant.com.merchant_data_layer.entity.HistoryPerShopEntity;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryEntityDataMapper {

    public HistoryEntityDataMapper() {
    }

    public ArrayList<HistoryList> transform(ArrayList<HistoryListEntity> historyListEntities1) {

        ArrayList<HistoryListEntity>historyListEntities = new ArrayList<>(historyListEntities1);
        ArrayList<HistoryList>historyList = new ArrayList<>();


        for(HistoryListEntity historyListEntity: historyListEntities){

            ArrayList<HistoryPerShopEntity> historyPerShopEntities = historyListEntity.getHistoryPerShopEntities();

            ArrayList<HistoryPerShop>historyPerShops  = new ArrayList<>();


            for(HistoryPerShopEntity historyPerShopEntity: historyPerShopEntities){

                ArrayList<HistoryPerProductEntity> historyPerProductEntities = historyPerShopEntity.getHistoryPerProductEntities();

                ArrayList<HistoryPerProduct> historyPerProducts = new ArrayList<>();

                for(HistoryPerProductEntity historyPerProductEntity: historyPerProductEntities){

                    //todo do some awesome something, that I don't freaking care about -_-
                    HistoryPerProduct tmp1 = new HistoryPerProduct(historyPerProductEntity.getProductId(),
                            historyPerProductEntity.getShopId(),historyPerProductEntity.getOrderId(),
                            historyPerProductEntity.getProductName(),historyPerProductEntity.getProductQuantity(),
                            historyPerProductEntity.getProductStatus(),historyPerProductEntity.getProductPrice(),
                            historyPerProductEntity.getProductImage());

                    historyPerProducts.add(tmp1);
                }

                HistoryPerShop tmp2 = new HistoryPerShop(historyPerShopEntity.getShopName(),historyPerProducts);

                historyPerShops.add(tmp2);
            }


            HistoryList tmp3 = new HistoryList(historyListEntity.getDate(),historyPerShops);
            historyList.add(tmp3);
        }

        return historyList;
    }

    public HistoryDetails transform(HistoryDetailsEntity historyDetailsEntity) {
        //todo transforming here
        HistoryDetails historyDetails = new HistoryDetails();

        historyDetails.setPaymentId(Integer.toString(historyDetailsEntity.getPaymentId()));
        historyDetails.setPaymentMethod(historyDetailsEntity.getPaymentMethod());

        historyDetails.setProductId(historyDetailsEntity.getProductId());
        historyDetails.setProductName(historyDetailsEntity.getProductName());
        historyDetails.setProductDetails(historyDetailsEntity.getProductDetails());
        historyDetails.setProductImage(historyDetailsEntity.getProductImage());
        historyDetails.setProductPrice(historyDetailsEntity.getProductPrice()+"");

        historyDetails.setProductQuantity(""+historyDetailsEntity.getProductQuantity());
        historyDetails.setProductDeliveryLocation(historyDetailsEntity.getProductDeliveryLocation());
        historyDetails.setProductDeliveryTime(historyDetailsEntity.getProductDeliveryTime().toString());

        historyDetails.setProductOrderId(Integer.toString(historyDetailsEntity.getProductOrderId()));
        historyDetails.setProductOrderTime(historyDetailsEntity.getProductOrderTime().toString());
        historyDetails.setProductOrderTime(historyDetailsEntity.getProductOrderTime().toString());

        historyDetails.setShopName(historyDetailsEntity.getShopName());
        historyDetails.setShopId(historyDetailsEntity.getShopId());


        return historyDetails;
    }
}
