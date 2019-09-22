package merchant.com.bizzybay_merchant.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import merchant.com.bizzybay_merchant.navigation.FragmentNavigator;
import merchant.com.bizzybay_merchant.view.fragment.HistoryDetailsFragment;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryDetailsActivity extends BaseActivity implements HistoryDetailsFragment.HistoryDetailsListener {

    private static final String ORDER_ID = "numan947.com.bizzybay.view.activity.ORDER_ID";
    private static final String PRODUCT_ID = "numan947.com.bizzybay.view.activity.PRODUCT_ID";
    private static final String SHOP_ID = "numan947.com.bizzybay.view.activity.SHOP_ID";

    private int productId,shopId,orderId;


    public static Intent getCallingIntent(Context context, int orderId, int shopId, int productID)
    {
        Intent callingIntent = new Intent(context, HistoryDetailsActivity.class);

        callingIntent.putExtra(ORDER_ID,orderId);
        callingIntent.putExtra(PRODUCT_ID,productID);
        callingIntent.putExtra(SHOP_ID,shopId);
        return callingIntent;
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getParameters();

        FragmentNavigator fragmentNavigator = FragmentNavigator.getInstance();
        fragmentNavigator.navigateToHistoryDetailsFragment(getSupportFragmentManager(),android.R.id.content,shopId,orderId,productId);

    }

    private void getParameters() {
        Intent extra =this.getIntent();

        this.productId = extra.getIntExtra(PRODUCT_ID,-1);
        this.shopId = extra.getIntExtra(SHOP_ID,-1);
        this.orderId = extra.getIntExtra(ORDER_ID,-1);
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
        //todo ??
    }

    @Override
    public void onShopNameClicked(int shopId) {
        //todo ??
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
