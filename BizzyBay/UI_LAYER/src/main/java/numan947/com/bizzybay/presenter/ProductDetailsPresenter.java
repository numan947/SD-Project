package numan947.com.bizzybay.presenter;

import com.example.interactor.GetProductDetailsUseCase;

import numan947.com.bizzybay.mapper.ProductModelDataMapper;
import numan947.com.bizzybay.view.ProductDetailsView;

/**
 * Created by numan947 on 5/7/17.
 */

public class ProductDetailsPresenter implements Presenter {

    private GetProductDetailsUseCase getProductDetailsUseCase;
    private ProductModelDataMapper productModelDataMapper;
    ProductDetailsView productDetailsView;

    public ProductDetailsPresenter(ProductDetailsView productDetailsView, GetProductDetailsUseCase getProductDetailsUseCase, ProductModelDataMapper productModelDataMapper) {
        this.getProductDetailsUseCase = getProductDetailsUseCase;
        this.productModelDataMapper = productModelDataMapper;
        this.productDetailsView = productDetailsView;
    }

    public void initialize(int productId, int shopId) {

    }

    @Override
    public void onResume() {
        //todo save states
    }

    @Override
    public void onPause() {
        //todo restore states
    }
}
