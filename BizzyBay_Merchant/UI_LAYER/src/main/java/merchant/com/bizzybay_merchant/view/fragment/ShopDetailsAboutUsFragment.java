package merchant.com.bizzybay_merchant.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import merchant.com.bizzybay_merchant.R;
import merchant.com.bizzybay_merchant.model.ShopDetailsModel;

/**
 * @author numan947
 * @since 5/16/17.<br>
 **/

public class ShopDetailsAboutUsFragment extends Fragment {
    private static final String MODEL = "numan947.com.bizzybay.view.fragment.ShopDetailsModel.MODEL";
    private static final String fragmentId ="numan947.com.bizzybay.view.fragment.ShopDetailsModel.SHOP_DETAILS_ABOUT_US";

    public static String getFragmentId()
    {
        return fragmentId;
    }

    public static ShopDetailsAboutUsFragment newInstance(ShopDetailsModel shopDetailsModel){
        Bundle bundle = new Bundle();
        bundle.putParcelable(MODEL,shopDetailsModel);

        ShopDetailsAboutUsFragment fragment = new ShopDetailsAboutUsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }


    private ShopDetailsModel shopdetailsModel;

    private ImageView shopDetailsAboutImage;
    private TextView shopDetailsAboutDescription;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_details_about_us, container, false);

        this.bindAll(view);
        this.getParameters();
        this.renderAll();

        return view;
    }



    private void renderAll() {
        if(shopdetailsModel!=null){
            this.renderImage(shopdetailsModel.getShopDetailsAboutUsImage());
            this.renderAboutText(shopdetailsModel.getShopDetailsAboutUs());
        }
    }

    private void renderImage(String shopDetailsAboutUsImage) {
        Glide.with(this).load(shopDetailsAboutUsImage)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade().fitCenter()
                .into(shopDetailsAboutImage);
    }

    private void renderAboutText(String shopDetailsAboutUs) {
        this.shopDetailsAboutDescription.setText(shopDetailsAboutUs);
    }


    private void getParameters() {
        Bundle bundle = getArguments();
        shopdetailsModel = bundle.getParcelable(MODEL);
    }

    private void bindAll(View view) {
        shopDetailsAboutImage = (ImageView) view.findViewById(R.id.shop_details_about_image);
        shopDetailsAboutDescription = (TextView)view.findViewById(R.id.shop_details_about_description);
    }
}
