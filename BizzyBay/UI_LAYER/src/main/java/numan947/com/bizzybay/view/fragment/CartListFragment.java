package numan947.com.bizzybay.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.interactor.GetCartListUseCase;
import com.example.interactor.GetCartListUseCaseImpl;
import com.example.repository.CartListWishListRepository;

import java.util.ArrayList;

import numan947.com.bizzybay.MainThread;
import numan947.com.bizzybay.R;
import numan947.com.bizzybay.mapper.CartListWishListModelDataMapper;
import numan947.com.bizzybay.model.CartListModel;
import numan947.com.bizzybay.model.CartProductModel;
import numan947.com.bizzybay.presenter.CartListPresenter;
import numan947.com.bizzybay.view.CartListView;
import numan947.com.bizzybay.view.adapter.CartListAdapter;
import numan947.com.bizzybay.view.component.EndlessRecyclerViewScrollListener;
import numan947.com.data_layer.cache.CartListWishListCache;
import numan947.com.data_layer.cache.TestCartListWishListCacheImpl;
import numan947.com.data_layer.entity.mapper.CartListWishListEntityDataMapper;
import numan947.com.data_layer.executor.BackgroundExecutor;
import numan947.com.data_layer.repository.CartListWishListDataRepository;
import numan947.com.data_layer.repository.datasource.CartListWishListDataStoreFactory;

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


    private CartListAdapter cartListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;

    private CartListAdapter.Callback callBackForRecyclerViewAdapter = new CartListAdapter.Callback() {
        @Override
        public void onProductItemClicked(int productId, int shopId) {

        }

        @Override
        public void onShopNameClicked(int shopId) {

        }

        @Override
        public void onShopDeleteButtonClicked(int position) {

        }

        @Override
        public void onCheckOutButtonClicked(CartListModel cartListModel) {

        }

        @Override
        public void onProductDeleteButtonClicked(CartListModel cartListModel, CartProductModel cartProduct) {

        }
    };



    private int pageNumber;
    private ArrayList<CartListModel>adapterItems;



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

        View view = inflater.inflate(R.layout.shopping_bag_fragment,container,false);

        this.bindAll(view);
        this.addListeners();
        this.setupRecyclerView();

        
        return view;
    }

    private void setupRecyclerView() {


        this.linearLayoutManager = new LinearLayoutManager(getContext());

        this.endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                cartListPresenter.initialize(++pageNumber);
            }
        };


        this.cartListAdapter = new CartListAdapter(getContext(),adapterItems,callBackForRecyclerViewAdapter);


        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(cartListAdapter);
    }

    private void addListeners() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cartListPresenter.initialize(0);
            }
        });

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cartListPresenter.initialize(0);
            }
        });
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

        if(savedInstanceState==null){
            this.cartListPresenter.initialize(0);
        }
        //do nothing
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
        this.adapterItems = new ArrayList<>();


        PostExecutionThread postExecutionThread = MainThread.getInstance();
        ThreadExecutor threadExecutor = BackgroundExecutor.getInstance();

        //todo add JSON Parser here plus other things, like a real cache instead of a test one

        CartListWishListCache cache = TestCartListWishListCacheImpl.getInstance();

        CartListWishListDataStoreFactory dataStoreFactory = new CartListWishListDataStoreFactory(cache);

        CartListWishListEntityDataMapper entityDataMapper = new CartListWishListEntityDataMapper();

        CartListWishListRepository cartListWishListRepository = CartListWishListDataRepository.getInstance(entityDataMapper,dataStoreFactory);

        GetCartListUseCase getCartListUseCase = new GetCartListUseCaseImpl(postExecutionThread,threadExecutor,cartListWishListRepository);
        CartListWishListModelDataMapper modelDataMapper = new CartListWishListModelDataMapper();

        this.cartListPresenter = new CartListPresenter(this,getCartListUseCase,modelDataMapper);
    }

    @Override
    public void renderCartList(int pageNumber,ArrayList<CartListModel> cartListModels) {
        if(pageNumber==0){
            resetRecyclerViewAdapter();
            cartListAdapter.addAll(cartListModels);


            cartListAdapter.notifyItemRangeInserted(0,cartListModels.size());
        }
        else{

            int before = cartListAdapter.getModelSize();

            cartListAdapter.addAll(cartListModels);

            int after = cartListAdapter.getModelSize();

            cartListAdapter.notifyItemRangeInserted(before,after-before);
        }
    }


    private void resetRecyclerViewAdapter() {
        pageNumber = 0;
        cartListAdapter.clearAll();
        cartListAdapter.notifyDataSetChanged();
        endlessRecyclerViewScrollListener.resetState();
    }

    @Override
    public void hideCartList() {
        this.recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showCartList() {
        this.recyclerView.setVisibility(View.VISIBLE);
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
        //goto suitable activity
        cartListListener.onProductClicked(productId,shopId);
    }

    @Override
    public void onShopNameClicked(int shopId) {
        //goto suitable activity
        cartListListener.onShopNameClicked(shopId);
    }

    @Override
    public void showLoading() {
        this.loadingView.setVisibility(View.VISIBLE);
        this.swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        this.loadingView.setVisibility(View.GONE);
        this.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showRetry() {
        this.retryView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.retryView.setVisibility(View.GONE);
    }   

    @Override
    public void showError(String message) {
        //todo show some awesome error message -_- well, may be later
    }
}
