package numan947.com.bizzybay.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import numan947.com.bizzybay.model.ShopDetailsModelForMap;
import numan947.com.bizzybay.view.fragment.GenericMapFragment;
import numan947.com.bizzybay.view.fragment.ProductListFragment;
import numan947.com.bizzybay.view.fragment.ShopDetailsFragment;

/**
 * @author numan947
 * @since 5/18/17.<br>
 **/

public class ShopDetailsFragmentNavigator {

    private String currentFragment;

    private static ShopDetailsFragmentNavigator navigator;
    public static ShopDetailsFragmentNavigator getInstance()
    {
        if(navigator==null)navigator=new ShopDetailsFragmentNavigator();
        return navigator;
    }
    private ShopDetailsFragmentNavigator() {
        currentFragment=null;
    }

    public void navigateToShopGenericMapView(FragmentManager fragmentManager,int container, ShopDetailsModelForMap model) {
        if (currentFragment != null) {
            Fragment fragment = fragmentManager.findFragmentByTag(currentFragment);
            if(fragment!=null)fragmentManager.beginTransaction().hide(fragment).commit();
        }


        GenericMapFragment fragment = (GenericMapFragment) fragmentManager.findFragmentByTag(GenericMapFragment.getFragmentId());

        if (fragment == null) {
            fragment = GenericMapFragment.newInstance(model);
            fragmentManager.beginTransaction().add(container,fragment,GenericMapFragment.getFragmentId()).commit();
        }
        else
            fragmentManager.beginTransaction().show(fragment).commit();

        currentFragment = GenericMapFragment.getFragmentId();
    }


    public void navigateToShopDetailsFragment(FragmentManager fragmentManager, int container, int shopId) {

        if(currentFragment!=null){
            Fragment fragment = fragmentManager.findFragmentByTag(currentFragment);
            if(fragment!=null)
                fragmentManager.beginTransaction().hide(fragment).commit();
        }


        ShopDetailsFragment shopDetailsFragment = (ShopDetailsFragment) fragmentManager.findFragmentByTag(ShopDetailsFragment.getFragmentId());

        if(shopDetailsFragment==null) {
            shopDetailsFragment = ShopDetailsFragment.newInstance(shopId);
            fragmentManager.beginTransaction().add(container, shopDetailsFragment, ShopDetailsFragment.getFragmentId()).commit();
        }
        else
            fragmentManager.beginTransaction().show(shopDetailsFragment).commit();


        currentFragment = ShopDetailsFragment.getFragmentId();
    }


    public String getCurrentFragment()
    {
        return currentFragment;
    }


    public void navigateToCurrentFragment(FragmentManager fragmentManager) {
        Fragment fragment = fragmentManager.findFragmentByTag(currentFragment);
        fragmentManager.beginTransaction().show(fragment).commit();
    }

    public boolean currentFragmentNotNull(FragmentManager supportFragmentManager) {
        return supportFragmentManager.findFragmentByTag(currentFragment)!=null;
    }

    public void navigateToShopProductFragment(FragmentManager fragmentManager,int container,int shopId) {
        if(currentFragment!=null){
            Fragment fr = fragmentManager.findFragmentByTag(currentFragment);
            if(fr!=null)fragmentManager.beginTransaction().hide(fr).commit();
        }

        ProductListFragment fragment = (ProductListFragment) fragmentManager.findFragmentByTag(ProductListFragment.getFragmentID());

        if(fragment==null){
            fragment = ProductListFragment.newInstance(shopId);
            fragmentManager.beginTransaction().add(container,fragment, ProductListFragment.getFragmentID()).commit();
        }
        else
            fragmentManager.beginTransaction().show(fragment).commit();

        currentFragment = ProductListFragment.getFragmentID();

    }
}
