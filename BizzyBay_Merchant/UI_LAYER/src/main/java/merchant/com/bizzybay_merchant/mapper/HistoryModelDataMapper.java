package merchant.com.bizzybay_merchant.mapper;

import com.merchant_example.HistoryDetails;
import com.merchant_example.HistoryList;
import com.merchant_example.HistoryPerProduct;
import com.merchant_example.HistoryPerShop;

import java.util.ArrayList;

import merchant.com.bizzybay_merchant.model.HistoryDetailsModel;
import merchant.com.bizzybay_merchant.model.HistoryListModel;
import merchant.com.bizzybay_merchant.model.HistoryPerProductModel;
import merchant.com.bizzybay_merchant.model.HistoryPerShopModel;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryModelDataMapper {
    public ArrayList<HistoryListModel> transform(ArrayList<HistoryList> historyList1) {

        ArrayList<HistoryList>historyList = new ArrayList<>(historyList1);

        ArrayList<HistoryListModel>model= new ArrayList<>();

        for(HistoryList single: historyList){

            ArrayList<HistoryPerShop>historyPerShop = single.getHistoryPerShop();

            ArrayList<HistoryPerShopModel> historyPerShopModel = new ArrayList<>();

            for(HistoryPerShop single2: historyPerShop){
                ArrayList<HistoryPerProduct>historyPerProduct = single2.getHistoryPerProduct();

                ArrayList<HistoryPerProductModel> historyPerProductModel = new ArrayList<>();

                for( HistoryPerProduct single3 : historyPerProduct){
                    //todo do some awesome styling and all those shit here -_-
                    HistoryPerProductModel model2 = new HistoryPerProductModel(single3.getProductId(),
                            single3.getShopId(),single3.getOrderId(),single3.getProductName(),single3.getProductQuantity(),
                            single3.getProductStatus(),single3.getProductPrice(),single3.getProductImage());

                    historyPerProductModel.add(model2);
                }

                HistoryPerShopModel model3 = new HistoryPerShopModel(single2.getShopName(),historyPerProductModel);
                historyPerShopModel.add(model3);
            }

            HistoryListModel model4 = new HistoryListModel(single.getDate(),historyPerShopModel);

            model.add(model4);
        }

        return model;
    }

    public HistoryDetailsModel transform(HistoryDetails historyDetails) {
        //todo do any necessary transformation here

        return  new HistoryDetailsModel(historyDetails.getProductName(),"Order ID "+historyDetails.getProductOrderId(),"ShopName "+historyDetails.getShopName(),"QTY "+historyDetails.getProductQuantity(),
                "Price " +historyDetails.getProductPrice(),"Order time " +historyDetails.getProductOrderTime(),"Delivery Time "+historyDetails.getProductDeliveryTime(),
                historyDetails.getProductDeliveryLocation(),historyDetails.getPaymentMethod(),historyDetails.getPaymentId(),historyDetails.getProductDetails(),historyDetails.getProductImage(),historyDetails.getProductId(),
                historyDetails.getShopId());

    }
}
