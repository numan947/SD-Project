package numan947.com.bizzybay.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.interactor.GetProductListUseCase;
import com.example.interactor.GetProductListUseCaseImpl;
import com.example.repository.ProductRepository;

import java.util.ArrayList;

import numan947.com.bizzybay.BizzyBay;
import numan947.com.bizzybay.MainThread;
import numan947.com.bizzybay.R;
import numan947.com.bizzybay.mapper.ProductModelDataMapper;
import numan947.com.bizzybay.model.ListProductModel;
import numan947.com.bizzybay.presenter.ProductListPresenter;
import numan947.com.bizzybay.view.ProductListView;
import numan947.com.bizzybay.view.activity.MainActivity;
import numan947.com.bizzybay.view.adapter.ProductListAdapter;
import numan947.com.data_layer.cache.ProductCache;
import numan947.com.data_layer.cache.TestProductCacheImpl;
import numan947.com.data_layer.entity.mapper.ProductEntityDataMapper;
import numan947.com.data_layer.executor.BackgroundExecutor;
import numan947.com.data_layer.repository.ProductDataRepository;
import numan947.com.data_layer.repository.datasource.ProductDataStoreFactory;

/**
 * Created by numan947 on 5/6/17.
 */

public class ProductListFragment extends BaseFragment implements View.OnClickListener,ProductListView {


    //this is the interface to send data to Activity so that it can change fragment or switch Activity
    public interface ProductListListener{
        void onProductClicked(final ListProductModel model);
    }


    //the mandatory presenter and related listener to pass data to presenter
    private ProductListPresenter productListPresenter;
    private ProductListListener productListListener;
    private ProductListAdapter adapter;


    //the views related to the view
    private RecyclerView recyclerView;
    private Button retry_button;
    private RelativeLayout rl_retry;
    private RelativeLayout rl_progress;



    public static ProductListFragment newInstance()
    {
        ProductListFragment fragment = new ProductListFragment();

        return fragment;
    }



    private final ProductListAdapter.Callback adapterCallback = new ProductListAdapter.Callback() {
        @Override
        public void OnLikedButtonClicked(ListProductModel model,int position) {
            productListPresenter.likeProduct(model,position);
        }

        @Override
        public void OnCardViewClicked(ListProductModel model,int position) {
            productListPresenter.viewProduct(model);
        }
    };



    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bt_retry){
            // retry load
            this.productListPresenter.initialize();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //MainActivity should implement this one
        if(context instanceof MainActivity)
            this.productListListener=((ProductListListener) context);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.list_product_fragment,container,false);

        recyclerView = (RecyclerView) v.findViewById(R.id.list_product_fragment_recycler_view);
        retry_button = (Button)v.findViewById(R.id.bt_retry);
        rl_progress = (RelativeLayout)v.findViewById(R.id.rl_progress);
        rl_retry = (RelativeLayout)v.findViewById(R.id.rl_retry);

        rl_retry.setOnClickListener(this);

        this.setupRecyclerView();
        return v;
    }

    private void setupRecyclerView() {
        //initialize the adapter with dummy list, that'll act as container for the product models
        adapter = new ProductListAdapter(getContext(), adapterCallback,new ArrayList<ListProductModel>());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.recyclerView.setAdapter(adapter);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //this is where presenter tries to load data
        if(productListPresenter==null)
            initializePresenter();

        productListPresenter.initialize();
    }

    @Override
    protected void initializePresenter() {
        //initializing the presenter

        ThreadExecutor threadExecutor = BackgroundExecutor.getInstance();
        PostExecutionThread postExecutionThread = MainThread.getInstance();

        //todo add JSON Serializer
        //todo add ProductCache just like shown in the example

        ProductCache productCache = TestProductCacheImpl.getInstance();


        ProductDataStoreFactory productDataStoreFactory = new ProductDataStoreFactory(BizzyBay.getBizzyBayApplicationContext(),productCache);
        ProductEntityDataMapper productEntityDataMapper = new ProductEntityDataMapper();
        ProductRepository productRepository = ProductDataRepository.getInstance(productDataStoreFactory,productEntityDataMapper);

        GetProductListUseCase getProductListUseCase = new GetProductListUseCaseImpl(productRepository,threadExecutor,postExecutionThread);
        ProductModelDataMapper productModelDataMapper = new ProductModelDataMapper();


        this.productListPresenter = new ProductListPresenter(this,getProductListUseCase,productModelDataMapper);

    }


    @Override
    public void onResume() {
        super.onResume();
        productListPresenter.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //todo save instance
    }

    @Override
    public void onPause() {
        productListPresenter.onPause();
        super.onPause();
    }


    @Override
    public void renderProductList(ArrayList<ListProductModel> products) {
        if(products!=null){
            adapter.addAll(products);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void viewProduct(ListProductModel product) {
        productListListener.onProductClicked(product);
    }


    @Override
    public void showProductLiked(ListProductModel productModel, int position) {
        //reload the adapter here
        adapter.notifyItemChanged(position,productModel);
    }

    @Override
    public void showLoading() {
        rl_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        rl_progress.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        //todo do error handle later
        this.showToastMessage(message);
    }


}
