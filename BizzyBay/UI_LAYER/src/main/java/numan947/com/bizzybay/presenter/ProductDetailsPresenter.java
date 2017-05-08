package numan947.com.bizzybay.presenter;

import com.example.DetailsProduct;
import com.example.exception.ErrorBundle;
import com.example.interactor.GetProductDetailsUseCase;

import numan947.com.bizzybay.mapper.ProductModelDataMapper;
import numan947.com.bizzybay.model.DetailsProductModel;
import numan947.com.bizzybay.view.ProductDetailsView;

/**
 * Created by numan947 on 5/7/17.
 */

public class ProductDetailsPresenter implements Presenter {

    //initializing parameters
    private GetProductDetailsUseCase getProductDetailsUseCase;
    private ProductModelDataMapper productModelDataMapper;
    private ProductDetailsView productDetailsView;


    //product loading parameters
    private int productId;
    private int shopId;

    //layer control parameters
    private final GetProductDetailsUseCase.Callback productDetailsCallback = new GetProductDetailsUseCase.Callback() {
        @Override
        public void onProductDetailsLoaded(DetailsProduct product) {
            DetailsProductModel model=ProductDetailsPresenter.this.productModelDataMapper.transform(product);
            ProductDetailsPresenter.this.renderProductDetails(model);
        }

        @Override
        public void onError(ErrorBundle errorBundle) {
            ProductDetailsPresenter.this.renderError(errorBundle);
        }
    };

    //method for rendering error to view
    private void renderError(ErrorBundle errorBundle) {
        productDetailsView.hideLoading();
        productDetailsView.hideDetailsView();
        productDetailsView.showRetry();
        productDetailsView.showError(errorBundle.getErrorMessage());
    }

    //method for rendering the loaded product to view
    private void renderProductDetails(DetailsProductModel product) {
        productDetailsView.hideRetry();
        productDetailsView.hideLoading();
        productDetailsView.showDetailsView();
        productDetailsView.renderProduct(product);
    }


    public ProductDetailsPresenter(ProductDetailsView productDetailsView, GetProductDetailsUseCase getProductDetailsUseCase, ProductModelDataMapper productModelDataMapper) {
        if(productDetailsView==null||getProductDetailsUseCase==null||productModelDataMapper==null)
            throw new IllegalArgumentException("Constructor parameters can't be NULL");

        this.getProductDetailsUseCase = getProductDetailsUseCase;
        this.productModelDataMapper = productModelDataMapper;
        this.productDetailsView = productDetailsView;
    }

    //method for initialize loading of products
    public void initialize(int productId, int shopId) {
        this.shopId = shopId;
        this.productId = productId;

        this.loadProductDetails();
    }

    //method for loading product details
    private void loadProductDetails() {
        this.hideProductDetailsView();
        this.hideRetry();
        this.showLoading();
        this.getProductDetails();
    }

    //self explanatory
    private void getProductDetails() {
        this.getProductDetailsUseCase.execute(this.productId,this.shopId,productDetailsCallback);
    }

    //self explanatory
    private void showLoading() {
        productDetailsView.showLoading();
    }

    //self explanatory
    private void hideRetry() {
        productDetailsView.hideRetry();
    }

    //self explanatory
    private void hideProductDetailsView() {
        productDetailsView.hideDetailsView();
    }

    @Override
    public void onResume() {
        //todo save states
    }

    @Override
    public void onPause() {
        //todo restore states
    }

    //self explanatory, method called from the view to pass control to controller
    public void onLikeButtonClicked(int productId, int shopId) {
        //todo some data layer action to save the product being liked

        //chain it to the view for update
        productDetailsView.showProductLiked(productId,shopId);
    }

    //self explanatory, method called from the view to pass control to controller
    public void onAddToCartButtonClicked(int productId, int shopId) {
        //todo some data layer action to save the product being liked


        //chain it to the view for update
        productDetailsView.showProductAddedToCart(productId,shopId);
    }

    //self explanatory, method called from the view to pass control to controller
    public void onBuyButtonClicked(int productId, int shopId) {
        //chain it to the view for update
        productDetailsView.buyProduct(productId,shopId);
    }

    //self explanatory, method called from the view to pass control to controller
    public void onShopLocationClicked(int shopId) {
        //chain it to the view for update
        productDetailsView.viewShopLocation(shopId);
    }

    //self explanatory, method called from the view to pass control to controller
    public void onShopNameClicked(int shopId) {
        //chain it to the view for update
        productDetailsView.viewShop(shopId);
    }
}
