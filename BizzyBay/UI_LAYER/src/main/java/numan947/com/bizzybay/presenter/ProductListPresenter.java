package numan947.com.bizzybay.presenter;

import com.example.ProductList;
import com.example.exception.ErrorBundle;
import com.example.interactor.GetProductListUseCase;

import java.util.ArrayList;
import java.util.Collection;

import numan947.com.bizzybay.mapper.ProductModelDataMapper;
import numan947.com.bizzybay.model.ProductListModel;
import numan947.com.bizzybay.view.ProductListView;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * Presenter implementation for the Product List View.
 * It holds the View as a interface.
 *
 **/

public class ProductListPresenter implements Presenter {

    //native elements that need to be maintained in the Presenter
    private final ProductListView productListView;
    private final GetProductListUseCase getProductListUseCaseUseCase;
    private final ProductModelDataMapper productModelDataMapper;


    /**needs the view, the usecases related to
     * the view and the data-mapper reltated to
     * the result of the usecases*/
    public ProductListPresenter(ProductListView productListView, GetProductListUseCase getProductListUseCaseUseCase, ProductModelDataMapper productModelDataMapper) {

        if(productListView==null|| getProductListUseCaseUseCase ==null||productModelDataMapper==null)
            throw  new IllegalArgumentException("Constructor parameters MUST not be null");

        this.productListView = productListView;
        this.getProductListUseCaseUseCase = getProductListUseCaseUseCase;
        this.productModelDataMapper = productModelDataMapper;
    }

    /**
     * The method available to the view to use the UseCase.
     * Takes a pageNumber and returns the content of the page
     * */
    public void initialize(int pageNumber,int shopId)
    {
        this.loadProductList(pageNumber,shopId);
    }

    /**
     * Method for performing data loading
     *  @param pageNumber The Page to load
     *
     * @param shopId*/
    private void loadProductList(int pageNumber, int shopId) {
        if(pageNumber==0) {
            this.hideRetryView();
            this.showLoadingView();
        }
        this.getProductsList(pageNumber,shopId);
    }


    /**
     * Method for executing the use case from inside the presenter
     *  @param pageNumber The page to load
     * @param shopId*/
    private void getProductsList(int pageNumber, int shopId) {
        this.getProductListUseCaseUseCase.execute(pageNumber,shopId,productListCallback);
    }

    /**
     * Field that's provided to the usecase, so that the usecase can callback to this presenter
     * */
    private final GetProductListUseCase.Callback productListCallback = new GetProductListUseCase.Callback() {
        @Override
        public void onProductsListLoaded(int pageNumber, Collection<ProductList> productLists) {
            ProductListPresenter.this.showProductsCollectionInView(pageNumber, productLists);
            ProductListPresenter.this.hideLoadingView();
            ProductListPresenter.this.hideRetryView();
        }

        @Override
        public void onError(ErrorBundle errorBundle) {
            ProductListPresenter.this.hideLoadingView();
            ProductListPresenter.this.showErrorMessage(errorBundle);
            ProductListPresenter.this.showRetryView();
        }
    };

    /**
     * What to do when data loading is failed.
     * */
    private void showErrorMessage(ErrorBundle errorBundle) {
        this.productListView.showError(errorBundle.getErrorMessage());
    }
    /**Self Explanatory*/
    private void hideLoadingView() {
        this.productListView.hideLoading();
    }
    /**Self Explanatory*/
    private void hideRetryView() {
        this.productListView.hideRetry();
    }
    /**Self Explanatory*/
    private void showLoadingView() {
        this.productListView.showLoading();
    }
    /**Self Explanatory*/
    private void showRetryView() {
        this.productListView.showRetry();
    }

    /**Shows the loaded data to the view using one
     *  of the methods in the {@link ProductListView}*/
    private void showProductsCollectionInView(int pageNumber, Collection<ProductList> productLists) {

        final ArrayList<ProductListModel> productModelCollection = this.productModelDataMapper.transform(productLists);
        this.productListView.renderProductList(pageNumber,productModelCollection);
    }




    @Override
    public void onResume() {
        //todo restore saves here

    }

    @Override
    public void onPause() {
        //todo do saves here
    }

    /**
     * Method is called from the View to signal the presenter that an item is clicked
     * */
    public void viewProduct(ProductListModel product) {
        productListView.viewProduct(product);
    }

    public void likeProduct(ProductListModel model, int position) {
        //todo send server data about the like

        model.setLiked(!model.isLiked());
        productListView.showProductLiked(model,position);
    }
}
