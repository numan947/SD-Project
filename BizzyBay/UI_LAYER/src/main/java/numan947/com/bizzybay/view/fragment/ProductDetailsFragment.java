package numan947.com.bizzybay.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.DetailsProductModel;
import numan947.com.bizzybay.view.ProductDetailsView;

/**
 * Created by numan947 on 5/7/17.
 */

public class ProductDetailsFragment extends BaseFragment implements ProductDetailsView{

    private static final String PRODUCT_ID="numan947.com.bizzybay.view.activity.DetailsProductFragment.PRODUCT_ID";
    private static final String SHOP_ID="numan947.com.bizzybay.view.activity.DetailsProductFragment.SHOP_ID";

    //implemented by the activity to respond to fragment's different needs
    public interface ProductDetailsListener{
        void OnCategorySelected(String category);
        void OnShopLocationClicked(DetailsProductModel model);
        void OnShopNameClicked(DetailsProductModel model);
    }

    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout mainLinearLayout;
    private LinearLayout retryLayout;
    private LinearLayout loadingLayout;
    private TextView productTitle;
    private TextView shopName;
    private TextView productPrice;
    private TextView productDetails;
    private TextView shopLocation;
    private TextView productCategory;
    private Button addToCartButton;
    private Button buyButton;
    private Button likeButton;





    public static ProductDetailsFragment newInstance(int productId,int shopId)
    {
        Bundle bundle  = new Bundle();
        bundle.putInt(PRODUCT_ID,productId);
        bundle.putInt(SHOP_ID,shopId);

        ProductDetailsFragment fragment = new ProductDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View returnView = inflater.inflate(R.layout.details_product_view,container,false);










        return returnView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initializePresenter() {

    }

    @Override
    public void renderProduct(DetailsProductModel model) {

    }

    @Override
    public void viewShop(DetailsProductModel model) {

    }

    @Override
    public void showProductLiked(DetailsProductModel model) {

    }

    @Override
    public void showProductAddedToCart(DetailsProductModel model) {

    }

    @Override
    public void buyProduct(DetailsProductModel model) {

    }

    @Override
    public void viewShopLocation(DetailsProductModel model) {

    }

    @Override
    public void viewProductsByCategory(String category) {

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
