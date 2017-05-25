package numan947.com.bizzybay.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import numan947.com.bizzybay.view.fragment.CartListFragment;
import numan947.com.bizzybay.view.fragment.HistoryListFragment;
import numan947.com.bizzybay.view.fragment.ProductListFragment;
import numan947.com.bizzybay.view.fragment.ShopListFragment;
import numan947.com.bizzybay.view.fragment.WishListFragment;


/**
 *
 * @author numan947
 * @since 5/9/17.<br>
 *
 * This class provides navigation in the {@link numan947.com.bizzybay.view.activity.MainActivity}
 * It handles the fragment changing operations of the {@link android.support.v4.widget.DrawerLayout}
 **/

public class DrawerNavigator {

    private static final int TRANSITION = FragmentTransaction.TRANSIT_FRAGMENT_OPEN;


    private static DrawerNavigator navigator;

    private static String currentFragment = null;

    public static DrawerNavigator getInstance()
    {
        if(navigator==null)navigator = new DrawerNavigator();
        return navigator;
    }


    private DrawerNavigator(){
    }


    private void setFragmentTransaction(FragmentManager trans)
    {
        trans.beginTransaction().setTransition(TRANSITION).commit();
    }

    /**
     * Navigation to the ProductListFragments
     * */
    public void navigateToProductListFragment(int container, FragmentManager fragmentManager)
    {
        this.setFragmentTransaction(fragmentManager);

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
        this.setFragmentTransaction(fragmentManager);

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
        this.setFragmentTransaction(fragmentManager);

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


    public void navigateToShoppingBagFragment(int main_activity_frame, FragmentManager supportFragmentManager) {

        this.setFragmentTransaction(supportFragmentManager);


        if(currentFragment!=null){
            Fragment f = supportFragmentManager.findFragmentByTag(currentFragment);
            if(f!=null)supportFragmentManager.beginTransaction().hide(f).commit();
        }

        CartListFragment cartListFragment = (CartListFragment) supportFragmentManager.findFragmentByTag(CartListFragment.getFragmentId());

        if(cartListFragment==null){
            cartListFragment = CartListFragment.newInstance();
            supportFragmentManager.beginTransaction().add(main_activity_frame,cartListFragment, CartListFragment.getFragmentId()).commit();
        }
        else
            supportFragmentManager.beginTransaction().show(cartListFragment).commit();

        currentFragment = CartListFragment.getFragmentId();
    }

    public void navigateToWishListFragment(int main_activity_frame, FragmentManager supportFragmentManager) {
        this.setFragmentTransaction(supportFragmentManager);

        if(currentFragment!=null){
            Fragment fr = supportFragmentManager.findFragmentByTag(currentFragment);
            if(fr!=null)supportFragmentManager.beginTransaction().hide(fr).commit();
        }

        WishListFragment wishListFragment = (WishListFragment) supportFragmentManager.findFragmentByTag(WishListFragment.getFragmentId());

        if(wishListFragment==null){
            wishListFragment = WishListFragment.newInstance();
            supportFragmentManager.beginTransaction().add(main_activity_frame,wishListFragment, WishListFragment.getFragmentId()).commit();
        }
        else
            supportFragmentManager.beginTransaction().show(wishListFragment).commit();

        currentFragment = WishListFragment.getFragmentId();
    }
}
