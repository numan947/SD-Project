package numan947.com.bizzybay.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import numan947.com.bizzybay.navigation.FragmentNavigator;
import numan947.com.bizzybay.view.fragment.UserDetailsFragment;

/**
 * @author numan947
 * @since 7/7/17.<br>
 **/

public class UserDetailsActivity extends BaseActivity implements UserDetailsFragment.UserDetailsListener {

    private static final String USERID= "numan947.com.bizzybay.view.activity.UserDetailsActivity.USER_ID";

    private int userId;


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

        FragmentNavigator fragmentNavigator = FragmentNavigator.getInstance();
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
}
