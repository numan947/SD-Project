package merchant.com.bizzybay_merchant.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.merchant_example.executor.PostExecutionThread;
import com.merchant_example.executor.ThreadExecutor;
import com.merchant_example.interactor.GetHistoryDetailsUseCase;
import com.merchant_example.interactor.GetHistoryDetailsUseCaseImpl;
import com.merchant_example.repository.HistoryRepository;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;

import merchant.com.bizzybay_merchant.BizzyBayMerchant;
import merchant.com.bizzybay_merchant.MainThread;
import merchant.com.bizzybay_merchant.R;
import merchant.com.bizzybay_merchant.mapper.HistoryModelDataMapper;
import merchant.com.bizzybay_merchant.model.HistoryDetailsModel;
import merchant.com.bizzybay_merchant.presenter.HistoryDetailsPresenter;
import merchant.com.bizzybay_merchant.view.HistoryDetailsView;
import merchant.com.merchant_data_layer.cache.HistoryCache;
import merchant.com.merchant_data_layer.cache.TestHistoryCacheImpl;
import merchant.com.merchant_data_layer.entity.mapper.HistoryEntityDataMapper;
import merchant.com.merchant_data_layer.executor.BackgroundExecutor;
import merchant.com.merchant_data_layer.repository.HistoryDataRepository;
import merchant.com.merchant_data_layer.repository.datasource.HistoryDataStoreFactory;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryDetailsFragment extends BaseFragment implements HistoryDetailsView, View.OnClickListener {
    private static final String fragmentId = "numan947.com.bizzybay.view.fragment.HISTORY_DETAILS_FRAGMENT";

    private static final String ORDER_ID = "numan947.com.bizzybay.view.fragment.ORDER_ID";
    private static final String SHOP_ID  = "numan947.com.bizzybay.view.fragment.SHOP_ID";
    private static final String PRODUCT_ID = "numan947.com.bizzybay.view.fragment.PRODUCT_ID";

    public interface HistoryDetailsListener{
        void onProductNameClicked(int productId,int shopId);
        void onShopNameClicked(int shopId);
        void finishActivity();
    }



    private Toolbar toolbar;
    private AppBarLayout appbarLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NestedScrollView nestedScrollView;
    private LinearLayout mainLinearLayout;

    private PorterShapeImageView productImageView;
    private TextView productName;
    private TextView productOrderId;
    private TextView shopName;
    private TextView productQuantity;
    private TextView productPrice;
    private TextView productOrderTime;
    private TextView productDeliveryTime;
    private TextView productDeliveryLocation;
    private TextView paymentMethod;
    private TextView paymentId;
    private TextView productDetails;

    private RelativeLayout retryLayout;
    private RelativeLayout loadingLayout;
    private Button retryButton;


    private HistoryDetailsPresenter historyDetailsPresenter;
    private HistoryDetailsListener historyDetailsListener;
    private HistoryDetailsModel historyDetailsModel;

    int orderId,shopId,productId;





    public static HistoryDetailsFragment newInstance(int orderId,int shopId,int productId){

        Bundle bundle  = new Bundle();
        bundle.putInt(ORDER_ID,orderId);
        bundle.putInt(SHOP_ID,shopId);
        bundle.putInt(PRODUCT_ID,productId);

        HistoryDetailsFragment fragment = new HistoryDetailsFragment();

        fragment.setArguments(bundle);

        return fragment;
    }
    public static String getFragmentID()
    {
        return fragmentId;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof HistoryDetailsListener) //get the listener to Activity
            this.historyDetailsListener = (HistoryDetailsListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_details_fragment,container,false);
        this.bindAll(view);
        this.addListeners();
        this.setupToolbar();
        return view;


    }

    private void setupToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return HistoryDetailsFragment.this.onOptionsItemSelected(item);
            }
        });


    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //todo setup options menu later
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                historyDetailsListener.finishActivity();
                break;
            //todo add other



        }

        return super.onOptionsItemSelected(item);
        //todo setup options menu later
    }


    private void addListeners() {
        productName.setOnClickListener(this);
        shopName.setOnClickListener(this);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //try reloading the data

                //todo may be invalidate the image here? only delete cached image if new one is available
                HistoryDetailsFragment.this.historyDetailsPresenter.initialize(HistoryDetailsFragment.this.orderId,
                        HistoryDetailsFragment.this.shopId,HistoryDetailsFragment.this.productId);
            }
        });

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo may be invalidate the image here? only delete cached image if new one is available
                HistoryDetailsFragment.this.historyDetailsPresenter.initialize(HistoryDetailsFragment.this.orderId,
                        HistoryDetailsFragment.this.shopId,HistoryDetailsFragment.this.productId);
            }
        });
    }


    @Override
    public void onClick(View v) {
        //chaining to presenter
        switch (v.getId()) {
            case R.id.history_details_view_product_name:
                historyDetailsPresenter.viewProduct(historyDetailsModel.getProductId(),historyDetailsModel.getShopId());
                break;
            case R.id.history_details_view_product_shop_name:
                historyDetailsPresenter.viewShop(historyDetailsModel.getShopId());
                break;
            //todo handle any additional case
        }
    }

    private void bindAll(View view) {

        toolbar = (Toolbar)view.findViewById(R.id.history_details_fragment_toolbar);
        appbarLayout = (AppBarLayout)view.findViewById(R.id.history_details_fragment_ABL);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.history_details_fragment_SRL);
        nestedScrollView = (NestedScrollView)view.findViewById(R.id.history_details_view_NSV);
        loadingLayout = (RelativeLayout)view.findViewById(R.id.rl_progress);
        retryLayout = (RelativeLayout)view.findViewById(R.id.rl_retry);
        retryButton = (Button)view.findViewById(R.id.bt_retry);
        mainLinearLayout = (LinearLayout)view.findViewById(R.id.history_details_fragment_main_LL) ;

        productImageView = (PorterShapeImageView) view.findViewById(R.id.history_details_view_image);
        productName = (TextView) view.findViewById(R.id.history_details_view_product_name);
        productOrderId = (TextView)view.findViewById(R.id.history_details_view_product_order_id);
        shopName = (TextView)view.findViewById(R.id.history_details_view_product_shop_name);
        productQuantity = (TextView)view.findViewById(R.id.history_details_view_product_quantity);
        productPrice = (TextView)view.findViewById(R.id.history_details_view_product_price);
        productOrderTime = (TextView)view.findViewById(R.id.history_details_view_product_order_time);
        productDeliveryTime = (TextView)view.findViewById(R.id.history_details_view_product_delivered_time);
        productDeliveryLocation = (TextView)view.findViewById(R.id.history_details_view_product_delivery_location);
        paymentMethod = (TextView)view.findViewById(R.id.history_details_view_payment_method);
        paymentId = (TextView)view.findViewById(R.id.history_details_view_payment_id);
        productDetails = (TextView)view.findViewById(R.id.history_details_view_product_details);


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState==null){
            this.getParameters(getArguments());
            historyDetailsPresenter.initialize(this.orderId,this.shopId,this.productId); //pull from data layer
        }
        else
            this.renderHistoryDetails(this.historyDetailsModel); //render the saved data
    }

    private void getParameters(Bundle arguments) {
        if(arguments!=null){
            this.productId = arguments.getInt(PRODUCT_ID,-1);
            this.shopId = arguments.getInt(SHOP_ID,-1);
            this.orderId = arguments.getInt(ORDER_ID,-1);
        }
    }


    @Override
    protected void initializePresenter() {
    //todo later
        PostExecutionThread postExecutionThread = MainThread.getInstance();
        ThreadExecutor threadExecutor = BackgroundExecutor.getInstance();

        HistoryCache historyCache = TestHistoryCacheImpl.getInstance(); //todo replace with a real one


        //todo insert json serializer here and a real cache


        HistoryDataStoreFactory historyDataStoreFactory = new HistoryDataStoreFactory(BizzyBayMerchant.getBizzyBayApplicationContext(),historyCache);

        HistoryEntityDataMapper historyEntityDataMapper = new HistoryEntityDataMapper();

        HistoryRepository historyRepository = HistoryDataRepository.getInstance(historyDataStoreFactory,historyEntityDataMapper);

        GetHistoryDetailsUseCase getHistoryDetailsUseCase = new GetHistoryDetailsUseCaseImpl(postExecutionThread,threadExecutor,historyRepository);

        HistoryModelDataMapper historyModelDataMapper = new HistoryModelDataMapper();


        historyDetailsPresenter = new HistoryDetailsPresenter(this,getHistoryDetailsUseCase,historyModelDataMapper);

    }

    @Override
    public void showDetails() {
        this.mainLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDetails() {
        this.mainLinearLayout.setVisibility(View.GONE);
    }

    @Override
    public void renderHistoryDetails(HistoryDetailsModel model) {

        this.historyDetailsModel = model; //save it

        this.renderTextualElements(model); //render the textual data
        this.renderImage(model.getProductImage()); //render the image
    }

    private void renderImage(String productImage) {
        Glide.with(this).load(productImage)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade().fitCenter()
                .into(productImageView);
    }

    private void renderTextualElements(HistoryDetailsModel model) {
        this.productName.setText(model.getProductName());
        this.productDetails.setText(model.getProductDetails());
        this.productDeliveryLocation.setText(model.getProductDeliveryLocation());
        this.productDeliveryTime.setText(model.getProductDeliveryTime());
        this.productOrderTime.setText(model.getProductOrderTime());
        this.productOrderId.setText(model.getProductOrderId());
        this.shopName.setText(model.getShopName());
        this.productPrice.setText(model.getProductPrice());
        this.productQuantity.setText(model.getProductQuantity());
        this.paymentId.setText(model.getPaymentId());
        this.paymentMethod.setText(model.getPaymentMethod());
    }

    @Override
    public void onProductNameClicked(int productId,int shopId) {
        //chain to activity
        historyDetailsListener.onProductNameClicked(productId,shopId);
    }

    @Override
    public void onShopNameClicked(int shopId) {
        //chain to activity
        historyDetailsListener.onShopNameClicked(shopId);
    }

    @Override
    public void showLoading() {
        this.swipeRefreshLayout.setRefreshing(true);
        //todo or do we use the default one?
    }

    @Override
    public void hideLoading() {
        this.swipeRefreshLayout.setRefreshing(false);
        //todo or do we use the default one?
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

        //todo show some awesome error message here
    }

    @Override
    public void onResume() {
        super.onResume();
        historyDetailsPresenter.onResume();
    }

    @Override
    public void onPause() {

        historyDetailsPresenter.onPause();
        super.onPause();
    }
}
