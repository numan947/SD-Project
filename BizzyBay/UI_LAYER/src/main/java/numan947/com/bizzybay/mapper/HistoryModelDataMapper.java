package numan947.com.bizzybay.mapper;

import com.example.HistoryList;
import com.example.HistoryPerProduct;
import com.example.HistoryPerShop;

import java.util.ArrayList;

import numan947.com.bizzybay.model.HistoryListModel;
import numan947.com.bizzybay.model.HistoryPerProductModel;
import numan947.com.bizzybay.model.HistoryPerShopModel;

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
}
