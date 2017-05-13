package numan947.com.bizzybay.view.fragment;

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
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;

import numan947.com.bizzybay.MainThread;
import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.HistoryDetailsModel;
import numan947.com.bizzybay.presenter.HistoryDetailsPresenter;
import numan947.com.bizzybay.view.HistoryDetailsView;
import numan947.com.data_layer.cache.HistoryCache;
import numan947.com.data_layer.cache.TestHistoryCacheImpl;
import numan947.com.data_layer.executor.BackgroundExecutor;
import numan947.com.data_layer.repository.datasource.DiskHistoryDataStore;
import numan947.com.data_layer.repository.datasource.HistoryDataStore;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryDetailsFragment extends BaseFragment implements HistoryDetailsView {
    private static final String fragmentId = "numan947.com.bizzybay.view.fragment.HISTORY_DETAILS_FRAGMENT";

    public interface HistoryDetailsListener{
        void onProductNameClicked(int productId,int shopId);
        void onShopNameClicked(int shopId);
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





    public static HistoryDetailsFragment newInstance(){
        //todo add necessary arguments here
        return new HistoryDetailsFragment();
    }
    public static String getFragmentID()
    {
        return fragmentId;
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
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //todo setup options menu later
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
        //todo setup options menu later
    }


    private void addListeners() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //todo implement later
            }
        });

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo implement later
            }
        });
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
            historyDetailsPresenter.initialize();
        }
    }



    @Override
    protected void initializePresenter() {
    //todo later
        PostExecutionThread postExecutionThread = MainThread.getInstance();
        ThreadExecutor threadExecutor = BackgroundExecutor.getInstance();

        HistoryCache historyCache = TestHistoryCacheImpl.getInstance(); //todo replace with a real one

        HistoryDataStore historyDataStore = 

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
        this.renderTextualElements(model);
        this.renderImage(model.getProductImage());
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
}
