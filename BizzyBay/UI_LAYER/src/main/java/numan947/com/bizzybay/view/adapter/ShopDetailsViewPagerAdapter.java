package numan947.com.bizzybay.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import numan947.com.bizzybay.model.ShopDetailsModel;
import numan947.com.bizzybay.view.fragment.ShopDetailsAboutUsFragment;
import numan947.com.bizzybay.view.fragment.ShopDetailsContactFragment;

/**
 * @author numan947
 * @since 5/18/17.<br>
 **/

public class ShopDetailsViewPagerAdapter extends FragmentPagerAdapter {
    private ShopDetailsModel shopDetailsModel;

    private ShopDetailsContactFragment contactFragment;
    private ShopDetailsAboutUsFragment aboutUsFragment;


    public ShopDetailsViewPagerAdapter(FragmentManager fm,ShopDetailsModel shopDetailsModel) {
        super(fm);
        this.shopDetailsModel = shopDetailsModel;
    }


    public ShopDetailsModel getShopDetailsModel() {
        return shopDetailsModel;
    }

    public void setShopDetailsModel(ShopDetailsModel shopDetailsModel) {
        this.shopDetailsModel = shopDetailsModel;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            if(aboutUsFragment==null){
                aboutUsFragment = ShopDetailsAboutUsFragment.newInstance(this.shopDetailsModel);
            }
            return aboutUsFragment;
        }
        else{
            if(contactFragment==null)
                contactFragment = ShopDetailsContactFragment.newInstance(this.shopDetailsModel);
            return contactFragment;
        }
    }

    @Override
    public int getCount() {
        return this.shopDetailsModel == null ? 0:2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0)return "About Us";
        return "Contact";
    }
}
