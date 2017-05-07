package numan947.com.bizzybay.navigation;



//class for Navigating to different parts of the app

import android.content.Context;
import android.support.v4.app.FragmentManager;

import numan947.com.bizzybay.view.fragment.ProductListFragment;

public class DrawerNavigator {
    private Context context;

    public DrawerNavigator(Context context){
        this.context = context;
    }

    public void navigateToProductListFragment(int container, FragmentManager fragmentManager)
    {
        ProductListFragment fragment = ProductListFragment.newInstance();

        fragmentManager.beginTransaction().replace(container,fragment).commit();

    }






}
