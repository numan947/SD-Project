package numan947.com.bizzybay.presenter;

import com.example.ShopList;
import com.example.exception.ErrorBundle;
import com.example.interactor.GetShopListUseCase;

import java.util.ArrayList;

import numan947.com.bizzybay.mapper.ShopModelDataMapper;
import numan947.com.bizzybay.model.ShopListModel;
import numan947.com.bizzybay.view.ShopListView;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public class ShopListPresenter implements Presenter {
    private ShopListView shopListView;
    private GetShopListUseCase getShopListUseCase;
    private ShopModelDataMapper shopModelDataMapper;

    private GetShopListUseCase.Callback createdCallback = new GetShopListUseCase.Callback() {
        @Override
        public void onShopListLoaded(int pageNumber, ArrayList<ShopList> shopList) {
            ShopListPresenter.this.renderLoadedItems(pageNumber,shopList);
        }

        @Override
        public void onError(ErrorBundle errorBundle) {
            ShopListPresenter.this.renderError(errorBundle);
        }
    };

    private void renderError(ErrorBundle errorBundle) {
        this.hideLoading();
        this.hideListView();
        this.showRetry();
    }

    private void showRetry() {
        shopListView.showRetry();
    }


    private void renderLoadedItems(int pageNumber, ArrayList<ShopList> shopList) {
        this.hideRetry();
        this.hideLoading();
        this.showListView();
        ArrayList<ShopListModel>shopListModel = shopModelDataMapper.transform(shopList);
        shopListView.renderList(pageNumber,shopListModel);
    }

    private void showListView() {
        this.shopListView.showListView();
    }

    private void hideLoading() {
        this.shopListView.hideLoading();
    }


    public ShopListPresenter(ShopListView shopListView, GetShopListUseCase getShopListUseCase, ShopModelDataMapper shopModelDataMapper) {
        this.shopListView = shopListView;
        this.getShopListUseCase = getShopListUseCase;
        this.shopModelDataMapper = shopModelDataMapper;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    public void OnListItemClicked(int shopId) {
        //chain to fragment
        this.shopListView.viewShop(shopId);
    }

    public void initialize(int pageNumber) {
        if(pageNumber==0){
            this.hideRetry();
            this.hideListView();
            this.shoLoading();

        }
        this.loadData(pageNumber);

    }

    private void loadData(int pageNumber) {
        this.getShopListUseCase.execute(pageNumber,createdCallback);
    }

    private void shoLoading() {
        shopListView.showLoading();
    }

    private void hideListView() {
        shopListView.hideListView();
    }


    private void hideRetry() {
        shopListView.hideRetry();
    }
}
