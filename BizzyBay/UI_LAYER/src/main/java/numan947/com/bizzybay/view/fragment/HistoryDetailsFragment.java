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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.view.HistoryDetailsView;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryDetailsFragment extends BaseFragment implements HistoryDetailsView {
    private static final String fragmentId = "numan947.com.bizzybay.view.fragment.HISTORY_DETAILS_FRAGMENT";


    private Toolbar toolbar;
    private AppBarLayout appbarLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NestedScrollView nestedScrollView;

    private PorterShapeImageView imageView;
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

        imageView = (PorterShapeImageView) view.findViewById(R.id.history_details_view_image);
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

    @Override
    protected void initializePresenter() {

    }

    @Override
    public void showDetails() {

    }

    @Override
    public void hideDetails() {

    }

    @Override
    public void onProductNameClicked() {

    }

    @Override
    public void onShopNameClicked() {

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
