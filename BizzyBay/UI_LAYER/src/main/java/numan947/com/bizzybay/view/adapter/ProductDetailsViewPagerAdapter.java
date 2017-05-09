package numan947.com.bizzybay.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import numan947.com.bizzybay.view.fragment.ProductDetailsViewPagerFragment;

/**
 *
 * @author numan947
 * @since 5/7/17.<br>
 *
 *  Adapter for the {@link android.support.v4.view.ViewPager} of the
 *  {@link numan947.com.bizzybay.view.fragment.ProductDetailsFragment}
 *
 **/

public class ProductDetailsViewPagerAdapter extends FragmentPagerAdapter {

    /**
     * Images to show, must be valid {@link java.net.URL}
     * */
    private ArrayList<String>images;

    public ProductDetailsViewPagerAdapter(FragmentManager fm, ArrayList<String>images) {
        super(fm);
        this.images = images;
    }

    @Override
    public Fragment getItem(int position) {
        return ProductDetailsViewPagerFragment.newInstance(images.get(position));
    }

    @Override
    public int getCount() {
        return images.size();
    }

    /**
     * Adds items to the viewPager.
     * */
    public void addAll(ArrayList<String>items){
        this.images.addAll(items);
    }

    /**
     * Clears the adapter.
     * */
    public void clear()
    {
        this.images.clear();
    }

    public ArrayList<String> getImages() {
        return images;
    }
}
