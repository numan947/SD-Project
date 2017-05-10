package numan947.com.data_layer.entity;

import com.example.HistoryPerProduct;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryPerShopEntity {

    private String shopName;

    private ArrayList<HistoryPerProduct> historyPerProductModel;


    public HistoryPerShopEntity(String shopName, ArrayList<HistoryPerProduct> historyPerProductModel) {
        this.shopName = shopName;
        this.historyPerProductModel = historyPerProductModel;
    }

    public String getShopName() {
        return shopName;
    }

    public ArrayList<HistoryPerProduct> getHistoryPerProductModel() {
        return historyPerProductModel;
    }
}
