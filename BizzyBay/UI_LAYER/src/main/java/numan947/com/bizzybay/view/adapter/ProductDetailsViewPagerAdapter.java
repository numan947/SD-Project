package numan947.com.bizzybay.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import numan947.com.bizzybay.view.fragment.ProductDetailsViewPagerFragment;

/**
 * Created by numan947 on 5/7/17.
 */

public class ProductDetailsViewPagerAdapter extends FragmentPagerAdapter {

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

    public void addAll(ArrayList<String>items){
        this.images.addAll(items);
    }
    public void clear()
    {
        this.images.clear();
    }
}
