package numan947.com.data_layer.entity;

import com.example.HistoryPerShop;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryListEntity {

    private String Date;
    private ArrayList<HistoryPerShop> historyPerShopModel;


    public HistoryListEntity(String date, ArrayList<HistoryPerShop> historyPerShopModel) {
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
