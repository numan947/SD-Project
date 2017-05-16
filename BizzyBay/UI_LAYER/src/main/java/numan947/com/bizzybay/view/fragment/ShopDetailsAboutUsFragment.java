package numan947.com.bizzybay.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import numan947.com.bizzybay.model.ShopDetailsModel;

/**
 * @author numan947
 * @since 5/16/17.<br>
 **/

public class ShopDetailsAboutUsFragment extends Fragment {
    private static final String MODEL = "numan947.com.bizzybay.view.fragment.ShopDetailsModel.MODEL";


    public static ShopDetailsAboutUsFragment newInstance(ShopDetailsModel shopDetailsModel){
        Bundle bundle = new Bundle();
        bundle.putParcelable(MODEL,shopDetailsModel);

        ShopDetailsAboutUsFragment fragment = new ShopDetailsAboutUsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
