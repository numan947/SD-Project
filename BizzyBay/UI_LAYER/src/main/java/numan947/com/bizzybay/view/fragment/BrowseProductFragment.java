package numan947.com.bizzybay.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.ProductModelMultiple;
import numan947.com.bizzybay.view.ProductListView;

/**
 * Created by numan947 on 5/1/17.
 */

public class BrowseProductFragment extends RecyclerViewBaseFragment implements ProductListView {


    public static BrowseProductFragment newInstance()
    {
        BrowseProductFragment browseProductFragment = new BrowseProductFragment();
        return browseProductFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.browse_product_fragment,container,false);

        rv.setHasFixedSize(true);

        super.setRecyclerView(rv);

        return rv;
    }



    @Override
    protected void initializePresenter() {

    }

    @Override
    public void renderProductList(Collection<ProductModelMultiple> products) {

    }

    @Override
    public void viewProduct(ProductModelMultiple product) {

    }

    @Override
    public void showProductAddedToCart(ProductModelMultiple product) {

    }

    @Override
    public void showProductAddedToWishList(ProductModelMultiple product) {

    }

    @Override
    public void buyProduct(ProductModelMultiple product) {

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
