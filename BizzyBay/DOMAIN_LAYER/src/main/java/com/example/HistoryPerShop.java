package com.example;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryPerShop {

    private String shopName;

    private ArrayList<HistoryPerProduct> historyPerProductModel;


    public HistoryPerShop(String shopName, ArrayList<HistoryPerProduct> historyPerProductModel) {
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
