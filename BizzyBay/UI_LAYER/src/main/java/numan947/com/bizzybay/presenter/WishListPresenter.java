package numan947.com.bizzybay.presenter;

import com.example.WishList;
import com.example.exception.ErrorBundle;
import com.example.interactor.GetWishListUseCase;

import java.util.ArrayList;

import numan947.com.bizzybay.mapper.CartListWishListModelDataMapper;
import numan947.com.bizzybay.model.WishListModel;
import numan947.com.bizzybay.view.WishListView;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class WishListPresenter implements Presenter {
    private final GetWishListUseCase getWishListUseCase;
    private final CartListWishListModelDataMapper modelMapper;
    private WishListView view;

    private final GetWishListUseCase.Callback createdCallback = new GetWishListUseCase.Callback() {
        @Override
        public void onWishListLoaded(int page, ArrayList<WishList> wishLists) {
            WishListPresenter.this.onWishListLoaded(page,wishLists);
        }

        @Override
        public void onError(ErrorBundle errorBundle) {
            WishListPresenter.this.onError(errorBundle);
        }
    };

    private void onError(ErrorBundle errorBundle) {
        this.hideEmpty();
        this.hideLoading();
        this.hideList();
        this.showRetry();
    }


    private void onWishListLoaded(int page, ArrayList<WishList> wishLists) {
        ArrayList<WishListModel>wishListModel = modelMapper.transformWishList(wishLists);

        this.hideRetry();
        this.hideLoading();
        this.renderList(page,wishListModel);
    }

    private void renderList(int page, ArrayList<WishListModel> wishListModel) {
        if(page==0&&wishListModel.size()==0){
            this.showList();
            this.showEmpty();
        }
        else{
            this.showList();
            view.renderWishList(page,wishListModel);
        }
    }



    public WishListPresenter(WishListView view, GetWishListUseCase getWishListUseCase, CartListWishListModelDataMapper modelMapper) {
        this.view = view;
        this.getWishListUseCase = getWishListUseCase;
        this.modelMapper = modelMapper;
    }


    public void onShopClicked(int shopId) {
        //chaining to view
        view.onShopClicked(shopId);
    }

    public void onProductClicked(int productId, int shopId) {
        //chaining to view
        view.onProductClicked(productId,shopId);
    }

    public void onLikeButtonClicked(WishListModel wishListModel, int position) {
        //todo do some network here
        view.onLikeButtonClicked(wishListModel,position);
    }

    public void initialize(int page) {
        this.hideRetry();
        this.hideList();
        this.hideEmpty();
        this.showLoading();
        this.loadProducts(page);
    }


    private void showEmpty() {
        view.showEmpty();
    }

    private void showList() {
        view.showWishList();
    }

    private void hideLoading() {
        view.hideLoading();
    }
    private void hideEmpty() {
        view.hideEmpty();
    }

    private void loadProducts(int page) {
        this.getWishListUseCase.getWishList(page,createdCallback);
    }

    private void showLoading() {
        view.showLoading();
    }

    private void hideList() {
        view.hideWishList();
    }

    private void hideRetry() {
        view.hideRetry();
    }

    private void showRetry() {
        view.showRetry();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

}
