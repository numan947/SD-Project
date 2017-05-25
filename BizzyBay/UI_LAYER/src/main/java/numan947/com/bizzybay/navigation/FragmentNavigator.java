package numan947.com.bizzybay.navigation;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import numan947.com.bizzybay.view.fragment.HistoryDetailsFragment;
import numan947.com.bizzybay.view.fragment.ProductDetailsFragment;

/**
 *
 * @author numan947
 * @since 5/9/17.<br>
 *
 * Provides navigation for fragments not in the {@link android.support.v4.widget.DrawerLayout}
 **/

public class FragmentNavigator {

    private static final int TRANSITION = FragmentTransaction.TRANSIT_FRAGMENT_FADE;

    //singleton
    private static FragmentNavigator navigator;

    public static FragmentNavigator getInstance()
    {
        if(navigator==null)navigator = new FragmentNavigator();
        return navigator;
    }

    private FragmentNavigator() {

    }

    private void setupTransition(FragmentManager fragmentManager)
    {
        fragmentManager.beginTransaction().setTransition(TRANSITION).commit();
    }

    /**
     * Navigation to the {@link ProductDetailsFragment} which is inside the {@link numan947.com.bizzybay.view.activity.DetailsProductActivity}
     * */
    public void navigateToProductDetailsFragment(FragmentManager fragmentManager, int container, int productId, int shopId) {

        this.setupTransition(fragmentManager);

        ProductDetailsFragment fragment = (ProductDetailsFragment) fragmentManager.findFragmentByTag(ProductDetailsFragment.getFragmentID());


        if (fragment == null) {
            fragment = ProductDetailsFragment.newInstance(productId, shopId);
            fragmentManager.beginTransaction().add(container, fragment, ProductDetailsFragment.getFragmentID()).commit();
        }
    }

    public void navigateToHistoryDetailsFragment(FragmentManager fragmentManager,int container,int shopId,int orderId, int productId)
    {
        this.setupTransition(fragmentManager);

        HistoryDetailsFragment fragment = (HistoryDetailsFragment) fragmentManager.findFragmentByTag(HistoryDetailsFragment.getFragmentID());
        if(fragment==null){
            fragment = HistoryDetailsFragment.newInstance(orderId,shopId,productId);
            fragmentManager.beginTransaction().add(container,fragment,HistoryDetailsFragment.getFragmentID()).commit();
        }

    }
}
