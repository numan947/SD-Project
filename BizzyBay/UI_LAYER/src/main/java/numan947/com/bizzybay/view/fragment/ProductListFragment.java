package numan947.com.bizzybay.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.interactor.BrowseProducts;
import com.example.interactor.BrowseProductsImpl;
import com.example.repository.ProductRepository;

import java.util.Collection;

import numan947.com.bizzybay.MainThread;
import numan947.com.bizzybay.R;
import numan947.com.bizzybay.mapper.ProductModelDataMapper;
import numan947.com.bizzybay.model.ProductModelMultiple;
import numan947.com.bizzybay.presenter.ProductListPresenter;
import numan947.com.bizzybay.view.ProductListView;
import numan947.com.bizzybay.view.adapter.ProductListAdapter;
import numan947.com.data_layer.cache.ProductCache;
import numan947.com.data_layer.cache.TestProductCacheImpl;
import numan947.com.data_layer.entity.mapper.ProductEntityDataMapper;
import numan947.com.data_layer.executor.BackgroundExecutor;
import numan947.com.data_layer.repository.ProductDataRepository;
import numan947.com.data_layer.repository.datasource.ProductDataStoreFactory;

/**
 * Created by numan947 on 5/1/17.
 */

public class ProductListFragment extends RecyclerViewBaseFragment implements ProductListView {


    private ProductListPresenter productListPresenter;

    private RelativeLayout rl_progress;
    private RelativeLayout rl_retry;
    private Button bt_retry;

    private ProductListAdapter productListAdapter;



    public static ProductListFragment newInstance()
    {
        ProductListFragment productListFragment = new ProductListFragment();
        return productListFragment;
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.browse_product_fragment,container,false);

        //binding views
        RecyclerView rv = (RecyclerView) v.findViewById(R.id.browse_products_recycler_view);
        rl_progress = (RelativeLayout) v.findViewById(R.id.rl_progress);
        rl_retry = (RelativeLayout) v.findViewById(R.id.rl_retry);
        bt_retry = (Button) v.findViewById(R.id.bt_retry);

        //todo change for endless list
        rv.setHasFixedSize(true);

        super.setRecyclerView(rv);

        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        productListPresenter.initialize();
    }

    @Override
    public void onResume() {
        super.onResume();
        productListPresenter.onResume();
    }

    @Override
    public void onPause() {
        productListPresenter.onPause();
        super.onPause();
    }


    @Override
    protected void initializePresenter() {
        ThreadExecutor threadExecutor = BackgroundExecutor.newInstance();
        PostExecutionThread postExecutionThread = MainThread.newInstance();

        //todo initialize JSON Serializer later

        ProductCache productCache = new TestProductCacheImpl();//todo change it later

        ProductDataStoreFactory productDataStoreFactory = new ProductDataStoreFactory(getContext(),productCache);

        ProductEntityDataMapper productEntityDataMapper = new ProductEntityDataMapper();

        ProductRepository productRepository = ProductDataRepository.newInstance(productDataStoreFactory,productEntityDataMapper);

        BrowseProducts browseProductsUseCase = new BrowseProductsImpl(productRepository,threadExecutor,postExecutionThread);
        ProductModelDataMapper productModelDataMapper = new ProductModelDataMapper();


        this.productListPresenter = new ProductListPresenter(this,browseProductsUseCase,productModelDataMapper);
    }

    @Override
    public void renderProductList(Collection<ProductModelMultiple> products) {
        if(products!=null) {
            //todo
            //this.productListAdapter = new ProductListAdapter(getContext(), getActivity().getLayoutInflater(), );

        }
    }

    @Override
    public void viewProduct(ProductModelMultiple product) {

    }

    @Override
    public void showProductAddedToCart(ProductModelMultiple product) {

    }

    @Override
    public void showProductAddedToWishList(ProductModelMultiple product) {

    }

    @Override
    public void buyProduct(ProductModelMultiple product) {

    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {

    }
}
