package numan947.com.bizzybay.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import numan947.com.bizzybay.navigation.ActivityNavigator;
import numan947.com.bizzybay.navigation.FragmentNavigator;
import numan947.com.bizzybay.view.fragment.ProductDetailsFragment;

/**
 * Created by numan947 on 5/7/17.
 */

public class DetailsProductActivity extends BaseActivity implements ProductDetailsFragment.ProductDetailsListener {
    private static final String PRODUCT_ID="numan947.com.bizzybay.view.activity.DetailsProductActivity.PRODUCT_ID";
    private static final String SHOP_ID="numan947.com.bizzybay.view.activity.DetailsProductActivity.SHOP_ID";
    private final String FRAGMENT= "numan947.com.bizzybay.view.activity.DetailsProductActivity.FRAGMENT_ID";

    private int pid;
    private int sid;

    private ActivityNavigator activityNavigator;
    private FragmentNavigator fragmentNavigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pid = getIntent().getIntExtra(PRODUCT_ID,-1);
        sid = getIntent().getIntExtra(SHOP_ID,-1);

        activityNavigator = new ActivityNavigator(this);
        fragmentNavigator = new FragmentNavigator(this);

        fragmentNavigator.navigateToDetailsFragment(getSupportFragmentManager(),android.R.id.content,pid,sid);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState!=null) {
            outState.putInt(PRODUCT_ID, pid);
            outState.putInt(SHOP_ID, sid);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null) {
            pid = savedInstanceState.getInt(PRODUCT_ID);
            sid = savedInstanceState.getInt(SHOP_ID);
        }
    }



    public static Intent getCallingIntent(Context context, int productId, int shopId)
    {
        Intent callingIntent = new Intent(context,DetailsProductActivity.class);
        callingIntent.putExtra(PRODUCT_ID,productId);
        callingIntent.putExtra(SHOP_ID,shopId);
        return callingIntent;
    }

    @Override
    public void OnCategorySelected(String category) {
        //todo redirect to activity so that it can show search activity with that category search

        Toast.makeText(this,"THIS WILL SHOW search by category activity",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnShopLocationClicked(int shopId) {
        //todo redirect to activity so that it can show shop location activity

        Toast.makeText(this,"THIS WILL SHOW Shop location activity",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnShopNameClicked(int shopId) {

        //todo redirect to activity so that it can show the shopActivity
        Toast.makeText(this,"THIS WILL SHOW SHOP ACTIVITY",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnBuyProduct(int productId, int shopId) {
        //todo redirect to activity so that it can show buy product activity

        Toast.makeText(this,"THIS WILL SHOW buy product ACTIVITY",Toast.LENGTH_SHORT).show();
    }


}
