package merchant.com.bizzybay_merchant.model;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/10/17.<br>
 *
 *
 * This class represents history per shop in the history page
 **/

public class HistoryPerShopModel {

    private String shopName;

    private ArrayList<HistoryPerProductModel> historyPerProductModel;


    public HistoryPerShopModel(String shopName, ArrayList<HistoryPerProductModel> historyPerProductModel) {
        this.shopName = shopName;
        this.historyPerProductModel = historyPerProductModel;
    }

    public String getShopName() {
        return shopName;
    }

    public ArrayList<HistoryPerProductModel> getHistoryPerProductModel() {
        return historyPerProductModel;
    }


}
