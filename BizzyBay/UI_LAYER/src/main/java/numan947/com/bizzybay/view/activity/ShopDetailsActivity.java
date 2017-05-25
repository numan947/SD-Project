package numan947.com.bizzybay.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.ShopDetailsModelForMap;
import numan947.com.bizzybay.navigation.ActivityNavigator;
import numan947.com.bizzybay.navigation.ShopDetailsFragmentNavigator;
import numan947.com.bizzybay.view.fragment.ProductListFragment;
import numan947.com.bizzybay.view.fragment.ShopDetailsContactFragment;
import numan947.com.bizzybay.view.fragment.ShopDetailsFragment;

/**
 * @author numan947
 * @since 5/18/17.<br>
 **/

public class ShopDetailsActivity extends BaseActivity implements ShopDetailsFragment.ShopDetailsListener,
        ShopDetailsContactFragment.ShopDetailsContactFragmentListener,
        ProductListFragment.ProductListListener{
    private static final String SHOP_ID = "numan947.com.bizzybay.view.activity.ShopDetailsActivity.SHOP_ID";
    //private static final String CURRENT_FRAGMENT="numan947.com.bizzybay.view.activity.ShopDetailsActivity.CURRENT_FRAGMENT";
    private static final String SHOP_DETAILS_MODEL_FOR_MAP = "numan947.com.bizzybay.view.activity.ShopDetailsActivity.SHOP_DETAILS_MODEL_FOR_MAP";

    private int shopId;

    private ShopDetailsFragmentNavigator fragmentNavigator;
    private ActivityNavigator activityNavigator;


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
        setContentView(R.layout.shop_details_activity);


        activityNavigator = new ActivityNavigator(this);

        fragmentNavigator = ShopDetailsFragmentNavigator.getInstance();

        this.getParameters();

        if(fragmentNavigator.getCurrentFragment()==null||!fragmentNavigator.currentFragmentNotNull(getSupportFragmentManager())){
            fragmentNavigator.navigateToShopDetailsFragment(getSupportFragmentManager(),R.id.shop_details_activity_frame,shopId);
        }
        else
            fragmentNavigator.navigateToCurrentFragment(getSupportFragmentManager());

    }


    private void getParameters() {
        this.shopId = getIntent().getIntExtra(SHOP_ID,-1);
    }

    @Override
    public void onBackPressed() {
        if (fragmentNavigator.getCurrentFragment().equals(ShopDetailsFragment.getFragmentId())) {
            super.onBackPressed();
        } else {
            this.fragmentNavigator.navigateToShopDetailsFragment(getSupportFragmentManager(),R.id.shop_details_activity_frame,shopId);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SHOP_ID,shopId);
        outState.putParcelable(SHOP_DETAILS_MODEL_FOR_MAP,shopDetailsModelForMap);

        //System.err.println("WHY WHY1 "+ shopId+"   "+currentFragment+"   "+shopDetailsModelForMap);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null) {
            this.shopId = savedInstanceState.getInt(SHOP_ID, -1);
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
        this.shopId = shopId;
        fragmentNavigator.navigateToShopProductFragment(getSupportFragmentManager(),R.id.shop_details_activity_frame,shopId);
    }


    @Override
    public void onWhatsAppButtonClicked(String shopNumber) {

        //assuming the number has '+' at the beginning

        //ALL HAIL STACK OVERFLOW B)

        String smsNumber;
        if(shopNumber.charAt(0)=='+')
            smsNumber = shopNumber.substring(1);
        else
            smsNumber = shopNumber; //without '+'


        try {
            Intent sendIntent = new Intent("android.intent.action.MAIN");
            //sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello!");
            sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } catch(Exception e) {
            Toast.makeText(this, "Error in whatsapp\nContact Customer Service\n" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFacebookButtonClicked(String faceBookPage) {

        Intent intent;
        try {
            this.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/"+faceBookPage));
        } catch (Exception e) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"+faceBookPage));
        }
        startActivity(intent);
    }

    @Override
    public void onMapClicked(ShopDetailsModelForMap modelForMap) {

        this.shopDetailsModelForMap = modelForMap;

        fragmentNavigator.navigateToShopGenericMapView(getSupportFragmentManager(),R.id.shop_details_activity_frame,
                modelForMap);

    }

    @Override
    public void onProductClicked(int productId,int shopId,String fragmentId) {
        activityNavigator.navigateToDetailsProductActivity(productId,shopId);
    }

    @Override
    public void onHomeButtonPressed() {
        this.onBackPressed();
    }
}
