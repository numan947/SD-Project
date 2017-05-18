package numan947.com.bizzybay.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import numan947.com.bizzybay.view.fragment.HistoryListFragment;
import numan947.com.bizzybay.view.fragment.ProductListFragment;
import numan947.com.bizzybay.view.fragment.ShopListFragment;


/**
 *
 * @author numan947
 * @since 5/9/17.<br>
 *
 * This class provides navigation in the {@link numan947.com.bizzybay.view.activity.MainActivity}
 * It handles the fragment changing operations of the {@link android.support.v4.widget.DrawerLayout}
 **/

public class DrawerNavigator {



    private static DrawerNavigator navigator;

    private static String currentFragment = null;

    public static DrawerNavigator getInstance()
    {
        if(navigator==null)navigator = new DrawerNavigator();
        return navigator;
    }


    private DrawerNavigator(){
    }

    /**
     * Navigation to the ProductListFragments
     * */
    public void navigateToProductListFragment(int container, FragmentManager fragmentManager)
    {

        if(currentFragment!=null) {
            Fragment f = fragmentManager.findFragmentByTag(currentFragment);

            if(f!=null)
                fragmentManager.beginTransaction().hide(f).commit();
        }


        ProductListFragment fragment;

        fragment = (ProductListFragment) fragmentManager.findFragmentByTag(ProductListFragment.getFragmentID());

        if(fragment==null) {
            fragment = ProductListFragment.newInstance(-1);
            fragmentManager.beginTransaction().add(container, fragment, ProductListFragment.getFragmentID()).commit();
        }
        else
            fragmentManager.beginTransaction().show(fragment).commit();


//        fragmentManager.executePendingTransactions();
        currentFragment = ProductListFragment.getFragmentID();


    }


    public void navigateToHistoryListFragment(int container,FragmentManager fragmentManager) {


        if(currentFragment!=null) {
            Fragment f = fragmentManager.findFragmentByTag(currentFragment);
            if(f!=null)
                fragmentManager.beginTransaction().hide(f).commit();
        }


        HistoryListFragment fragment;

        fragment = (HistoryListFragment) fragmentManager.findFragmentByTag(HistoryListFragment.getFragmentID());

        if(fragment==null) {
            fragment = HistoryListFragment.newInstance();
            fragmentManager.beginTransaction().add(container, fragment, HistoryListFragment.getFragmentID()).commit();
        }
        else
            fragmentManager.beginTransaction().show(fragment).commit();


        //fragmentManager.executePendingTransactions();
        currentFragment = HistoryListFragment.getFragmentID();

    }

    public void navigateToShopListFragment(FragmentManager fragmentManager,int container)
    {
        if(currentFragment!=null){
            Fragment fragment = fragmentManager.findFragmentByTag(currentFragment);
            if(fragment!=null)fragmentManager.beginTransaction().hide(fragment).commit();
        }

        ShopListFragment shopListFragment;

        shopListFragment= (ShopListFragment) fragmentManager.findFragmentByTag(ShopListFragment.getFragmentId());

        if(shopListFragment==null){
            shopListFragment = ShopListFragment.newInstance();
            fragmentManager.beginTransaction().add(container,shopListFragment, ShopListFragment.getFragmentId()).commit();
        }
        else
            fragmentManager.beginTransaction().show(shopListFragment).commit();

        currentFragment = ShopListFragment.getFragmentId();
    }

    public String getCurrentFragment(){
        return currentFragment;
    }
}
