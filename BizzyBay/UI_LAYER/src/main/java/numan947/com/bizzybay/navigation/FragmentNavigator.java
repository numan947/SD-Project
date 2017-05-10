package numan947.com.bizzybay.navigation;

import android.support.v4.app.FragmentManager;

import numan947.com.bizzybay.view.fragment.ProductDetailsFragment;

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
    public void navigateToDetailsFragment(FragmentManager fragmentManager,int container,int productId,int shopId) {

        ProductDetailsFragment fragment = (ProductDetailsFragment) fragmentManager.findFragmentByTag(ProductDetailsFragment.getFragmentID());


        if (fragment == null) {
            fragment = ProductDetailsFragment.newInstance(productId, shopId);
            fragmentManager.beginTransaction().add(container, fragment, ProductDetailsFragment.getFragmentID()).commit();
        }
    }



}
