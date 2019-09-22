package merchant.com.bizzybay_merchant.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import merchant.com.bizzybay_merchant.view.fragment.ImageViewPageFragment;

/**
 *
 * @author numan947
 * @since 5/7/17.<br>
 *
 *  Adapter for the {@link android.support.v4.view.ViewPager} of the
 *  {@link merchant.com.bizzybay_merchant.view.fragment.ProductDetailsFragment}
 *
 **/

public class ImageViewPagerAdapter extends FragmentPagerAdapter {

    /**
     * Images to show, must be valid {@link java.net.URL}
     * */
    private ArrayList<String>images;

    public ImageViewPagerAdapter(FragmentManager fm, ArrayList<String>images) {
        super(fm);
        this.images = images;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageViewPageFragment.newInstance(images.get(position));
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
