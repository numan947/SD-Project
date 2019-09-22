package merchant.com.bizzybay_merchant.view.fragment;

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
import android.widget.TextView;

import com.merchant_example.executor.PostExecutionThread;
import com.merchant_example.executor.ThreadExecutor;
import com.merchant_example.interactor.GetWishListUseCase;
import com.merchant_example.interactor.GetWishListUseCaseImpl;
import com.merchant_example.repository.CartListWishListRepository;

import java.util.ArrayList;

import merchant.com.bizzybay_merchant.MainThread;
import merchant.com.bizzybay_merchant.R;
import merchant.com.bizzybay_merchant.mapper.CartListWishListModelDataMapper;
import merchant.com.bizzybay_merchant.model.WishListModel;
import merchant.com.bizzybay_merchant.presenter.WishListPresenter;
import merchant.com.bizzybay_merchant.view.WishListView;
import merchant.com.bizzybay_merchant.view.adapter.WishListAdapter;
import merchant.com.bizzybay_merchant.view.component.EndlessRecyclerViewScrollListener;
import merchant.com.merchant_data_layer.cache.CartListWishListCache;
import merchant.com.merchant_data_layer.cache.TestCartListWishListCacheImpl;
import merchant.com.merchant_data_layer.entity.mapper.CartListWishListEntityDataMapper;
import merchant.com.merchant_data_layer.executor.BackgroundExecutor;
import merchant.com.merchant_data_layer.repository.CartListWishListDataRepository;
import merchant.com.merchant_data_layer.repository.datasource.CartListWishListDataStoreFactory;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class WishListFragment extends BaseFragment implements WishListView {
    private static final String fragmentId = "numan947.com.bizzybay.view.fragment.WishListFragment.WISH_LIST_FRAGMENT";

    public static String getFragmentId()
    {
        return fragmentId;
    }

    public static WishListFragment newInstance()
    {
        //todo add some good parameters here
        return new WishListFragment();
    }



    public interface WishListListener{
        void onProductClicked(int productId,int shopId,String fragmentId);
        void onShopClicked(int shopId,String fragmentId);
    }


    private WishListAdapter.Callback callbackForRecyclerViewAdapter = new WishListAdapter.Callback() {
        //these calls are chained to presenter

        @Override
        public void onShopClicked(int shopId) {
            wishListPresenter.onShopClicked(shopId);
        }

        @Override
        public void onProductClicked(int productId, int shopId) {
            wishListPresenter.onProductClicked(productId,shopId);
        }

        @Override
        public void onLikeButtonClicked(WishListModel wishListModel,int position) {
            wishListPresenter.onLikeButtonClicked(wishListModel,position);
        }
    };

    private WishListListener wishListListener;
    private WishListPresenter wishListPresenter;


    //views
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RelativeLayout retryView;
    private RelativeLayout loadingView;
    private RelativeLayout emptyView;
    private TextView emptyViewText;
    private Button retryButton;

    //recycler view things
    private ArrayList<WishListModel>adapterItems;
    private int pageNumber;

    private WishListAdapter wishListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof WishListListener)
            wishListListener = (WishListListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wish_list_fragment,container,false);

        this.bindAll(view);
        this.addListeners();
        this.setupRecyclerView();

        return view;
    }

    private void setupRecyclerView() {
        this.wishListAdapter = new WishListAdapter(this.adapterItems,getContext(),this.callbackForRecyclerViewAdapter);
        this.linearLayoutManager = new LinearLayoutManager(getContext());
        this.endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                wishListPresenter.initialize(++pageNumber);
            }
        };


        this.recyclerView.setAdapter(wishListAdapter);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.addOnScrollListener(endlessRecyclerViewScrollListener);
    }


    private void addListeners() {
        this.retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wishListPresenter.initialize(0);
            }
        });
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                wishListPresenter.initialize(0);
            }
        });
    }

    private void bindAll(View view) {
        this.swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.wish_list_fragment_SRL);
        this.recyclerView = (RecyclerView)view.findViewById(R.id.wish_list_fragment_recycler_view);
        this.loadingView = (RelativeLayout)view.findViewById(R.id.rl_progress);
        this.retryView = (RelativeLayout)view.findViewById(R.id.rl_retry);
        this.retryButton = (Button)view.findViewById(R.id.bt_retry);
        this.emptyView = (RelativeLayout)view.findViewById(R.id.rl_empty);
        this.emptyViewText = (TextView)view.findViewById(R.id.txt_empty);

        emptyViewText.setText("WISH LIST IS EMPTY");
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(savedInstanceState==null){
            wishListPresenter.initialize(0);
        }
        //else do nothing B)

    }

    @Override
    protected void initializePresenter() {
        //initialize the adapter
        this.adapterItems = new ArrayList<>();
        this.pageNumber = 0;


        PostExecutionThread postExecutionThread = MainThread.getInstance();
        ThreadExecutor threadExecutor  = BackgroundExecutor.getInstance();


        //todo add json serializer and some real cache, instead of a test one
        CartListWishListCache cartListWishListCache = TestCartListWishListCacheImpl.getInstance();


        CartListWishListDataStoreFactory dataStoreFactory = new CartListWishListDataStoreFactory(cartListWishListCache);
        CartListWishListEntityDataMapper entityDataMapper = new CartListWishListEntityDataMapper();

        CartListWishListRepository repository = CartListWishListDataRepository.getInstance(entityDataMapper,dataStoreFactory);


        GetWishListUseCase getWishListUseCase = new GetWishListUseCaseImpl(postExecutionThread,threadExecutor,repository);
        CartListWishListModelDataMapper modelMapper = new CartListWishListModelDataMapper();

        this.wishListPresenter = new WishListPresenter(this,getWishListUseCase,modelMapper);

    }

    private void resetRecyclerViewAdapter() {
        pageNumber = 0;
        wishListAdapter.clearAll();
        wishListAdapter.notifyDataSetChanged();
        endlessRecyclerViewScrollListener.resetState();
    }


    @Override
    public void renderWishList(int pageNumber, ArrayList<WishListModel> wishListModels) {

        System.out.println("PAGE NUMBER WISH "+pageNumber);
        if(pageNumber==0){
            this.resetRecyclerViewAdapter();

            wishListAdapter.addAll(wishListModels);
            wishListAdapter.notifyItemRangeInserted(0,wishListModels.size());
        }
        else{
            int before = wishListAdapter.getModelSize();

            wishListAdapter.addAll(wishListModels);

            int after = wishListAdapter.getModelSize();

            wishListAdapter.notifyItemRangeInserted(before,after-before);
        }
    }

    @Override
    public void hideWishList() {
        this.recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showWishList() {
        this.recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmpty() {
        this.emptyView.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty() {
        this.emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLikeButtonClicked(WishListModel wishListModel,int position) {

        this.wishListAdapter.removeAt(position);
        this.wishListAdapter.notifyItemRemoved(position);
        this.wishListAdapter.notifyItemRangeChanged(position,wishListAdapter.getItemCount()-position);
    }

    @Override
    public void onShopClicked(int shopId) {
        //chain to activity
        this.wishListListener.onShopClicked(shopId,getFragmentId());
    }

    @Override
    public void onProductClicked(int productId, int shopId) {
        //chain to activity
        this.wishListListener.onProductClicked(productId,shopId,getFragmentId());
    }

    @Override
    public void showLoading() {
        this.swipeRefreshLayout.setRefreshing(true);
        this.loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.swipeRefreshLayout.setRefreshing(false);
        this.loadingView.setVisibility(View.GONE);
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
        //todo do some awesome error messageing here -_-
    }

    @Override
    public void onResume() {
        super.onResume();
        this.wishListPresenter.onResume();
    }

    @Override
    public void onPause() {
        this.wishListPresenter.onPause();
        super.onPause();
    }
}
