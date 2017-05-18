package numan947.com.bizzybay.navigation;

import android.support.v4.app.FragmentManager;

import numan947.com.bizzybay.view.fragment.HistoryDetailsFragment;
import numan947.com.bizzybay.view.fragment.ProductDetailsFragment;
import numan947.com.bizzybay.view.fragment.ShopDetailsFragment;

/**
 *
 * @author numan947
 * @since 5/9/17.<br>
 *
 * Provides navigation for fragments not in the {@link android.support.v4.widget.DrawerLayout}
 **/

public class FragmentNavigator {

    //singleton
    private static FragmentNavigator navigator;

    public static FragmentNavigator getInstance()
    {
        if(navigator==null)navigator = new FragmentNavigator();
        return navigator;
    }

    private FragmentNavigator() {

    }

    /**
     * Navigation to the {@link ProductDetailsFragment} which is inside the {@link numan947.com.bizzybay.view.activity.DetailsProductActivity}
     * */
    public void navigateToProductDetailsFragment(FragmentManager fragmentManager, int container, int productId, int shopId) {

        ProductDetailsFragment fragment = (ProductDetailsFragment) fragmentManager.findFragmentByTag(ProductDetailsFragment.getFragmentID());


        if (fragment == null) {
            fragment = ProductDetailsFragment.newInstance(productId, shopId);
            fragmentManager.beginTransaction().add(container, fragment, ProductDetailsFragment.getFragmentID()).commit();
        }
    }

    public void navigateToHistoryDetailsFragment(FragmentManager fragmentManager,int container,int shopId,int orderId, int productId)
    {
        HistoryDetailsFragment fragment = (HistoryDetailsFragment) fragmentManager.findFragmentByTag(HistoryDetailsFragment.getFragmentID());
        if(fragment==null){
            fragment = HistoryDetailsFragment.newInstance(orderId,shopId,productId);
            fragmentManager.beginTransaction().add(container,fragment,HistoryDetailsFragment.getFragmentID()).commit();
        }

    }


    public void navigateToShopDetailsFragment(FragmentManager fragmentManager,int container,int shopId) {
        ShopDetailsFragment shopDetailsFragment = (ShopDetailsFragment) fragmentManager.findFragmentByTag(ShopDetailsFragment.getFragmentId());

        if(shopDetailsFragment==null){
            shopDetailsFragment = ShopDetailsFragment.newInstance(shopId);
            fragmentManager.beginTransaction().add(container,shopDetailsFragment,ShopDetailsFragment.getFragmentId()).commit();
        }


    }
}
