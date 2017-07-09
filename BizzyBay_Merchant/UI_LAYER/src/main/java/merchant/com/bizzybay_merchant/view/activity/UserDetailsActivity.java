package merchant.com.bizzybay_merchant.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import merchant.com.bizzybay_merchant.navigation.FragmentNavigator;
import merchant.com.bizzybay_merchant.view.fragment.UserDetailsFragment;

/**
 * @author numan947
 * @since 7/7/17.<br>
 **/

public class UserDetailsActivity extends BaseActivity implements UserDetailsFragment.UserDetailsListener {

    private static final String USERID= "numan947.com.bizzybay.view.activity.UserDetailsActivity.USER_ID";

    private int userId;

    private FragmentNavigator fragmentNavigator;

    public static Intent getCallingIntent(Context context,int userId)
    {
        Intent callingIntent = new Intent(context,UserDetailsActivity.class);

        callingIntent.putExtra(USERID,userId);

        return callingIntent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getParameters();

        fragmentNavigator = FragmentNavigator.getInstance();
        fragmentNavigator.navigateToUserDetailsFragment(getSupportFragmentManager(),android.R.id.content,userId);

    }

    private void getParameters() {
        Intent i = this.getIntent();
        this.userId = i.getIntExtra(USERID,-1);
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
    public void finishActivity() {
        finish();
    }

    @Override
    public void navigateToShoppingBag() {
        //// TODO: 7/8/17  

    }

    @Override
    public void navigateToPaymentHistory() {
        // TODO: 7/8/17  
    }

    @Override
    public void navigateToLikedItems() {
        // TODO: 7/8/17
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
