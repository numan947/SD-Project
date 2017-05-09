package numan947.com.bizzybay.navigation;

import android.content.Context;
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
    private Context context;

    private final String DETAILS_FRAGMENT = "numan947.com.bizzybay.navigation.FragmentNavigator.DETAILS_FRAGMENT";


    public FragmentNavigator(Context context) {
        this.context = context;
    }

    /**
     * Navigation to the {@link ProductDetailsFragment} which is inside the {@link numan947.com.bizzybay.view.activity.DetailsProductActivity}
     * */
    public void navigateToDetailsFragment(FragmentManager fragmentManager,int container,int productId,int shopId)
    {
        ProductDetailsFragment fragment = (ProductDetailsFragment) fragmentManager.findFragmentByTag(DETAILS_FRAGMENT);
        if(fragment==null)
            fragment = ProductDetailsFragment.newInstance(productId,shopId);

        fragmentManager.beginTransaction().replace(container,fragment,DETAILS_FRAGMENT).commit();
    }




}
