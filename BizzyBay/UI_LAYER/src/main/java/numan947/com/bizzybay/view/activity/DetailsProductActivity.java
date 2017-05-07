package numan947.com.bizzybay.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import numan947.com.bizzybay.model.DetailsProductModel;
import numan947.com.bizzybay.navigation.ActivityNavigator;
import numan947.com.bizzybay.view.fragment.ProductDetailsFragment;

/**
 * Created by numan947 on 5/7/17.
 */

public class DetailsProductActivity extends BaseActivity implements ProductDetailsFragment.ProductDetailsListener {
    private static final String PRODUCT_ID="numan947.com.bizzybay.view.activity.DetailsProductActivity.PRODUCT_ID";
    private static final String SHOP_ID="numan947.com.bizzybay.view.activity.DetailsProductActivity.SHOP_ID";



    private ActivityNavigator navigator;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int pid = getIntent().getIntExtra(PRODUCT_ID,-1);
        int sid = getIntent().getIntExtra(SHOP_ID,-1);
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, ProductDetailsFragment.newInstance(pid,sid));


        navigator = new ActivityNavigator(this);
    }



    public static Intent getCallingIntent(Context context,int productId, int shopId)
    {
        Intent callingIntent = new Intent(context,DetailsProductActivity.class);
        callingIntent.putExtra(PRODUCT_ID,productId);
        callingIntent.putExtra(SHOP_ID,shopId);
        return callingIntent;
    }

    @Override
    public void OnCategorySelected(String category) {
        //todo
    }

    @Override
    public void OnShopLocationClicked(DetailsProductModel model) {
        //todo
    }

    @Override
    public void OnShopNameClicked(DetailsProductModel model) {
        //todo
    }
}
