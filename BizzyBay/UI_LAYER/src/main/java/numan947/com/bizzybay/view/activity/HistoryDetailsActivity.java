package numan947.com.bizzybay.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import numan947.com.bizzybay.view.fragment.HistoryDetailsFragment;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryDetailsActivity extends BaseActivity implements HistoryDetailsFragment.HistoryDetailsListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onProductNameClicked(int productId, int shopId) {

    }

    @Override
    public void onShopNameClicked(int shopId) {

    }
}
