package numan947.com.data_layer.entity;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryListEntity {

    private String Date;
    private ArrayList<HistoryPerShopEntity> historyPerShopEntities;


    public HistoryListEntity(String date, ArrayList<HistoryPerShopEntity> historyPerShopEntities) {
        Date = date;
        this.historyPerShopEntities = historyPerShopEntities;
    }

    public String getDate() {
        return Date;
    }

    public ArrayList<HistoryPerShopEntity> getHistoryPerShopEntities() {
        return historyPerShopEntities;
    }
}
