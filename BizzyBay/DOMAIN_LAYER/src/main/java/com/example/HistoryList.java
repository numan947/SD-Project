package com.example;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryList {

    private String Date;
    private ArrayList<HistoryPerShop> historyPerShopModel;

    public HistoryList(String date, ArrayList<HistoryPerShop> historyPerShopModel) {
        Date = date;
        this.historyPerShopModel = historyPerShopModel;
    }

    public String getDate() {
        return Date;
    }

    public ArrayList<HistoryPerShop> getHistoryPerShopModel() {
        return historyPerShopModel;
    }
}
