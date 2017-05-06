package numan947.com.bizzybay.presenter;

import com.example.Product;
import com.example.exception.ErrorBundle;
import com.example.interactor.GetProductListUseCase;

import java.util.ArrayList;
import java.util.Collection;

import numan947.com.bizzybay.mapper.ProductModelDataMapper;
import numan947.com.bizzybay.model.ListProductModel;
import numan947.com.bizzybay.view.ProductListView;

/**
 * Created by numan947 on 5/1/17.
 */

public class ProductListPresenter implements Presenter {

    private final ProductListView productListView;
    private final GetProductListUseCase getProductListUseCaseUseCase;
    private final ProductModelDataMapper productModelDataMapper;

    public ProductListPresenter(ProductListView productListView, GetProductListUseCase getProductListUseCaseUseCase, ProductModelDataMapper productModelDataMapper) {

        if(productListView==null|| getProductListUseCaseUseCase ==null||productModelDataMapper==null)
            throw  new IllegalArgumentException("Constructor parameters MUST not be null");

        this.productListView = productListView;
        this.getProductListUseCaseUseCase = getProductListUseCaseUseCase;
        this.productModelDataMapper = productModelDataMapper;
    }
    
    public void initialize()
    {
        this.loadProductList();
    }

    private void loadProductList() {
        this.hideRetryView();
        this.showLoadingView();
        this.getProductsList();
    }


    private void getProductsList() {
        this.getProductListUseCaseUseCase.execute(productListCallback);
    }

    private final GetProductListUseCase.Callback productListCallback = new GetProductListUseCase.Callback() {
        @Override
        public void onProductsListLoaded(Collection<Product> products) {
            ProductListPresenter.this.showProductsCollectionInView(products);
            ProductListPresenter.this.hideLoadingView();
        }

        @Override
        public void onError(ErrorBundle errorBundle) {
            ProductListPresenter.this.hideLoadingView();
            ProductListPresenter.this.showErrorMessage(errorBundle);
            ProductListPresenter.this.showRetryView();
        }
    };

    private void showErrorMessage(ErrorBundle errorBundle) {
        this.productListView.showError(errorBundle.getErrorMessage());
    }

    private void hideLoadingView() {
        this.productListView.hideLoading();
    }
    private void hideRetryView() {
        this.productListView.hideRetry();
    }

    private void showProductsCollectionInView(Collection<Product> products) {
        final ArrayList<ListProductModel> productModelCollection = this.productModelDataMapper.transform(products);
        this.productListView.renderProductList(productModelCollection);
    }

    private void showLoadingView() {
        this.productListView.showLoading();
    }

    private void showRetryView() {
        this.productListView.showRetry();
    }


    @Override
    public void onResume() {}

    @Override
    public void onPause() {}

    public void viewProduct(ListProductModel product) {
        //todo implement
        productListView.viewProduct(product);
    }

    public void likeProduct(ListProductModel model, int position) {
        //todo implement
        productListView.showProductLiked(model,position);
    }
}
