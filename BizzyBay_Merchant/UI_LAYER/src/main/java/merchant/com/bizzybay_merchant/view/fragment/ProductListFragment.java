package merchant.com.bizzybay_merchant.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.merchant_example.executor.PostExecutionThread;
import com.merchant_example.executor.ThreadExecutor;
import com.merchant_example.interactor.GetProductListUseCase;
import com.merchant_example.interactor.GetProductListUseCaseImpl;
import com.merchant_example.repository.ProductRepository;

import java.util.ArrayList;

import merchant.com.bizzybay_merchant.BizzyBayMerchant;
import merchant.com.bizzybay_merchant.MainThread;
import merchant.com.bizzybay_merchant.R;
import merchant.com.bizzybay_merchant.mapper.ProductModelDataMapper;
import merchant.com.bizzybay_merchant.model.ProductListModel;
import merchant.com.bizzybay_merchant.presenter.ProductListPresenter;
import merchant.com.bizzybay_merchant.view.ProductListView;
import merchant.com.bizzybay_merchant.view.adapter.ProductListAdapter;
import merchant.com.bizzybay_merchant.view.component.EndlessRecyclerViewScrollListener;
import merchant.com.merchant_data_layer.cache.ProductCache;
import merchant.com.merchant_data_layer.cache.TestProductCacheImpl;
import merchant.com.merchant_data_layer.entity.mapper.ProductEntityDataMapper;
import merchant.com.merchant_data_layer.executor.BackgroundExecutor;
import merchant.com.merchant_data_layer.repository.ProductDataRepository;
import merchant.com.merchant_data_layer.repository.datasource.ProductDataStoreFactory;

import static android.view.View.GONE;

/**
 *
 * @author numan947
 * @since 5/6/17.<br>
 *
 * Fragment that shows Product List.
 * It implements {@link ProductListView} interface.
 *
 **/


public class ProductListFragment extends BaseFragment implements View.OnClickListener,ProductListView {
    private static final String fragmentId = "numan947.com.bizzybay.view.fragment.ProductListFragment.PRODUCT_LIST_FRAGMENT";
    private static final String SHOPID = "numan947.com.bizzybay.view.fragment.ProductListFragment.ShopId";

    //this is the interface to send data to Activity so that it can change fragment or switch Activity
    /**
     * Interface to be implemented by the Parent Activity, so that the fragment can send data to the activity.
     * */
    public interface ProductListListener{
        /**
         * Called when a Product in the list is clicked.
         * */
        void onProductClicked(int productId,int shopId,String fragmentId);
        void onHomeButtonPressed();
    }


    //the mandatory presenter and related listener to pass data to presenter
    private ProductListPresenter productListPresenter;
    private ProductListListener productListListener;
    private ProductListAdapter adapter;
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;

    private ArrayList<ProductListModel>adapterItems;//will be retained
    private int pageNumber; //will be retained
    private int shopId;


    //the views related to the view
    private RecyclerView recyclerView;
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private Button retry_button;
    private RelativeLayout rl_retry;
    private RelativeLayout rl_progress;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CoordinatorLayout coordinatorLayout;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;



    /**
     * Initializer for the Fragment.
     * */
    public static ProductListFragment newInstance(int shopId)
    {
        Bundle bundle = new Bundle();
        bundle.putInt(SHOPID,shopId);

        ProductListFragment productListFragment = new ProductListFragment();
        productListFragment.setArguments(bundle);

        return productListFragment;
    }

    public static String getFragmentID()
    {
        return  fragmentId;

    }

    /**
     * This is the callback implementation provided to the adapter of the
     * {@link RecyclerView}, so that it can chain the event handling to this fragment.
     * */
    private final ProductListAdapter.Callback adapterCallback = new ProductListAdapter.Callback() {
        @Override
        public void OnLikedButtonClicked(ProductListModel model, int position) {
            productListPresenter.likeProduct(model,position);
        }

        @Override
        public void OnCardViewClicked(ProductListModel model, int position) {
            productListPresenter.viewProduct(model);
        }
    };


    /**
     * Handles different click events.
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_retry:
                productListPresenter.initialize(0,this.shopId);
                break;
            //todo handle other cases
        }
    }

    /**
     * Acquires the listener to the activity here.
     * */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //MainActivity should implement this one
        if(context instanceof ProductListListener)
            this.productListListener=((ProductListListener) context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.list_product_fragment,container,false);

        this.bindAll(v);
        this.addListenersToViews();
        this.setupRecyclerView();
        this.setupSwipeRefreshLayout();

       // this.setupToolbar();

        return v;
    }

    private void setupToolbar() {

        //todo add toolbar menu items here

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onOptionsItemSelected(item);
            }
        });
        //todo add handler for toolbar elements


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.navigation_view_menu,menu);
        //todo add optionsmenu here
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //todo handle optionsmenu click here
        switch (item.getItemId()){
            case android.R.id.home:
                productListListener.onHomeButtonPressed();
                break;
            //todo handle other cases here
        }

        return super.onOptionsItemSelected(item);

    }

    /**
     * Method for setting up the swipe refresh layout's refreshing.
     * */
    private void setupSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ProductListFragment.this.productListPresenter.initialize(0, ProductListFragment.this.shopId);
            }
        });
    }

    /**
     * Method for resetting recycler view adapter and the endless scroll listener
     * */
    private void resetRecyclerViewAdapter() {
        pageNumber = 0;
        adapter.clearAll();
        adapter.notifyDataSetChanged();
        endlessRecyclerViewScrollListener.resetState();
    }

    /**
     *Adds listeners to the needed views.
     * */
    private void addListenersToViews() {
        rl_retry.setOnClickListener(this);
    }

    /**
     * Binds all views with their java counter parts.
     * */
    private void bindAll(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.list_product_fragment_recycler_view);
        retry_button = (Button)v.findViewById(R.id.bt_retry);
        rl_progress = (RelativeLayout)v.findViewById(R.id.rl_progress);
        rl_retry = (RelativeLayout)v.findViewById(R.id.rl_retry);
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.list_product_fragment_SRL);
        coordinatorLayout = (CoordinatorLayout)v.findViewById(R.id.list_product_fragment_CL);

        appBarLayout = (AppBarLayout)v.findViewById(R.id.list_product_ABL);
        toolbar = (Toolbar)v.findViewById(R.id.list_product_toolbar);
    }

    /**
     * sets up the recycler view.
     * */
    private void setupRecyclerView() {
        //initialize the adapter with dummy list, that'll act as container for the product models
        adapter = new ProductListAdapter(getContext(), adapterCallback,adapterItems);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

         // Endless Recycler View Scroll listener, this, listener gives the illusion of endless recycler
         //view by loading data before the recycler view reaches the end
        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                productListPresenter.initialize(++pageNumber, ProductListFragment.this.shopId);
            }
        };

        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(adapter);
        this.recyclerView.addOnScrollListener(endlessRecyclerViewScrollListener);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(savedInstanceState==null) {

            this.getParameters();

            //this is where presenter tries to load data
            if (productListPresenter == null)
                initializePresenter();

            productListPresenter.initialize(0, ProductListFragment.this.shopId);
        }
        //else do nothing....everything should be saved
    }

    /**
     * This is where presenter start to load data.
     * */


    private void getParameters() {
        Bundle bundle  = getArguments();
        if(bundle!=null){
            this.shopId = bundle.getInt(SHOPID);

            if(shopId==-1){
                //(this.appBarLayout.getParent()).invalidateChildInParent();
                coordinatorLayout.removeView(appBarLayout);
                setHasOptionsMenu(false);
            }

            else
                this.setupToolbar();

        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //todo save states here
    }

    private void restoreStates(Bundle savedInstanceState) {
        //todo restore saved states here
        if(savedInstanceState!=null){

        }
    }


    @Override
    protected void initializePresenter() {
        adapterItems = new ArrayList<>();
        //initializing the presenter

        //needed by usecase
        ThreadExecutor threadExecutor = BackgroundExecutor.getInstance();
        PostExecutionThread postExecutionThread = MainThread.getInstance();

        //todo add JSON Serializer
        //todo add ProductCache just like shown in the example

        //needed bby productDataStoreFactory
        ProductCache productCache = TestProductCacheImpl.getInstance();


        //needed by productDataRepository
        ProductDataStoreFactory productDataStoreFactory = new ProductDataStoreFactory(BizzyBayMerchant.getBizzyBayApplicationContext(),productCache);
        //needed by productDataRepository
        ProductEntityDataMapper productEntityDataMapper = new ProductEntityDataMapper();

        //needed by usecase
        ProductRepository productRepository = ProductDataRepository.getInstance(productDataStoreFactory,productEntityDataMapper);

        //needed by the presenter
        GetProductListUseCase getProductListUseCase = new GetProductListUseCaseImpl(productRepository,threadExecutor,postExecutionThread);
        //needed by the presenter
        ProductModelDataMapper productModelDataMapper = new ProductModelDataMapper();

        //needed by the view
        this.productListPresenter = new ProductListPresenter(this,getProductListUseCase,productModelDataMapper);

    }



    @Override
    public void onResume() {
        super.onResume();
        productListPresenter.onResume();
    }



    @Override
    public void onPause() {
        productListPresenter.onPause();
        super.onPause();
    }


    @Override
    public void renderProductList(int pageNumber, ArrayList<ProductListModel> products) {
        if(products!=null){
            if(pageNumber==0) {
                ProductListFragment.this.resetRecyclerViewAdapter();
                adapter.addAll(products);
                adapter.notifyItemRangeInserted(0,products.size());
            }
            else if(pageNumber==-1){
                //do nothing, it means end of the list
            }
            else{
                this.pageNumber = pageNumber;
                int before = adapter.getModelSize();
                adapter.addAll(products);
                int after = adapter.getModelSize();
                adapter.notifyItemRangeInserted(before,after-before);

            }
        }
    }

    @Override
    public void viewProduct(ProductListModel product) {
        productListListener.onProductClicked(product.getProductID(),product.getShopID(),getFragmentID());
    }


    @Override
    public void showProductLiked(ProductListModel productModel, int position) {
        //reload the adapter here
        adapter.notifyItemChanged(position,productModel);
    }

    @Override
    public void showLoading() {
        //rl_progress.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(GONE);
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        //rl_progress.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showRetry() {
        swipeRefreshLayout.setRefreshing(false);
        rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        rl_retry.setVisibility(GONE);
    }

    @Override
    public void showError(String message) {
        //todo do error handle later
        this.showToastMessage(message);
    }


}
