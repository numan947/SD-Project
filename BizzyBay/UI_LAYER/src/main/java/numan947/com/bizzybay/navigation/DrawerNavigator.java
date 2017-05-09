package numan947.com.bizzybay.navigation;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import numan947.com.bizzybay.view.fragment.ProductListFragment;


/**
 *
 * @author numan947
 * @since 5/9/17.<br>
 *
 * This class provides navigation in the {@link numan947.com.bizzybay.view.activity.MainActivity}
 * It handles the fragment changing operations of the {@link android.support.v4.widget.DrawerLayout}
 **/

public class DrawerNavigator {
    private Context context;
    private final String PRODUCT_LIST_FRAGMENT = "numan947.com.bizzybay.navigation.DrawerNavigator.PRODUCT_LIST_FRAGMENT";


    public DrawerNavigator(Context context){
        this.context = context;
    }

    /**
     * Navigation to the ProductListFragments
     * */
    public void navigateToProductListFragment(int container, FragmentManager fragmentManager)
    {
        ProductListFragment fragment;

        fragment = (ProductListFragment) fragmentManager.findFragmentByTag(PRODUCT_LIST_FRAGMENT);

        if(fragment==null)
            fragment = ProductListFragment.newInstance();

        fragmentManager.beginTransaction().replace(container,fragment,PRODUCT_LIST_FRAGMENT).commit();

    }


}
