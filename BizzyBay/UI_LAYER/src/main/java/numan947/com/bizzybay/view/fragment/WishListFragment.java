package numan947.com.bizzybay.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import numan947.com.bizzybay.model.WishListModel;
import numan947.com.bizzybay.presenter.WishListPresenter;
import numan947.com.bizzybay.view.WishListView;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class WishListFragment extends BaseFragment implements WishListView {
    private static final String fragmentId = "numan947.com.bizzybay.view.fragment.WishListFragment.WISH_LIST_FRAGMENT";

    public static String getFragmentId()
    {
        return fragmentId;
    }

    public static WishListFragment newInstance()
    {
        //todo add some good parameters here
        return new WishListFragment();
    }



    public interface WishListListener{
        void onProductClicked(int productId,int shopId,String fragmentId);
        void onShopClicked(int shopId,String fragmentId);
    }


    private WishListListener wishListListener;
    private WishListPresenter wishListPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof WishListListener)
            wishListListener = (WishListListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initializePresenter() {

    }



    @Override
    public void renderWishList(int pageNumber, ArrayList<WishListModel> wishListModels) {

    }

    @Override
    public void hideWishList() {

    }

    @Override
    public void showWishList() {

    }

    @Override
    public void hideEmpty() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onLikeButtonClicked(WishListModel wishListModel) {

    }

    @Override
    public void onShopClicked(int shopId) {

    }

    @Override
    public void onProductClicked(int productId, int shopId) {

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
