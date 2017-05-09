package numan947.com.bizzybay.navigation;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import numan947.com.bizzybay.view.fragment.ProductDetailsFragment;

/**
 * Created by numan947 on 5/9/17.
 */

public class FragmentNavigator {
    private Context context;

    private final String DETAILS_FRAGMENT = "numan947.com.bizzybay.navigation.FragmentNavigator.DETAILS_FRAGMENT";


    public FragmentNavigator(Context context) {
        this.context = context;
    }

    public void navigateToDetailsFragment(FragmentManager fragmentManager,int container,int productId,int shopId)
    {
        ProductDetailsFragment fragment = (ProductDetailsFragment) fragmentManager.findFragmentByTag(DETAILS_FRAGMENT);
        if(fragment==null)
            fragment = ProductDetailsFragment.newInstance(productId,shopId);

        fragmentManager.beginTransaction().replace(container,fragment,DETAILS_FRAGMENT).commit();
    }




}
