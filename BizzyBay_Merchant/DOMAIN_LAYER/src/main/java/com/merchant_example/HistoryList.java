package com.merchant_example;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryList {

    private String Date;
    private ArrayList<HistoryPerShop> historyPerShop;

    public HistoryList(String date, ArrayList<HistoryPerShop> historyPerShop) {
        Date = date;
        this.historyPerShop = historyPerShop;
    }

    public String getDate() {
        return Date;
    }

    public ArrayList<HistoryPerShop> getHistoryPerShop() {
        return historyPerShop;
    }
}
