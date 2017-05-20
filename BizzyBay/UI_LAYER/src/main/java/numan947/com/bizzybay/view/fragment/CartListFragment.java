package numan947.com.bizzybay.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.CartListModel;
import numan947.com.bizzybay.presenter.CartListPresenter;
import numan947.com.bizzybay.view.CartListView;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class CartListFragment extends BaseFragment implements CartListView {
    private static final String fragmentId = "numan947.com.bizzybay.view.fragment.CartListFragment.CART_LIST_FRAGMENT";

    public static String getFragmentId()
    {
        return fragmentId;
    }

    public static CartListFragment newInstance()
    {
        //todo may be add some parameters here
        return new CartListFragment();
    }

    public interface CartListListener{
        void finishActivity();
        void onShopNameClicked(int shopId);
        void onProductClicked(int productId,int shopId);
    }

    private CartListListener cartListListener;
    private CartListPresenter cartListPresenter;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout retryView;
    private RelativeLayout loadingView;
    private Button retryButton;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof CartListListener)
            this.cartListListener  = (CartListListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //todo add default things
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //todo add view
        View view = inflater.inflate(R.layout.shopping_bag_fragment,container,false);

        this.bindAll(view);
        this.addListeners();
        this.setupRecyclerView();

        
        return null;
    }

    private void setupRecyclerView() {
        // TODO: 5/20/17 setup recycler view
    }

    private void addListeners() {
        //// TODO: 5/20/17 Add listeners
    }

    private void bindAll(View view) {
        this.swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.cart_page_fragment_SRL);
        this.recyclerView  = (RecyclerView) view.findViewById(R.id.cart_page_fragment_recycler_view);

        this.retryView = (RelativeLayout) view.findViewById(R.id.rl_retry);
        this.loadingView = (RelativeLayout)view.findViewById(R.id.rl_progress);
        this.retryButton = (Button)view.findViewById(R.id.bt_retry);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //todo 
    }

    @Override
    public void onResume() {
        super.onResume();
        cartListPresenter.onResume();
    }

    @Override
    public void onPause() {
        cartListPresenter.onPause();
        super.onPause();
    }

    @Override
    protected void initializePresenter() {
        //todo
    }

    @Override
    public void renderCartList(ArrayList<CartListModel> cartListModels) {
        //todo
    }

    @Override
    public void hideCartList() {
        //todo
    }

    @Override
    public void showCartList() {
        //todo
    }

    @Override
    public void onDeleteShopButtonClicked(int shopId, int orderId) {
        //// TODO: 5/20/17  
    }

    @Override
    public void onDeleteProductButtonClicked(int shopId, int productId, int orderId) {
        // TODO: 5/20/17  
    }

    @Override
    public void onCheckoutButtonClicked(int shopId, int orderId) {
        // TODO: 5/20/17  
    }

    @Override
    public void onProductItemClicked(int shopId, int productId) {
        // TODO: 5/20/17  
    }

    @Override
    public void onShopNameClicked(int shopId) {
        // TODO: 5/20/17  
    }

    @Override
    public void showLoading() {
        // TODO: 5/20/17  
    }

    @Override
    public void hideLoading() {
        // TODO: 5/20/17  
    }

    @Override
    public void showRetry() {
        // TODO: 5/20/17  
    }

    @Override
    public void hideRetry() {
        // TODO: 5/20/17  
    }   

    @Override
    public void showError(String message) {
        // TODO: 5/20/17
    }
}
