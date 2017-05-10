package com.example;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryPerShop {

    private String shopName;

    private ArrayList<HistoryPerProduct> historyPerProduct;


    public HistoryPerShop(String shopName, ArrayList<HistoryPerProduct> historyPerProduct) {
        this.shopName = shopName;
        this.historyPerProduct = historyPerProduct;
    }

    public String getShopName() {
        return shopName;
    }

    public ArrayList<HistoryPerProduct> getHistoryPerProduct() {
        return historyPerProduct;
    }
}
