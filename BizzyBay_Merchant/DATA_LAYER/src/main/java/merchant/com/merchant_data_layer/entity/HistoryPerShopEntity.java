package merchant.com.merchant_data_layer.entity;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryPerShopEntity {

    private String shopName;

    private ArrayList<HistoryPerProductEntity> historyPerProductEntities;


    public HistoryPerShopEntity(String shopName, ArrayList<HistoryPerProductEntity> historyPerProductEntities) {
        this.shopName = shopName;
        this.historyPerProductEntities = historyPerProductEntities;
    }

    public String getShopName() {
        return shopName;
    }

    public ArrayList<HistoryPerProductEntity> getHistoryPerProductEntities() {
        return historyPerProductEntities;
    }
}
