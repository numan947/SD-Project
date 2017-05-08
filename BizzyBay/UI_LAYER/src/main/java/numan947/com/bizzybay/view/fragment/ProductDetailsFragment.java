package numan947.com.bizzybay.view.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.interactor.GetProductDetailsUseCase;
import com.example.interactor.GetProductDetailsUseCaseImpl;
import com.example.repository.ProductRepository;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import numan947.com.bizzybay.MainThread;
import numan947.com.bizzybay.R;
import numan947.com.bizzybay.mapper.ProductModelDataMapper;
import numan947.com.bizzybay.model.DetailsProductModel;
import numan947.com.bizzybay.presenter.ProductDetailsPresenter;
import numan947.com.bizzybay.view.ProductDetailsView;
import numan947.com.bizzybay.view.adapter.ProductDetailsViewPagerAdapter;
import numan947.com.data_layer.cache.ProductCache;
import numan947.com.data_layer.cache.TestProductCacheImpl;
import numan947.com.data_layer.entity.mapper.ProductEntityDataMapper;
import numan947.com.data_layer.executor.BackgroundExecutor;
import numan947.com.data_layer.repository.ProductDataRepository;
import numan947.com.data_layer.repository.datasource.ProductDataStoreFactory;

/**
 * Created by numan947 on 5/7/17.
 */

@SuppressWarnings("FieldCanBeLocal")
public class ProductDetailsFragment extends BaseFragment implements ProductDetailsView, View.OnClickListener {

    //elements for sharing information between activities/fragments
    private static final String PRODUCT_ID="numan947.com.bizzybay.view.activity.DetailsProductFragment.PRODUCT_ID";
    private static final String SHOP_ID="numan947.com.bizzybay.view.activity.DetailsProductFragment.SHOP_ID";


    //implemented by the activity to respond to fragment's different needs
    public interface ProductDetailsListener{
        void OnCategorySelected(String category);
        void OnShopLocationClicked(int shopId);
        void OnShopNameClicked(int shopId);
        void OnBuyProduct(int productId,int shopId);
    }

    //native elements //todo may be we need to add user id here as well
    private int productId;
    private int shopId;
    private ProductDetailsPresenter productDetailsPresenter;
    private ProductDetailsListener activityListener;
    private boolean cartButtonLocalStatus;
    private boolean likeButtonLocalStatus;
    private ProductDetailsViewPagerAdapter adapter;


    //ui elements
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout mainLinearLayout;
    private RelativeLayout retryLayout;
    private RelativeLayout loadingLayout;
    private TextView productTitle;
    private TextView shopName;
    private TextView productPrice;
    private TextView productDetails;
    private TextView shopLocation;
    private TextView productCategory;
    private Button addToCartButton;
    private Button buyButton;
    private Button likeButton;
    private ViewPager viewPager;
    private Button pagerLeft;
    private Button pagerRight;
    private CircleIndicator pagerIndicator;
    private Button retryButton;

    //viewpager page changer
    private MainThread mainThread = MainThread.getInstance();
    private Runnable viewPagerUpdater = new Runnable() {
        @Override
        public void run() {
            if(viewPager.getAdapter()!=null){

                int cur = viewPager.getCurrentItem();

                if(cur==viewPager.getAdapter().getCount()-1)
                    viewPager.setCurrentItem(0,true);
                else
                    viewPager.setCurrentItem(++cur,true);
            }
        }
    };
    private final Timer viewPagerTimer = new Timer();
    private final long TIME_BEFORE_PAGE_CHANGE = 3000;



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
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activityListener = (ProductDetailsListener)context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setHasOptionsMenu(true);

        //get the arguments passed to the fragment
        Bundle b = getArguments();

        if(b!=null) {
            productId = b.getInt(PRODUCT_ID);
            shopId = b.getInt(SHOP_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View returnView = inflater.inflate(R.layout.details_product_view,container,false);

        this.bindAll(returnView);
        this.setupToolbar();
        this.setupViewPager();

        return returnView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.productDetailsPresenter.initialize(this.productId,this.shopId);
    }

    //method for setting up the toolbar
    private void setupToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        //todo add handler for toolbar elements
    }

    //method for binding view elements
    private void bindAll(View returnView) {
        toolbar = (Toolbar) returnView.findViewById(R.id.toolbar);


        appBarLayout = (AppBarLayout) returnView.findViewById(R.id.details_product_view_ABL);
        coordinatorLayout = (CoordinatorLayout) returnView.findViewById(R.id.details_product_view_CL);
        swipeRefreshLayout = (SwipeRefreshLayout) returnView.findViewById(R.id.details_product_view_SRL);


        mainLinearLayout = (LinearLayout) returnView.findViewById(R.id.details_product_main_LL);
        retryLayout = (RelativeLayout) returnView.findViewById(R.id.rl_retry);
        loadingLayout = (RelativeLayout) returnView.findViewById(R.id.rl_progress);
        retryButton =(Button) returnView.findViewById(R.id.bt_retry);
        retryButton.setOnClickListener(this);


        productTitle = (TextView) returnView.findViewById(R.id.details_product_view_product_title);
        productDetails = (TextView) returnView.findViewById(R.id.details_product_view_product_details);
        productPrice = (TextView) returnView.findViewById(R.id.details_product_view_product_price);
        shopName = (TextView) returnView.findViewById(R.id.details_product_view_product_shopname);
        shopLocation = (TextView) returnView.findViewById(R.id.details_product_view_shop_location);
        productCategory = (TextView) returnView.findViewById(R.id.details_product_view_category);
        addToCartButton = (Button) returnView.findViewById(R.id.details_product_view_cart_button);
        likeButton = (Button) returnView.findViewById(R.id.details_product_view_like_button);
        buyButton = (Button) returnView.findViewById(R.id.details_product_view_buy_button);


        viewPager = (ViewPager) returnView.findViewById(R.id.details_product_viewpager);
        pagerLeft = (Button) returnView.findViewById(R.id.details_product_viewpager_leftarrow);
        pagerRight = (Button) returnView.findViewById(R.id.details_product_viewpager_rightarrow);
        pagerIndicator = (CircleIndicator) returnView.findViewById(R.id.details_product_viewpager_indicator);
    }


    //method for initially setting up the viewpager and click events
    private void setupViewPager() {

        viewPager.setOffscreenPageLimit(3);

        //setup
        pagerRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getAdapter() != null) {
                    if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1)
                        viewPager.setCurrentItem(0);
                    else viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

                }
            }
        });

        pagerLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getAdapter()!=null){
                    if(viewPager.getCurrentItem()==0)
                        viewPager.setCurrentItem(viewPager.getAdapter().getCount()-1);
                    else
                        viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                }
            }
        });
    }

    //method for setting the viewpager after data is loaded
    private void setupViewPagerAdapter(ProductDetailsViewPagerAdapter adapter)
    {
        viewPager.setAdapter(adapter);
        pagerIndicator.setViewPager(viewPager);
        adapter.registerDataSetObserver(pagerIndicator.getDataSetObserver());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void renderProduct(DetailsProductModel model) {

        //setupViewpager

        if(adapter==null) {
            adapter = new ProductDetailsViewPagerAdapter(
                    getActivity().getSupportFragmentManager(), model.getProductImages());
        }
        else{
            adapter.clear();
            adapter.addAll(model.getProductImages());
        }
        setupViewPagerAdapter(adapter);


        //setup trivial data
        productDetails.setText(model.getProductDetails());
        productPrice.setText(model.getProductPrice());
        productTitle.setText(model.getProductTitle());

        //setup shop details
        shopName.setText(model.getShopName());
        shopLocation.setText(model.getShopLocation());

        //setup product categories
        setupProductCategories(model.getProductCategory());

        cartButtonLocalStatus = model.isCarted();
        likeButtonLocalStatus = model.isLiked();

        //setup drawables for the buttons
        Drawable cartDrawable;
        Drawable likeDrawable;
        if(model.isCarted())
                cartDrawable = ContextCompat.getDrawable(getContext(),R.drawable.carted);
        else
            cartDrawable = ContextCompat.getDrawable(getContext(),R.drawable.cart);

        if(model.isLiked())
            likeDrawable = ContextCompat.getDrawable(getContext(),R.drawable.liked);
        else
            likeDrawable = ContextCompat.getDrawable(getContext(),R.drawable.not_liked);


        this.addDrawableToButton(likeButton,likeDrawable);
        this.addDrawableToButton(addToCartButton,cartDrawable);



        likeButton.setOnClickListener(this);
        addToCartButton.setOnClickListener(this);
        buyButton.setOnClickListener(this);
        shopLocation.setOnClickListener(this);
        shopName.setOnClickListener(this);
    }

    @SuppressWarnings("deprecation")
    private void addDrawableToButton(Button b, Drawable drawable)
    {
        if(Build.VERSION.SDK_INT>=16) {
            b.setBackground(drawable);
        }
        else {
            b.setBackgroundDrawable(drawable);
        }
    }

    @Override
    public void onClick(View v) {

        //handle click events here
        switch (v.getId()){
            case R.id.details_product_view_like_button:
                this.productDetailsPresenter.onLikeButtonClicked(productId,shopId);
                break;
            case R.id.details_product_view_cart_button:
                this.productDetailsPresenter.onAddToCartButtonClicked(productId,shopId);
                break;
            case R.id.details_product_view_buy_button:
                this.productDetailsPresenter.onBuyButtonClicked(productId,shopId);
                break;
            case R.id.details_product_view_shop_location:
                this.productDetailsPresenter.onShopLocationClicked(shopId);
                break;
            case R.id.details_product_view_product_shopname:
                this.productDetailsPresenter.onShopNameClicked(shopId);
                break;
            case R.id.rl_retry:
                this.productDetailsPresenter.initialize(productId,shopId);
                break;
        }

    }

    private void setupProductCategories(ArrayList<String> categories) {
        //todo use span for setting up this plus setup categories' click listener separately

        //for demo purpose only
        StringBuilder cat= new StringBuilder();
        for(String a:categories)
            cat.append(a).append(" ");
        productCategory.setText(cat.toString());

    }


    @Override
    protected void initializePresenter() {
        ThreadExecutor threadExecutor = BackgroundExecutor.getInstance();
        PostExecutionThread postExecutionThread = MainThread.getInstance();

        //todo initialize serializer

        //todo add real cache instead of test
        ProductCache productCache = TestProductCacheImpl.getInstance();

        ProductDataStoreFactory productDataStoreFactory = new ProductDataStoreFactory(getContext(),productCache);

        ProductEntityDataMapper productEntityDataMapper = new ProductEntityDataMapper();

        ProductRepository productRepository = ProductDataRepository.getInstance(productDataStoreFactory,productEntityDataMapper);

        GetProductDetailsUseCase getProductDetailsUseCase = new GetProductDetailsUseCaseImpl(productRepository,threadExecutor,postExecutionThread);
        ProductModelDataMapper productModelDataMapper = new ProductModelDataMapper();

        this.productDetailsPresenter = new ProductDetailsPresenter(this,getProductDetailsUseCase,productModelDataMapper);
    }



    @Override
    public void viewShop(int shopId) {
        this.activityListener.OnShopNameClicked(shopId);
    }

    @Override
    public void showProductLiked(int productId, int shopId) {
        //todo update button state to the repository


        //changing button state
        Drawable drawable;
        if(likeButtonLocalStatus)
            drawable = ContextCompat.getDrawable(getContext(),R.drawable.not_liked);
        else
            drawable = ContextCompat.getDrawable(getContext(),R.drawable.liked);

        addDrawableToButton(likeButton,drawable);
        likeButtonLocalStatus=(!likeButtonLocalStatus);
    }

    @Override
    public void showProductAddedToCart(int productId,int shopId) {
        //todo update button state to the repository

        //changing button state
        Drawable drawable;
        if(cartButtonLocalStatus)
            drawable = ContextCompat.getDrawable(getContext(),R.drawable.cart);
        else
            drawable = ContextCompat.getDrawable(getContext(),R.drawable.carted);

        addDrawableToButton(addToCartButton,drawable);
        cartButtonLocalStatus=(!cartButtonLocalStatus);

    }

    @Override
    public void buyProduct(int productId, int shopId) {
       activityListener.OnBuyProduct(productId,shopId);
    }

    @Override
    public void viewShopLocation(int shopId) {
     activityListener.OnShopLocationClicked(shopId);
    }

    @Override
    public void viewProductsByCategory(String category) {
        activityListener.OnCategorySelected(category);
    }



    @Override
    public void showLoading() {
        this.loadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.loadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void showDetailsView() {
        this.mainLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDetailsView() {
        this.mainLinearLayout.setVisibility(View.GONE);
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
        //todo show error here
    }

    @Override
    public void onResume() {
        super.onResume();
        productDetailsPresenter.onResume();


        viewPagerTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mainThread.post(viewPagerUpdater);
            }
        },TIME_BEFORE_PAGE_CHANGE,TIME_BEFORE_PAGE_CHANGE);
    }

    @Override
    public void onPause() {
        productDetailsPresenter.onPause();
        viewPagerTimer.cancel();
        viewPagerTimer.purge();
        super.onPause();
    }
}
