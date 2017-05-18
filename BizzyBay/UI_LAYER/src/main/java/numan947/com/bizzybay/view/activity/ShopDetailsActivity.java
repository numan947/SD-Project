package numan947.com.bizzybay.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import numan947.com.bizzybay.model.ShopDetailsModelForMap;
import numan947.com.bizzybay.navigation.ActivityNavigator;
import numan947.com.bizzybay.navigation.ShopDetailsFragmentNavigator;
import numan947.com.bizzybay.view.fragment.ShopDetailsContactFragment;
import numan947.com.bizzybay.view.fragment.ShopDetailsFragment;

/**
 * @author numan947
 * @since 5/18/17.<br>
 **/

public class ShopDetailsActivity extends BaseActivity implements ShopDetailsFragment.ShopDetailsListener,ShopDetailsContactFragment.ShopDetailsContactFragmentListener {
    private static final String SHOP_ID = "numan947.com.bizzybay.view.activity.ShopDetailsActivity.SHOP_ID";
    private static final String CURRENT_FRAGMENT="numan947.com.bizzybay.view.activity.ShopDetailsActivity.CURRENT_FRAGMENT";
    private static final String SHOP_DETAILS_MODEL_FOR_MAP = "numan947.com.bizzybay.view.activity.ShopDetailsActivity.SHOP_DETAILS_MODEL_FOR_MAP";

    private int shopId;

    private ShopDetailsFragmentNavigator fragmentNavigator;
    private ActivityNavigator activityNavigator;

    private int currentFragment=-1;
    private ShopDetailsModelForMap shopDetailsModelForMap;


    public static Intent getCallingIntent(Context context,int shopId)
    {
        Intent intent = new Intent(context, ShopDetailsActivity.class);
        intent.putExtra(SHOP_ID,shopId);

        return intent;
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        activityNavigator = new ActivityNavigator(this);

        fragmentNavigator = ShopDetailsFragmentNavigator.getInstance();

        this.getParameters();

        if(currentFragment==-1) {
            fragmentNavigator.navigateToShopDetailsFragment(getSupportFragmentManager(), android.R.id.content, shopId);
            currentFragment = 0;
            System.err.println("NOW I SEE YOU");
        }
        else{
            this.provideNavigation(currentFragment);
        }
    }

    private void provideNavigation(int currentFragment) {
        switch (currentFragment){
            case 0:
                fragmentNavigator.navigateToShopDetailsFragment(getSupportFragmentManager(), android.R.id.content,
                        shopId);
                break;
            case 1:
                fragmentNavigator.navigateToShopGenericMapView(getSupportFragmentManager(),android.R.id.content,
                        shopDetailsModelForMap);
                break;
            //todo add others
        }
    }

    private void getParameters() {
        this.shopId = getIntent().getIntExtra(SHOP_ID,-1);
    }

    @Override
    public void onBackPressed() {
        if (currentFragment==0) {
            super.onBackPressed();
        } else {

            this.fragmentNavigator.navigateToShopDetailsFragment(getSupportFragmentManager(),android.R.id.content,shopId);
            currentFragment = 0;
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SHOP_ID,shopId);
        outState.putInt(CURRENT_FRAGMENT,currentFragment);
        outState.putParcelable(SHOP_DETAILS_MODEL_FOR_MAP,shopDetailsModelForMap);

        //System.err.println("WHY WHY1 "+ shopId+"   "+currentFragment+"   "+shopDetailsModelForMap);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null) {
            this.shopId = savedInstanceState.getInt(SHOP_ID, -1);
            this.currentFragment = savedInstanceState.getInt(CURRENT_FRAGMENT, -1);
            this.shopDetailsModelForMap = savedInstanceState.getParcelable(SHOP_DETAILS_MODEL_FOR_MAP);
        }

        //System.err.println("WHY WHY2 "+ shopId+"   "+currentFragment+"   "+shopDetailsModelForMap);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void showShopProducts(int shopId) {
        //// TODO: 5/18/17

        Toast.makeText(this,"This will show the products",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onWhatsAppButtonClicked() {
        //// TODO: 5/18/17
        Toast.makeText(this,"this will open up whatsapp",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFacebookButtonClicked() {
    //// TODO: 5/18/17
        Toast.makeText(this,"this will open up facebook",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapClicked(ShopDetailsModelForMap modelForMap) {
        currentFragment =1;
        this.shopDetailsModelForMap = modelForMap;

        fragmentNavigator.navigateToShopGenericMapView(getSupportFragmentManager(),android.R.id.content,
                modelForMap);

    }
}
