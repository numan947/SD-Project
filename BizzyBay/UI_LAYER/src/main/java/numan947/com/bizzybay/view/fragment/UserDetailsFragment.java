package numan947.com.bizzybay.view.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;

/**
 * @author numan947
 * @since 7/6/17.<br>
 **/

public class UserDetailsFragment extends BaseFragment {
    private static final String fragmentId = "numan947.com.bizzybay.view.fragment.UserDetailsFragment.USER_DETAILS_FRAGMENT";
    private static final String USER_ID= "numan947.com.bizzybay.view.fragment.UserDetailsFragment.USER_ID";


    public static String getFragmentId(){return fragmentId;}


    public static UserDetailsFragment newInstance(int userId)
    {
        Bundle bundle = new Bundle();
        bundle.putInt(USER_ID,userId);

        UserDetailsFragment userDetailsFragment = new UserDetailsFragment();
        userDetailsFragment.setArguments(bundle);
        return userDetailsFragment;
    }

    public interface UserDetailsListener{
        void finishActivity();
        //todo add the things that can be edited and setup requests accordingly
    }

    private AppBarLayout appBarLayout;
    //todo



    @Override
    protected void initializePresenter() {

    }
}
