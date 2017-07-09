package merchant.com.bizzybay_merchant.view;

import java.util.ArrayList;

import merchant.com.bizzybay_merchant.model.HistoryListModel;

/**
 * @author numan947
 * @since 5/10/17.<br>
 *
 * Interface for rendering history list to the view.
 * This is held & used by the associated presenter.
 **/

public interface HistoryListView extends DataToLoad {

    /**
     * This method render a list of history to the view.
     * */
    void renderHistoryList(int pageNumber, ArrayList<HistoryListModel>historyList);

    /**
     * This method is called by the view when a single history product is clicked.
     * This should open up a new activity to show the selected history.
     * */
    void viewProductHistory(int orderId,int shopId,int productId);


    /**
     *
     * Hides the Recycler view
     * */
    void hideList();

    /**
     * Shows the recycler view
     * */
    void showList();

}
