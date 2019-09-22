package merchant.com.bizzybay_merchant.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.merchant_example.executor.PostExecutionThread;
import com.merchant_example.executor.ThreadExecutor;
import com.merchant_example.interactor.GetShopDetailsUseCase;
import com.merchant_example.interactor.GetShopDetailsUseCaseImpl;
import com.merchant_example.repository.ShopRepository;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import merchant.com.bizzybay_merchant.BizzyBayMerchant;
import merchant.com.bizzybay_merchant.MainThread;
import merchant.com.bizzybay_merchant.R;
import merchant.com.bizzybay_merchant.mapper.ShopModelDataMapper;
import merchant.com.bizzybay_merchant.model.ShopDetailsModel;
import merchant.com.bizzybay_merchant.presenter.ShopDetailsPresenter;
import merchant.com.bizzybay_merchant.view.ShopDetailsView;
import merchant.com.bizzybay_merchant.view.adapter.ImageViewPagerAdapter;
import merchant.com.bizzybay_merchant.view.adapter.ShopDetailsViewPagerAdapter;
import merchant.com.merchant_data_layer.cache.ShopCache;
import merchant.com.merchant_data_layer.cache.TestShopCacheImpl;
import merchant.com.merchant_data_layer.entity.mapper.ShopEntityDataMapper;
import merchant.com.merchant_data_layer.executor.BackgroundExecutor;
import merchant.com.merchant_data_layer.repository.ShopDataRepository;
import merchant.com.merchant_data_layer.repository.datasource.ShopDataStoreFactory;

/**
 * @author numan947
 * @since 5/16/17.<br>
 **/

public class ShopDetailsFragment extends BaseFragment implements ShopDetailsView {

    private static final String fragmentId = "numan947.com.bizzybay.view.fragment.ShopDetailsFragment.SHOP_DETAILS_FRAGMENT";
    private static final String SHOP_ID = "numan947.com.bizzybay.view.fragment.ShopDetailsFragment.SHOP_ID";

    private static final String IMAGE_PAGER_CURRENT_ITEM="numan947.com.bizzybay.view.fragment.ShopDetailsFragment.IMAGE_PAGER_CURRENT_ITEM";
    private static final String DETAILS_PAGER_CURRENT_ITEM="numan947.com.bizzybay.view.fragment.ShopDetailsFragment.DETAILS_PAGER_CURRENT_ITEM";


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
        void showShopProducts(int shopId);
        //todo add some method for passing instructions to activity
    }

    //appbar
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;

    //image view pager
    private RelativeLayout imageViewPagerParent;
    private ViewPager imageViewPager;
    private ImageViewPagerAdapter imageViewPagerAdapter;
    private Runnable timerTask = new Runnable() {
        @Override
        public void run() {
            int cur = ShopDetailsFragment.this.imageViewPager.getCurrentItem();
            int size = ShopDetailsFragment.this.imageViewPager.getAdapter().getCount();

            if(size-1==cur)imageViewPager.setCurrentItem(0,false);
            else imageViewPager.setCurrentItem(++cur,true);
            imagePagerCurrentItem = cur;
        }
    };
    private Timer imagePagerTimer;
    private final int TIME_BEFORE_PAGE_CHANGE = 7000;

    private TextView shopUserName;
    private TextView shopName;
    private Button productButton;
    private TextView shopIdText;

    //swipe refresh view

    private SwipeRefreshLayout swipeRefreshLayout;

    //fragment viewpager
    private LinearLayout fragmentViewPagerParent;
    private TabLayout fragmentViewPagerTabLayout;
    private ViewPager fragmentViewPager;
    private ShopDetailsViewPagerAdapter shopDetailsViewPagerAdapter;

    //generic retry view
    private RelativeLayout retryLayout;
    private Button retryButton;

    //generic progress view
    private RelativeLayout progressView;

    private ShopDetailsListener shopDetailsListener;
    private ShopDetailsPresenter shopDetailsPresenter;

    private ShopDetailsModel shopDetailsModel;//will be saved on config change

    private int shopId; //we'll get it from intent and use it to pull data from datalayer

    private int imagePagerCurrentItem;
    private int detailsPagerCurrentItem;


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


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if(savedInstanceState==null){
            this.getParameters();
            this.imagePagerCurrentItem = -1;
            this.detailsPagerCurrentItem = -1;
            if(shopId!=-1)this.shopDetailsPresenter.initialize(shopId);
        }
        else{
            System.err.println("CurrentImagePage "+imagePagerCurrentItem+" CurrentFragmentPage "+detailsPagerCurrentItem);
            renderShopDetails(this.shopDetailsModel);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //save these, in case of configuration change
        this.imagePagerCurrentItem = imageViewPager.getCurrentItem();
        this.detailsPagerCurrentItem = fragmentViewPager.getCurrentItem();
    }

    private void getParameters() {
        Bundle bundle  = getArguments();
        this.shopId = bundle.getInt(SHOP_ID,-1);
    }


    private void setupViewPagers() {

        this.imageViewPager.setOffscreenPageLimit(3);

        this.imageViewPagerAdapter = new ImageViewPagerAdapter(getActivity().getSupportFragmentManager(),new ArrayList<String>());

        this.imageViewPager.setAdapter(imageViewPagerAdapter); //dummy

        imageViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });



        this.fragmentViewPager.setOffscreenPageLimit(3);

        this.shopDetailsViewPagerAdapter = new ShopDetailsViewPagerAdapter(getActivity().getSupportFragmentManager(),null);


        this.fragmentViewPager.setAdapter(shopDetailsViewPagerAdapter);

        this.fragmentViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                detailsPagerCurrentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                enableDisableSwipeRefresh(state==ViewPager.SCROLL_STATE_IDLE);
            }
        });


        this.fragmentViewPagerTabLayout.setupWithViewPager(fragmentViewPager);

    }

    private void enableDisableSwipeRefresh(boolean enable) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setEnabled(enable);
        }
    }

    private void addListeners() {
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shopDetailsPresenter.initialize(shopId);
                ShopDetailsFragment.this.resetPagerCurrentItems();
            }
        });

        this.retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopDetailsPresenter.initialize(shopId);
                ShopDetailsFragment.this.resetPagerCurrentItems();
            }
        });


        productButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopDetailsListener.showShopProducts(shopId);
            }
        });



    }

    private void resetPagerCurrentItems() {
        ShopDetailsFragment.this.detailsPagerCurrentItem = -1;
        ShopDetailsFragment.this.imagePagerCurrentItem = -1;
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
        fragmentViewPagerTabLayout = (TabLayout) returnView.findViewById(R.id.shop_details_tab_layout);

        retryLayout = (RelativeLayout) returnView.findViewById(R.id.rl_retry);
        retryButton = (Button) returnView.findViewById(R.id.bt_retry);
        progressView = (RelativeLayout) returnView.findViewById(R.id.rl_progress);
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
        PostExecutionThread postExecutionThread = MainThread.getInstance();
        ThreadExecutor threadExecutor = BackgroundExecutor.getInstance();

        //todo initialize serializer

        //todo add real cache instead of test

        ShopCache shopCache = TestShopCacheImpl.getInstance();


        ShopDataStoreFactory shopDataStoreFactory = new ShopDataStoreFactory(BizzyBayMerchant.getBizzyBayApplicationContext(),shopCache);

        ShopEntityDataMapper shopEntityDataMapper = new ShopEntityDataMapper();


        ShopRepository shopRepository = ShopDataRepository.getInstance(shopDataStoreFactory,shopEntityDataMapper);


        GetShopDetailsUseCase getShopDetailsUseCase = new GetShopDetailsUseCaseImpl(postExecutionThread,threadExecutor,shopRepository);

        ShopModelDataMapper shopModelDataMapper = new ShopModelDataMapper();


        this.shopDetailsPresenter = new ShopDetailsPresenter(this,getShopDetailsUseCase,shopModelDataMapper);





    }

    @Override
    public void renderShopDetails(ShopDetailsModel shopDetailsModel) {
        this.shopDetailsModel = shopDetailsModel; //save it


        this.renderImageViewPager(shopDetailsModel.getShopDetailsImageViewPagerImages());

        this.renderDetailsViewPager(shopDetailsModel);

        this.renderOthers(shopDetailsModel);


    }

    private void renderOthers(ShopDetailsModel shopDetailsModel) {
        this.shopIdText.setText(shopDetailsModel.getShopId());
        this.shopName.setText(shopDetailsModel.getShopName());
        this.shopUserName.setText(shopDetailsModel.getShopUserName());
    }

    private void renderDetailsViewPager(ShopDetailsModel shopDetailsModel) {

        this.shopDetailsViewPagerAdapter.setShopDetailsModel(shopDetailsModel);
        this.shopDetailsViewPagerAdapter.notifyDataSetChanged();

        if(detailsPagerCurrentItem==-1) {
            this.fragmentViewPager.setCurrentItem(0);
            this.detailsPagerCurrentItem = 0;
        }
        else this.fragmentViewPager.setCurrentItem(detailsPagerCurrentItem);
    }

    private void renderImageViewPager(ArrayList<String> shopDetailsImageViewPagerImages) {
        this.imageViewPagerAdapter.clear();
        this.imageViewPagerAdapter.addAll(shopDetailsImageViewPagerImages);
        this.imageViewPagerAdapter.notifyDataSetChanged();
        if(imagePagerCurrentItem==-1){
            this.imageViewPager.setCurrentItem(0);
            imagePagerCurrentItem=0;
        }
        else this.imageViewPager.setCurrentItem(imagePagerCurrentItem);
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

    @Override
    public void onResume() {
        super.onResume();
        shopDetailsPresenter.onResume();
        this.setupViewPagerTimer();
    }

    /**
     * Method to initiate the {@link ViewPager} {@link Timer}.
     * */
    private void setupViewPagerTimer() {
        imagePagerTimer = new Timer();
        imagePagerTimer.schedule(
                new TimerTask() {
            @Override
            public void run() {
                MainThread.getInstance().post(timerTask);
            }
        },TIME_BEFORE_PAGE_CHANGE,TIME_BEFORE_PAGE_CHANGE);
    }

    @Override
    public void onPause() {
        shopDetailsPresenter.onPause();
        this.cancelViewPagerTimer();
        super.onPause();
    }

    /**
     * Cancels {@link ViewPager} {@link Timer}.
     * */
    private void cancelViewPagerTimer() {
        imagePagerTimer.cancel();
        imagePagerTimer.purge();
    }

}
