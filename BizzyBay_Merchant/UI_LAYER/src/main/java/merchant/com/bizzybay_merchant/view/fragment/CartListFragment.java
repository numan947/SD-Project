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
import com.merchant_example.interactor.GetCartListUseCase;
import com.merchant_example.interactor.GetCartListUseCaseImpl;
import com.merchant_example.repository.CartListWishListRepository;

import java.util.ArrayList;

import merchant.com.bizzybay_merchant.MainThread;
import merchant.com.bizzybay_merchant.R;
import merchant.com.bizzybay_merchant.mapper.CartListWishListModelDataMapper;
import merchant.com.bizzybay_merchant.model.CartListModel;
import merchant.com.bizzybay_merchant.model.CartProductModel;
import merchant.com.bizzybay_merchant.presenter.CartListPresenter;
import merchant.com.bizzybay_merchant.view.CartListView;
import merchant.com.bizzybay_merchant.view.adapter.CartListAdapter;
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
        void onShopClicked(int shopId,String fragmentId);
        void onProductClicked(int productId,int shopId,String fragmentId);

        void onCheckoutButtonClicked(CartListModel cartListModel);
    }

    private CartListListener cartListListener;
    private CartListPresenter cartListPresenter;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout retryView;
    private RelativeLayout loadingView;
    private RelativeLayout emptyView;

    private TextView emptyText;
    private Button retryButton;


    private CartListAdapter cartListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;

    private CartListAdapter.Callback callBackForRecyclerViewAdapter = new CartListAdapter.Callback() {
        @Override
        public void onProductItemClicked(int productId, int shopId) {
            //chain to presenter
            cartListPresenter.onProductItemClicked(productId,shopId);
        }

        @Override
        public void onShopNameClicked(int shopId) {
            cartListPresenter.onShopNameClicked(shopId);
        }

        @Override
        public void onShopDeleteButtonClicked(int position) {
            cartListPresenter.onShopDeleteButtonClicked(position);
        }

        @Override
        public void onCheckOutButtonClicked(CartListModel cartListModel) {
            cartListPresenter.onCheckoutButtonClicked(cartListModel);
        }

        @Override
        public void onProductDeleteButtonClicked(CartListModel cartListModel, CartProductModel cartProduct,int position) {
            cartListPresenter.onProductDeleteButtonClicked(cartListModel,cartProduct,position);
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
        //todo: do we have anything to do here?
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
                System.out.println("HELLO WORLD");
                cartListPresenter.initialize(++pageNumber);
            }
        };


        this.cartListAdapter = new CartListAdapter(getContext(),adapterItems,callBackForRecyclerViewAdapter);


        this.recyclerView.addOnScrollListener(endlessRecyclerViewScrollListener);
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

        this.emptyView = (RelativeLayout)view.findViewById(R.id.rl_empty);
        this.emptyText = (TextView)view.findViewById(R.id.txt_empty);

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
        this.pageNumber = 0;

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

            this.resetRecyclerViewAdapter();

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
    public void showEmpty() {
        this.emptyText.setText("THE LIST IS EMPTY");
        this.emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmpty() {
        this.emptyView.setVisibility(View.GONE);
    }

    @Override
    public void onDeleteShopButtonClicked(int position) {
        //todo do some internet update from here using the presenter
        System.err.println("SHOP DELETE---POSITION................."+position);
        CartListModel cartListModel = cartListAdapter.removeAt(position);
        cartListAdapter.notifyItemRemoved(position);
        cartListAdapter.notifyItemRangeChanged(position,cartListAdapter.getItemCount()-position);



    }

    @Override
    public void onDeleteProductButtonClicked(CartListModel cartListModel,CartProductModel cartProductModel,int position) {
        //todo do some internet update from here using the presenter
        System.err.println("PRODUCT DELETE-----"+cartListModel.getShopName()+" "+cartProductModel.getProductName());

        //remove from the productModel from cartListModel so that it doesn't affect the whole checkout thingy
        cartListModel.getCartProductModels().remove(cartProductModel);


        cartListModel.setTotalPrice("1234"); //debug, checking if the total price can be set programmatically, result: it can be :)


        System.err.println("WHY IS THE SIZE ---- "+cartListModel.getCartProductModels().size());

        if(cartListModel.getCartProductModels().size()==0){
            //todo do some internet thingy here later


            cartListAdapter.removeAt(position);
            cartListAdapter.notifyItemRemoved(position);
            cartListAdapter.notifyItemRangeChanged(position,cartListAdapter.getItemCount()-position);
        }
        else
            cartListAdapter.notifyItemChanged(position,cartListModel);

    }


    @Override
    public void onCheckoutButtonClicked(CartListModel cartListModel) {
        //todo may be we need some internet action here???
        if(cartListModel.getCartProductModels().size()>0){ //explicit checking needed, so that our customer doesn't get an empty checkout page -_-

            cartListListener.onCheckoutButtonClicked(cartListModel);
        }
    }

    @Override
    public void onProductItemClicked(int shopId, int productId) {
        //chain to activity
        cartListListener.onProductClicked(productId,shopId,getFragmentId());
    }

    @Override
    public void onShopNameClicked(int shopId) {
        //chain to activity
        cartListListener.onShopClicked(shopId,getFragmentId());
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
