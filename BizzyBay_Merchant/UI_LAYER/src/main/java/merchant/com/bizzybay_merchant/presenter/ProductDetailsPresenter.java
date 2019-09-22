package merchant.com.bizzybay_merchant.presenter;

import com.merchant_example.ProductDetails;
import com.merchant_example.exception.ErrorBundle;
import com.merchant_example.interactor.GetProductDetailsUseCase;

import merchant.com.bizzybay_merchant.mapper.ProductModelDataMapper;
import merchant.com.bizzybay_merchant.model.ProductDetailsModel;
import merchant.com.bizzybay_merchant.view.ProductDetailsView;

/**
 *
 * @author numan947
 * @since 5/7/17.<br>
 *
 * Presenter implementation for the Product Details View.
 * It holds the View as a interface.
 *
 **/

public class ProductDetailsPresenter implements Presenter {

    //native elements that need to be maintained in the Presenter
    private GetProductDetailsUseCase getProductDetailsUseCase;
    private ProductModelDataMapper productModelDataMapper;
    private ProductDetailsView productDetailsView;


    //product loading parameters
    private int productId;
    private int shopId;

    /**
     * Callback provided to the use case so that it can callback once execution is finished.
     * */
    private final GetProductDetailsUseCase.Callback productDetailsCallback = new GetProductDetailsUseCase.Callback() {
        @Override
        public void onProductDetailsLoaded(ProductDetails product) {
            ProductDetailsModel model=ProductDetailsPresenter.this.productModelDataMapper.transform(product);
            ProductDetailsPresenter.this.renderProductDetails(model);
        }

        @Override
        public void onError(ErrorBundle errorBundle) {
            ProductDetailsPresenter.this.renderError(errorBundle);
        }
    };

    /**
     * Method for rendering error when the data loading is failed.
     * */
    private void renderError(ErrorBundle errorBundle) {
        productDetailsView.hideLoading();
        productDetailsView.hideDetailsView();
        productDetailsView.showRetry();
        productDetailsView.showError(errorBundle.getErrorMessage());
    }

    /**
     * Method for rendering Details when the data loading is OK.
     * */
    private void renderProductDetails(ProductDetailsModel product) {
        productDetailsView.hideRetry();
        productDetailsView.hideLoading();
        productDetailsView.showDetailsView();
        productDetailsView.renderProduct(product);
    }


    /**needs the view, the usecases related to
     * the view and the data-mapper reltated to
     * the result of the usecases*/
    public ProductDetailsPresenter(ProductDetailsView productDetailsView, GetProductDetailsUseCase getProductDetailsUseCase, ProductModelDataMapper productModelDataMapper) {
        if(productDetailsView==null||getProductDetailsUseCase==null||productModelDataMapper==null)
            throw new IllegalArgumentException("Constructor parameters can't be NULL");

        this.getProductDetailsUseCase = getProductDetailsUseCase;
        this.productModelDataMapper = productModelDataMapper;
        this.productDetailsView = productDetailsView;
    }

    /**
     * Method for initializing the data load inside the presenter.
     * */
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

    /** method called from
     * the view to pass control to controller*/
    public void onLikeButtonClicked(ProductDetailsModel model) {
        //todo some data layer action to save the product being liked

        //changing button state
        model.setLiked(!model.isLiked());
        productDetailsView.showProductLiked(model);
    }

    /** method called from
     * the view to pass control to controller*/
    public void onAddToCartButtonClicked(ProductDetailsModel model) {
        //todo some data layer action to save the product being liked


        //changing button state
        model.setCarted(!model.isCarted());
        productDetailsView.showProductAddedToCart(model);
    }

    /** method called from
     * the view to pass control to controller*/
    public void onBuyButtonClicked(int productId, int shopId) {
        //chain it to the view for update
        productDetailsView.buyProduct(productId,shopId);
    }

    /**method called from
     * the view to pass control to controller*/
    public void onShopLocationClicked(int shopId) {
        //chain it to the view for update
        productDetailsView.viewShopLocation(shopId);
    }

    /**method called from
     * the view to pass control to controller*/
    public void onShopNameClicked(int shopId) {
        //chain it to the view for update
        productDetailsView.viewShop(shopId);
    }

    /**
     *Method called from the view to pass the control to controller
     */
    public void onCategorySelected(String category){
        productDetailsView.viewProductsByCategory(category);
    }
}
