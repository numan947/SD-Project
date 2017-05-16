package numan947.com.bizzybay.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import numan947.com.bizzybay.model.ShopDetailsModel;
import numan947.com.bizzybay.view.ShopDetailsView;

/**
 * @author numan947
 * @since 5/16/17.<br>
 **/

public class ShopDetailsFragment extends BaseFragment implements ShopDetailsView {

    private static final String fragmentId = "numan947.com.bizzybay.view.fragment.SHOP_DETAILS_FRAGMENT";


    public static String getFragmentId(){
        return fragmentId;
    }

    public static ShopDetailsFragment newInstance()
    {
        //todo add parameters
        return new ShopDetailsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //todo acquire listener to activity here
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
        //todo bind views here
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //todo initialize here
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //todo have the toolbar setup here
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
        //todo handle toolbar events here
    }

    @Override
    protected void initializePresenter() {
        //todo
    }

    @Override
    public void renderShopDetails(ShopDetailsModel shopDetailsModel) {
        //todo
    }

    @Override
    public void hideShopDetails() {

    }

    @Override
    public void showShopDetails() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }
}
