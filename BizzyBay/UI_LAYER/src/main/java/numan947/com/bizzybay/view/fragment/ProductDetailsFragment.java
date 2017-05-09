package numan947.com.bizzybay.view.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
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
import numan947.com.bizzybay.BizzyBay;
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
 *
 * @author numan947
 * @since 5/7/17.<br>
 *
 * This is the View for the Product Details.
 * This implement the {@link ProductDetailsView} interface and provides implementation.
 **/

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class ProductDetailsFragment extends BaseFragment implements ProductDetailsView, View.OnClickListener {

    //elements for sharing information between activities/fragments
    private static final String PRODUCT_ID="numan947.com.bizzybay.view.activity.DetailsProductFragment.PRODUCT_ID";
    private static final String SHOP_ID="numan947.com.bizzybay.view.activity.DetailsProductFragment.SHOP_ID";

    private int productId;//todo may be we need to add user id here as well
    private int shopId;

    //implemented by the activity to respond to fragment's different needs
    /**
     * This interface is to be implemented by the activity holding the Fragment so that the fragment can
     * send "Activity Change" requests.
     * */
    public interface ProductDetailsListener{
        /**
         * Method to call when one of the Category is clicked.
         * */
        void OnCategorySelected(String category);
        /**
         * Method to call when the Shop Location is clicked.
         * */
        void OnShopLocationClicked(int shopId);
        /**
         * Method to call when the Shop Name is clicked.
         * */
        void OnShopNameClicked(int shopId);
        /**
         * Method to call when Buy Product button is clicked.
         * */
        void OnBuyProduct(int productId,int shopId);
    }



    //native elements
    private ProductDetailsPresenter productDetailsPresenter;
    private ProductDetailsListener activityListener;
    private boolean cartButtonLocalStatus;
    private boolean likeButtonLocalStatus;
    private ProductDetailsViewPagerAdapter viewPagerAdapter;


    //ui elements
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
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

    //needed drawables
    private Drawable isCartedDrawable;
    private Drawable notCartedDrawable ;
    private Drawable isLikedDrawable;
    private Drawable notLikedDrawable;
    private Drawable viewPagerBackground;




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
    private Timer viewPagerTimer;
    private final long TIME_BEFORE_PAGE_CHANGE = 3000;


    public ProductDetailsFragment(){
        //empty constructor
    }

    /**
     * Initializer for the Fragment.
     * Fragment is initialized with the needed parameters.
     * The parameters are to be put into a {@link Bundle} and
     * the {@link Bundle} to be set as argument to the {@link android.support.v4.app.Fragment}
     * */
    public static ProductDetailsFragment newInstance(int productId,int shopId)
    {
        Bundle bundle  = new Bundle();
        bundle.putInt(PRODUCT_ID,productId);
        bundle.putInt(SHOP_ID,shopId);

        ProductDetailsFragment fragment = new ProductDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * Get the data passing listener to the activity.
     * */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activityListener = (ProductDetailsListener)context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(false);
        setHasOptionsMenu(true);
    }

    /**
     * Method for getting parameters from the {@link Bundle} passed to the Fragment
     * */
    private void getParameters() {
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
        this.setupSwipeRefreshLayout();
        this.addListenersToViews();

        return returnView;
    }

    /**
     * Method for adding listeners to the views available.
     * */
    private void addListenersToViews() {
        likeButton.setOnClickListener(this);
        addToCartButton.setOnClickListener(this);
        buyButton.setOnClickListener(this);
        shopLocation.setOnClickListener(this);
        shopName.setOnClickListener(this);
    }

    /**
     * Method setting up the swipe refresh layout's refreshing.
     * */
    private void setupSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ProductDetailsFragment.this.productDetailsPresenter.initialize(productId,shopId);
            }
        });
    }

    /**
     * The parameters passed to the activity / the parameters saved while configuration changed are restored here.
     * The drawables for this view is initialized here.
     * Also he presenter's main work is started here.
     * */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState!=null)this.restoreStates(savedInstanceState);

        this.getParameters();
        this.initializeDrawables();
        this.productDetailsPresenter.initialize(this.productId,this.shopId);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //todo save fragment specific states here
    }

    private void restoreStates(Bundle savedInstanceState) {
        //todo restore fragment specific states here
    }

    /**
     * Loads the {@link Drawable}s for this view from the {@link android.content.res.Resources}
     * */
    private void initializeDrawables() {
        notLikedDrawable = ContextCompat.getDrawable(getContext(),R.drawable.not_liked);
        isLikedDrawable  = ContextCompat.getDrawable(getContext(),R.drawable.liked);
        notCartedDrawable = ContextCompat.getDrawable(getContext(),R.drawable.cart);
        isCartedDrawable  = ContextCompat.getDrawable(getContext(),R.drawable.carted);
        viewPagerBackground = ContextCompat.getDrawable(getContext(),R.drawable.placeholder);
    }

    //method for setting up the toolbar
    /**
     * Sets up the toolbar and the related things.
     * */
    private void setupToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        //todo add handler for toolbar elements
    }

    /**
     * Creates Options Menu.
     * */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //todo create options menu here
    }

    /**
     * Handles Options Menu Item click.
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
        //todo handle options menu item click here
    }

    //method for binding view elements
    /**
     * Binds all the XML views with their java counterparts.
     * */
    private void bindAll(View returnView) {
        toolbar = (Toolbar) returnView.findViewById(R.id.toolbar);


        appBarLayout = (AppBarLayout) returnView.findViewById(R.id.details_product_view_ABL);
        coordinatorLayout = (CoordinatorLayout) returnView.findViewById(R.id.details_product_view_CL);
        swipeRefreshLayout = (SwipeRefreshLayout) returnView.findViewById(R.id.details_product_view_SRL);
        collapsingToolbarLayout = (CollapsingToolbarLayout) returnView.findViewById(R.id.details_product_view_CTL);

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
    /**
     * Sets up the viewpager as well as the related buttons.
     * */
    private void setupViewPager() {

        viewPager.setOffscreenPageLimit(3);

        //initiating the adapter with container
        viewPagerAdapter = new ProductDetailsViewPagerAdapter(getActivity().getSupportFragmentManager(),new ArrayList<String>());
        viewPager.setAdapter(viewPagerAdapter);
        pagerIndicator.setViewPager(viewPager);
        viewPagerAdapter.registerDataSetObserver(pagerIndicator.getDataSetObserver());

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


    @SuppressWarnings("deprecation")
    @Override
    public void renderProduct(DetailsProductModel model) {

        //add images to the viewpager
        this.renderViewPagerItems(model);

        this.renderTextualData(model);

        //setup product categories
        this.renderProductCategories(model.getProductCategories());

        this.renderButtonData(model);


    }

    /**
     * Method for rendering button data.
     * */
    private void renderButtonData(DetailsProductModel model) {
        cartButtonLocalStatus = model.isCarted();
        likeButtonLocalStatus = model.isLiked();

        //setup drawables for the buttons

        if(model.isCarted())
            this.addDrawableToButton(addToCartButton,isCartedDrawable);
        else
            this.addDrawableToButton(addToCartButton,notCartedDrawable);

        if(model.isLiked())
            this.addDrawableToButton(likeButton,isLikedDrawable);
        else
            this.addDrawableToButton(likeButton,notLikedDrawable);
    }

    /**
     * Method for rendering basic textual data to the textviews.
     * */
    private void renderTextualData(DetailsProductModel model) {
        //setup trivial data
        productDetails.setText(model.getProductDetails());
        productPrice.setText(model.getProductPrice());
        productTitle.setText(model.getProductTitle());

        //setup shop details
        shopName.setText(model.getShopName());
        shopLocation.setText(model.getShopLocation());

    }

    /**
     * Method for rendering data to viewpager.
     * */
    private void renderViewPagerItems(DetailsProductModel model) {
        viewPagerAdapter.clear();
        viewPagerAdapter.addAll(model.getProductImages());
        viewPagerAdapter.notifyDataSetChanged();

        viewPager.setCurrentItem(0);
    }

    /**
     * Method for adding a {@link Drawable} as the background of a {@link Button}
     * */
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

    /**
     * Events are passed to presenter for handling.
     * The view holds a reference to the Presenter.
     * So, it can call the related method when needed.
     * */
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

    /**
     * Method for setting up the productCategories.
     * Handled specially as we need to use {@link android.text.Spannable} text.
     * */
    private void renderProductCategories(ArrayList<String> categories) {
        //todo use span for setting up this plus setup categories' click listener separately

        //for demo purpose only
        StringBuilder cat= new StringBuilder();
        for(String a:categories)
            cat.append(a).append(" ");
        productCategory.setText(cat.toString());

    }


    @Override
    protected void initializePresenter() {

        //needed by GetProductDetailsUseCase for working in the background.
        ThreadExecutor threadExecutor = BackgroundExecutor.getInstance();

        //needed by GetProductDetailsUseCase for posting result in the UI thread.
        PostExecutionThread postExecutionThread = MainThread.getInstance();

        //todo initialize serializer

        //todo add real cache instead of test
        //needed by the productDataStoreFactory
        ProductCache productCache = TestProductCacheImpl.getInstance();

        //needed by ProductRepository
        ProductDataStoreFactory productDataStoreFactory = new ProductDataStoreFactory(BizzyBay.getBizzyBayApplicationContext(),productCache);

        //needed by ProductRepository
        ProductEntityDataMapper productEntityDataMapper = new ProductEntityDataMapper();


        //needed by GetProductDetailsUseCase
        ProductRepository productRepository = ProductDataRepository.getInstance(productDataStoreFactory,productEntityDataMapper);

        //needed by the Presenter.
        GetProductDetailsUseCase getProductDetailsUseCase = new GetProductDetailsUseCaseImpl(productRepository,threadExecutor,postExecutionThread);

        //needed by the Presenter
        ProductModelDataMapper productModelDataMapper = new ProductModelDataMapper();

        //needed by the View
        this.productDetailsPresenter = new ProductDetailsPresenter(this,getProductDetailsUseCase,productModelDataMapper);
    }



    @Override
    public void viewShop(int shopId) {
        this.activityListener.OnShopNameClicked(shopId);
    }

    @Override
    public void showProductLiked(int productId, int shopId) {
        //todo update button state to the repository



        if(likeButtonLocalStatus)
            addDrawableToButton(likeButton,notLikedDrawable);
        else
            addDrawableToButton(likeButton,isLikedDrawable);

        likeButtonLocalStatus=(!likeButtonLocalStatus);
    }

    @Override
    public void showProductAddedToCart(int productId,int shopId) {
        //todo update button state to the repository


        //changing button state
        if(cartButtonLocalStatus)
            addDrawableToButton(addToCartButton,notCartedDrawable);
        else
            addDrawableToButton(addToCartButton,isCartedDrawable);
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
        //this.loadingLayout.setVisibility(View.VISIBLE);
        this.viewPager.setVisibility(View.GONE);
        this.pagerIndicator.setVisibility(View.GONE);
        this.swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {

        //this.loadingLayout.setVisibility(View.GONE);
        this.viewPager.setVisibility(View.VISIBLE);
        this.pagerIndicator.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
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
        this.setupViewPagerTimer();
    }

    /**
     * Method to initiate the {@link ViewPager} {@link Timer}.
     * */
    private void setupViewPagerTimer() {
        viewPagerTimer = new Timer();
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
        this.cancelViewPagerTimer();
        super.onPause();
    }

    /**
     * Cancels {@link ViewPager} {@link Timer}.
     * */
    private void cancelViewPagerTimer() {
        viewPagerTimer.cancel();
        viewPagerTimer.purge();
    }

}
