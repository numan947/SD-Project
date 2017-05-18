package numan947.com.bizzybay.presenter;

import com.example.ShopDetails;
import com.example.exception.ErrorBundle;
import com.example.interactor.GetShopDetailsUseCase;

import numan947.com.bizzybay.mapper.ShopModelDataMapper;
import numan947.com.bizzybay.model.ShopDetailsModel;
import numan947.com.bizzybay.view.ShopDetailsView;

/**
 * @author numan947
 * @since 5/16/17.<br>
 **/

public class ShopDetailsPresenter implements Presenter {

    private ShopModelDataMapper shopModelDataMapper;
    private ShopDetailsView shopDetailsView;
    private GetShopDetailsUseCase getShopDetailsUseCase;
    private int shopId;


    private GetShopDetailsUseCase.Callback createdCallback = new GetShopDetailsUseCase.Callback() {
        @Override
        public void OnShopDetailsLoaded(ShopDetails shopDetails) {
            ShopDetailsPresenter.this.renderShopDetails(shopDetails);
        }

        @Override
        public void OnError(ErrorBundle errorBundle) {
            ShopDetailsPresenter.this.renderError(errorBundle);
        }
    };

    private void renderError(ErrorBundle errorBundle) {
        this.hideLoadingView();
        this.hideDetailsView();
        this.showRetryView();
    }

    private void renderShopDetails(ShopDetails shopDetails) {
        ShopDetailsModel shopDetailsModel = shopModelDataMapper.transform(shopDetails);
        shopDetailsView.renderShopDetails(shopDetailsModel);


        this.hideLoadingView();
        this.hideRetryView();
        this.showDetailsView();


    }

    public ShopDetailsPresenter(ShopDetailsView shopDetailsView, GetShopDetailsUseCase getShopDetailsUseCase, ShopModelDataMapper shopModelDataMapper) {

        if(shopDetailsView==null||shopModelDataMapper==null||getShopDetailsUseCase==null)
            throw new IllegalArgumentException("WHAT THE FUCK IS WRONG WITH YOU? ARE YOU DRUNK?");

        this.shopModelDataMapper = shopModelDataMapper;
        this.shopDetailsView = shopDetailsView;
        this.getShopDetailsUseCase = getShopDetailsUseCase;
    }



    public void initialize(int shopId) {
        this.shopId=shopId;

        this.loadShopDetails();
    }

    private void loadShopDetails() {
        this.hideDetailsView();
        this.hideRetryView();
        this.showLoadingView();
        this.getShopDetails();
    }

    private void getShopDetails() {
        this.getShopDetailsUseCase.execute(this.shopId,createdCallback);
    }

    private void hideLoadingView(){
        this.shopDetailsView.hideLoading();
    }
    private void showLoadingView(){
        this.shopDetailsView.showLoading();
    }
    private void showRetryView(){
        this.shopDetailsView.showRetry();
    }
    private void hideRetryView() {
        this.shopDetailsView.hideRetry();
    }
    private void showDetailsView(){
        this.shopDetailsView.showShopDetails();
    }
    private void hideDetailsView() {
        shopDetailsView.hideShopDetails();
    }

    @Override
    public void onResume() {
        //todo do we really need these
    }

    @Override
    public void onPause() {
        //todo do we really need these
    }
}
