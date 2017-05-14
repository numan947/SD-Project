package numan947.com.bizzybay.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.interactor.GetShopListUseCase;
import com.example.interactor.GetShopListUseCaseImpl;
import com.example.repository.ShopRepository;

import java.util.ArrayList;

import numan947.com.bizzybay.BizzyBay;
import numan947.com.bizzybay.MainThread;
import numan947.com.bizzybay.R;
import numan947.com.bizzybay.mapper.ShopModelDataMapper;
import numan947.com.bizzybay.model.ShopListModel;
import numan947.com.bizzybay.presenter.ShopListPresenter;
import numan947.com.bizzybay.view.ShopListView;
import numan947.com.bizzybay.view.adapter.ShopListAdapter;
import numan947.com.bizzybay.view.component.EndlessAbsListViewScrollListener;
import numan947.com.data_layer.cache.ShopCache;
import numan947.com.data_layer.cache.TestShopCacheImpl;
import numan947.com.data_layer.entity.mapper.ShopEntityDataMapper;
import numan947.com.data_layer.executor.BackgroundExecutor;
import numan947.com.data_layer.repository.ShopDataRepository;
import numan947.com.data_layer.repository.datasource.ShopDataStoreFactory;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public class ShopListFragment extends BaseFragment implements ShopListView {
    private static final String fragmentId = "numan947.com.bizzybay.view.fragment.SHOP_LIST_VIEW_FRAGMENT";

    public ShopListFragment() {
    }


    //implemented by the activity
    public interface ShopListListener{
        void onShopListItemClicked(int shopId);
    }


    private ArrayList<ShopListModel>shopListModels;
    private int pageNumber;
    private ShopListPresenter shopListPresenter;
    private ShopListListener shopListListener;

    //views
    private ShopListAdapter shopListAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView shopListView;
    private RelativeLayout retryView;
    private RelativeLayout loadingView;
    private Button retryButton;



    private ShopListAdapter.Callback createdCallback = new ShopListAdapter.Callback() {
        @Override
        public void onListItemClicked(int shopId) {
            ShopListFragment.this.shopListPresenter.OnListItemClicked(shopId);
        }
    };

    private EndlessAbsListViewScrollListener endLessListener ;



    public static ShopListFragment newInstance()
    {
        //todo add additional parameters may be
        return new ShopListFragment();
    }
    public static String getFragmentId()
    {
        return fragmentId;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof ShopListListener)
            this.shopListListener = (ShopListListener) context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_list_fragment,container,false);

        this.bindAll(view);
        this.setupListView();
        this.addAllListeners();

        return view;
    }

    private void addAllListeners() {
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopListFragment.this.shopListPresenter.initialize(0); //retry loading
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ShopListFragment.this.shopListPresenter.initialize(0); //load again
                endLessListener.reset();
            }
        });
    }

    private void setupListView() {

        this.shopListAdapter = new ShopListAdapter(createdCallback,shopListModels,getContext());
        this.shopListView.setAdapter(shopListAdapter);

        endLessListener = new EndlessAbsListViewScrollListener(swipeRefreshLayout) {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                shopListPresenter.initialize(++pageNumber);
                return true;
            }
        };

        this.shopListView.setOnScrollListener(endLessListener);


    }

    private void bindAll(View view) {
        this.shopListView = (ListView) view.findViewById(R.id.shop_list_fragment_listview);
        this.swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.shop_list_fragment_SRL);
        this.retryButton = (Button)view.findViewById(R.id.bt_retry);
        this.retryView = (RelativeLayout)view.findViewById(R.id.rl_retry);
        this.loadingView = (RelativeLayout)view.findViewById(R.id.rl_progress);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState==null){
            shopListPresenter.initialize(0); //first time initialize
        }//else do nothing, adapter takes care of the view init
    }

    @Override
    protected void initializePresenter() {
        //todo initialze
        this.shopListModels = new ArrayList<>();

        PostExecutionThread postExecutionThread  = MainThread.getInstance();
        ThreadExecutor backgroundExecutor = BackgroundExecutor.getInstance();

        //todo add json mapper,serializer here and setup it accordingly

        ShopCache shopCache = TestShopCacheImpl.getInstance();

        ShopEntityDataMapper shopEntityDataMapper= new ShopEntityDataMapper();
        ShopDataStoreFactory  shopDataStoreFactory  = new ShopDataStoreFactory(BizzyBay.getBizzyBayApplicationContext(),shopCache);


        ShopRepository shopRepository = ShopDataRepository.getInstance(shopDataStoreFactory,shopEntityDataMapper);


        GetShopListUseCase getShopListUseCase = new GetShopListUseCaseImpl(backgroundExecutor,postExecutionThread,shopRepository);

        ShopModelDataMapper shopModelDataMapper = new ShopModelDataMapper();

        this.shopListPresenter = new ShopListPresenter(this,getShopListUseCase,shopModelDataMapper);

    }

    @Override
    public void viewShop(int shopId) {
        //chained to activity
        shopListListener.onShopListItemClicked(shopId);
    }

    @Override
    public void renderList(int page, ArrayList<ShopListModel> shopListModels) {
        //-1 means end of list, no more data to load, may be we'll add some other view here

        if(page==0){
            shopListAdapter.clear();
        }

        if(page!=-1){
            this.pageNumber = page;
            shopListAdapter.addAll(shopListModels);
            shopListAdapter.notifyDataSetChanged();
        }
        else{
            //do nothing it means end of the list
        }

        System.out.println("PAGE NUMBER "+pageNumber);
    }

    @Override
    public void hideListView() {
        shopListView.setVisibility(View.GONE);
    }

    @Override
    public void showListView() {
        shopListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        //loadingView.setVisibility(View.VISIBLE);
        this.swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        //loadingView.setVisibility(View.GONE);
        this.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showRetry() {
        retryView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideRetry() {
        retryView.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        //todo show awesome(!) error message
    }
}
