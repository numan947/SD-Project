package merchant.com.bizzybay_merchant.model;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/10/17.<br>
 *
 * This class represents history per day in the history page.
 **/

public class HistoryListModel {

    private String Date;
    private ArrayList<HistoryPerShopModel> historyPerShopModel;

    public HistoryListModel(String date, ArrayList<HistoryPerShopModel> historyPerShopModel) {
        Date = date;
        this.historyPerShopModel = historyPerShopModel;
    }

    public String getDate() {
        return Date;
    }

    public ArrayList<HistoryPerShopModel> getHistoryPerShopModel() {
        return historyPerShopModel;
    }
}
