package numan947.com.bizzybay.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.ShopDetailsModel;
import numan947.com.bizzybay.presenter.ShopDetailsPresenter;
import numan947.com.bizzybay.view.ShopDetailsView;

/**
 * @author numan947
 * @since 5/16/17.<br>
 **/

public class ShopDetailsFragment extends BaseFragment implements ShopDetailsView {

    private static final String fragmentId = "numan947.com.bizzybay.view.fragment.SHOP_DETAILS_FRAGMENT";
    private static final String SHOP_ID = "numan947.com.bizzybay.view.fragment.SHOP_ID";

    public static String getFragmentId(){
        return fragmentId;
    }

    public static ShopDetailsFragment newInstance(int shopId)
    {

        Bundle bundle = new Bundle();
        bundle.putInt(SHOP_ID,shopId);
        ShopDetailsFragment shopDetailsFragment = new ShopDetailsFragment();
        shopDetailsFragment.setArguments(bundle);
        return shopDetailsFragment;

    }

    public interface  ShopDetailsListener{
        void finishActivity();
        //todo add some method for passing instructions to activity
    }

    //appbar
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;

    //image view pager
    private RelativeLayout imageViewPagerParent;
    private ViewPager imageViewPager;
    private TextView shopUserName;
    private TextView shopName;
    private Button productButton;
    private TextView shopIdText;

    //swipe refresh view

    private SwipeRefreshLayout swipeRefreshLayout;

    //fragment viewpager
    private LinearLayout fragmentViewPagerParent;
    private TableLayout fragmentViewPagerTabLayout;
    private ViewPager fragmentViewPager;

    //generic retry view
    private RelativeLayout retryLayout;
    private Button retryButton;

    //generic progress view
    private RelativeLayout progressView;

    private ShopDetailsListener shopDetailsListener;
    private ShopDetailsPresenter shopDetailsPresenter;

    private ShopDetailsModel shopDetailsModel;//will be saved on config change

    private int shopId; //we'll get it from intent and use it to pull data from datalayer



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof ShopDetailsListener)
            this.shopDetailsListener = (ShopDetailsListener) context;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View returnView = inflater.inflate(R.layout.shop_details_fragment,container,false);

        this.bindAll(returnView);
        this.setupToolbar();
        this.addListeners();
        this.setupViewPagers();


        return returnView;
    }

    private void setupViewPagers() {
        //todo setup viewpagers here, both image and fragment
    }

    private void addListeners() {
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shopDetailsPresenter.initialize(shopId);
            }
        });



    }

    private void setupToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return ShopDetailsFragment.this.onOptionsItemSelected(item);
            }
        });

    }


    private void bindAll(View returnView) {

        appBarLayout = (AppBarLayout) returnView.findViewById(R.id.shop_details_ABL);
        toolbar = (Toolbar) returnView.findViewById(R.id.shop_details_toolbar);

        imageViewPagerParent = (RelativeLayout) returnView.findViewById(R.id.shop_details_image_view_pager_parent);
        imageViewPager = (ViewPager) returnView.findViewById(R.id.shop_details_image_view_pager);
        shopUserName = (TextView) returnView.findViewById(R.id.shop_details_shop_user_name);
        shopName = (TextView) returnView.findViewById(R.id.shop_details_shop_name);
        productButton = (Button) returnView.findViewById(R.id.shop_details_product_button);
        shopIdText = (TextView) returnView.findViewById(R.id.shop_details_shop_id);


        swipeRefreshLayout = (SwipeRefreshLayout) returnView.findViewById(R.id.shop_details_swipe_refresh_layout);

        fragmentViewPagerParent = (LinearLayout) returnView.findViewById(R.id.shop_details_fragment_view_parent);
        fragmentViewPager = (ViewPager) returnView.findViewById(R.id.shop_details_view_pager);
        fragmentViewPagerTabLayout = (TableLayout) returnView.findViewById(R.id.shop_details_tab_layout);

        retryLayout = (RelativeLayout) returnView.findViewById(R.id.rl_retry);
        retryButton = (Button) returnView.findViewById(R.id.bt_retry);
        progressView = (RelativeLayout) returnView.findViewById(R.id.rl_progress);
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
        switch (item.getItemId()){
            case android.R.id.home:
                shopDetailsListener.finishActivity();
                return true;
            //todo handle other case here
        }
        return super.onOptionsItemSelected(item);
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
        this.fragmentViewPagerParent.setVisibility(View.GONE);
        this.imageViewPagerParent.setVisibility(View.GONE);
    }

    @Override
    public void showShopDetails() {
        this.fragmentViewPagerParent.setVisibility(View.VISIBLE);
        this.imageViewPagerParent.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        this.swipeRefreshLayout.setRefreshing(true);
        //or should we use the stock one?
    }

    @Override
    public void hideLoading() {
        this.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showRetry() {
        this.retryLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.retryLayout.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        //todo do some awesome things here -_-
    }
}
