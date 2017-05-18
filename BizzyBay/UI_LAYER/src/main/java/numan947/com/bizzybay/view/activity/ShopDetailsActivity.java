package numan947.com.bizzybay.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import numan947.com.bizzybay.navigation.ActivityNavigator;
import numan947.com.bizzybay.navigation.FragmentNavigator;
import numan947.com.bizzybay.view.fragment.ShopDetailsContactFragment;
import numan947.com.bizzybay.view.fragment.ShopDetailsFragment;

/**
 * @author numan947
 * @since 5/18/17.<br>
 **/

public class ShopDetailsActivity extends BaseActivity implements ShopDetailsFragment.ShopDetailsListener,ShopDetailsContactFragment.ShopDetailsContactFragmentListener {
    private static final String SHOP_ID = "numan947.com.bizzybay.view.activity.ShopDetailsActivity.SHOP_ID";
    private int shopId;

    private FragmentNavigator fragmentNavigator;
    private ActivityNavigator activityNavigator;


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

        fragmentNavigator = FragmentNavigator.getInstance();
        fragmentNavigator.navigateToShopDetailsFragment(getSupportFragmentManager(),android.R.id.content,shopId);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SHOP_ID,shopId);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.shopId = savedInstanceState.getInt(SHOP_ID,-1);
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
    public void onMapClicked(LatLng latLng) {
        //// TODO: 5/18/17
        Toast.makeText(this,"this will open up the map in a new window",Toast.LENGTH_SHORT).show();
    }
}
