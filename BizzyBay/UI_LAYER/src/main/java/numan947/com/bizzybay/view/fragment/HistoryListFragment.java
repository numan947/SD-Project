package numan947.com.bizzybay.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
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
import com.example.interactor.GetHistoryListUseCase;
import com.example.interactor.GetHistoryListUseCaseImpl;
import com.example.repository.HistoryRepository;

import java.util.ArrayList;

import numan947.com.bizzybay.BizzyBay;
import numan947.com.bizzybay.MainThread;
import numan947.com.bizzybay.R;
import numan947.com.bizzybay.mapper.HistoryModelDataMapper;
import numan947.com.bizzybay.model.HistoryListModel;
import numan947.com.bizzybay.presenter.HistoryListPresenter;
import numan947.com.bizzybay.view.HistoryListView;
import numan947.com.bizzybay.view.adapter.HistoryListAdapter;
import numan947.com.bizzybay.view.component.EndlessRecyclerViewScrollListener;
import numan947.com.data_layer.cache.HistoryCache;
import numan947.com.data_layer.cache.TestHistoryCacheImpl;
import numan947.com.data_layer.entity.mapper.HistoryEntityDataMapper;
import numan947.com.data_layer.executor.BackgroundExecutor;
import numan947.com.data_layer.repository.HistoryDataRepository;
import numan947.com.data_layer.repository.datasource.HistoryDataStoreFactory;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

@SuppressWarnings("FieldCanBeLocal")
public class HistoryListFragment extends BaseFragment implements HistoryListView {

    public interface HistoryListListener{
        void onHistoryItemClicked(int orderId,int shopId,int productId);
    }

    private CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RelativeLayout loadingView;
    private RelativeLayout retryView;
    private Button retryButton;



    private HistoryListPresenter historyListPresenter;
    private HistoryListListener historyListListener;
    private HistoryListAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;



    /**
     * This is the callback implementation provided to the adapter of the
     * {@link RecyclerView}, so that it can chain the event handling to this fragment.
     * */
    private final HistoryListAdapter.Callback adapterCallback = new HistoryListAdapter.Callback() {

        @Override
        public void onItemClicked(int orderId, int shopId, int productId) {
            historyListPresenter.onItemClicked(orderId,shopId,productId);
        }
    };


    public static HistoryListFragment newInstance()
    {
        //todo add viable parameters here
        return new HistoryListFragment();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof HistoryListListener)
            this.historyListListener = (HistoryListListener)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.history_list_fragment,container,false);

        this.bindAll(v);
        this.setupRecyclerView();
        this.addListenersToView();
        this.setupSwipeRefreshView();

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState!=null)this.restoreSavedStates(savedInstanceState);
        this.getParameters();

        if(historyListPresenter==null)
            initializePresenter();

        historyListPresenter.initialize(0);
    }

    private void getParameters() {
        Bundle b = getArguments();
        if(b!=null){
            //todo get the passed arguments here
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //todo save states here

    }

    private void restoreSavedStates(Bundle savedInstanceState) {
        //todo restore states here
    }


    private void setupSwipeRefreshView() {

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                historyListPresenter.initialize(0);
            }
        });

    }

    private void addListenersToView() {

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //again try to load the first page
                historyListPresenter.initialize(0);
            }
        });

    }

    private void setupRecyclerView() {

        adapter = new HistoryListAdapter(getContext(),new ArrayList<HistoryListModel>(),adapterCallback);
        linearLayoutManager  = new LinearLayoutManager(getContext());
        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                //todo implement later
            }
        };


        this.resetRecyclerViewAdapter();


        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(endlessRecyclerViewScrollListener);
        recyclerView.setAdapter(adapter);
    }

    private void resetRecyclerViewAdapter() {
        this.adapter.clearAll();
        adapter.notifyDataSetChanged();
        endlessRecyclerViewScrollListener.resetState();
    }

    private void bindAll(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.history_list_fragment_recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.history_page_fragment_SRL);
        coordinatorLayout = (CoordinatorLayout)v.findViewById(R.id.history_page_fragment_CL);
        loadingView = (RelativeLayout)v.findViewById(R.id.rl_progress);
        retryView = (RelativeLayout) v.findViewById(R.id.rl_retry);
        retryButton = (Button)v.findViewById(R.id.bt_retry);
    }

    @Override
    protected void initializePresenter() {
        //todo
        PostExecutionThread postExecutionThread = MainThread.getInstance();
        ThreadExecutor threadExecutor = BackgroundExecutor.getInstance();


        //todo add JSON Parser here plus other things, like a real cache instead of a test one

        HistoryCache historyCache = TestHistoryCacheImpl.getInstance();


        HistoryDataStoreFactory historyDataStoreFactory = new HistoryDataStoreFactory(BizzyBay.getBizzyBayApplicationContext(),historyCache);
        HistoryEntityDataMapper historyEntityDataMapper = new HistoryEntityDataMapper();

        HistoryRepository historyRepository = HistoryDataRepository.getInstance(historyDataStoreFactory,historyEntityDataMapper);

        GetHistoryListUseCase getHistoryListUseCase = new GetHistoryListUseCaseImpl(historyRepository,postExecutionThread,threadExecutor);

        HistoryModelDataMapper historyModelDataMapper = new HistoryModelDataMapper();


        this.historyListPresenter = new HistoryListPresenter(this,getHistoryListUseCase,historyModelDataMapper);

    }

    @Override
    public void showLoading() {
        recyclerView.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRetry() {
        swipeRefreshLayout.setRefreshing(false);
        retryView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        retryView.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        //todo show error here
    }

    @Override
    public void renderHistoryList(int pageNumber, ArrayList<HistoryListModel> historyList) {
        if(pageNumber==0){
            resetRecyclerViewAdapter();
            adapter.addAll(historyList);
            adapter.notifyItemRangeInserted(0,historyList.size());
        }
        else{
            int before = adapter.getModelSize();
            adapter.addAll(historyList);
            int after = adapter.getModelSize();
            adapter.notifyItemRangeInserted(before,after-before);
        }
    }

    @Override
    public void viewProductHistory(int orderId, int shopId,int productId) {
        //pass to the activity
        historyListListener.onHistoryItemClicked(orderId,shopId,productId);
    }

    @Override
    public void hideList() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showList() {
        recyclerView.setVisibility(View.VISIBLE);
    }


    @Override
    public void onResume() {
        //todo do something here may be?
        super.onResume();
        historyListPresenter.onResume();
    }

    @Override
    public void onPause() {
        //todo do something here may be?
        historyListPresenter.onPause();
        super.onPause();

    }
}
