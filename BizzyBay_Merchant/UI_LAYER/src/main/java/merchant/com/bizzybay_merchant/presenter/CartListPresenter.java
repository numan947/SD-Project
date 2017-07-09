package merchant.com.bizzybay_merchant.presenter;

import com.merchant_example.CartList;
import com.merchant_example.exception.ErrorBundle;
import com.merchant_example.interactor.GetCartListUseCase;

import java.util.ArrayList;

import merchant.com.bizzybay_merchant.mapper.CartListWishListModelDataMapper;
import merchant.com.bizzybay_merchant.model.CartListModel;
import merchant.com.bizzybay_merchant.model.CartProductModel;
import merchant.com.bizzybay_merchant.view.CartListView;

/**
 * @author numan947
 * @since 5/20/17.<br>
 **/

public class CartListPresenter implements Presenter {
    private CartListView cartListView;
    private GetCartListUseCase getCartListUseCase;
    private CartListWishListModelDataMapper modelDataMapper;

    private GetCartListUseCase.Callback createdCallback = new GetCartListUseCase.Callback() {
        @Override
        public void onCartListLoaded(int page, ArrayList<CartList> cartList) {
            ArrayList<CartListModel>cartListModels = modelDataMapper.transformCartList(cartList);
            CartListPresenter.this.successfulLoad(page,cartListModels);
        }

        @Override
        public void onError(ErrorBundle errorBundle) {
            CartListPresenter.this.failedLoad(errorBundle);
        }
    };

    private void failedLoad(ErrorBundle errorBundle) {
        this.showRetryView();
        this.hideListView();
        this.hideEmptyView();
        this.hideLoadingView();
        this.cartListView.showError(errorBundle.getErrorMessage());
    }



    private void successfulLoad(int page, ArrayList<CartListModel> cartLists) {
        this.hideLoadingView();
        this.hideRetryView();

        if(cartLists.size()>0){
            this.showListView();
            this.cartListView.renderCartList(page,cartLists);
        }
        else{
            if(page==0){
                this.showEmptyView();
            }
            else{
                //todo signal somehow to add the end of list
            }
        }

    }

    private void showEmptyView() {
        this.cartListView.showEmpty();
    }
    private void hideEmptyView()
    {
        this.cartListView.hideEmpty();
    }


    public CartListPresenter(CartListView cartListView, GetCartListUseCase getCartListUseCase, CartListWishListModelDataMapper modelDataMapper) {
        this.cartListView = cartListView;
        this.getCartListUseCase = getCartListUseCase;
        this.modelDataMapper = modelDataMapper;
    }





    public void initialize(int page) {
        this.hideListView();
        this.hideEmptyView();

        this.hideRetryView();
        this.showLoadingView();
        this.loadList(page);
        System.out.println("HELLO WORLD");
    }
    private void loadList(int page) {
        this.getCartListUseCase.getCartList(page,createdCallback);
    }


    private void hideLoadingView() {
        this.cartListView.hideLoading();
    }

    private void showRetryView() {
        this.cartListView.showRetry();
    }


    private void showLoadingView() {
        this.cartListView.showLoading();
    }

    private void hideRetryView() {
        this.cartListView.hideRetry();
    }

    private void hideListView() {
        this.cartListView.hideCartList();
    }
    private void showListView() {
        this.cartListView.showCartList();
    }



    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    public void onProductItemClicked(int productId, int shopId) {
        this.cartListView.onProductItemClicked(shopId,productId);
    }

    public void onShopNameClicked(int shopId) {
        this.cartListView.onShopNameClicked(shopId);
    }

    public void onShopDeleteButtonClicked(int position) {
        this.cartListView.onDeleteShopButtonClicked(position);
    }

    public void onCheckoutButtonClicked(CartListModel cartListModel) {
        this.cartListView.onCheckoutButtonClicked(cartListModel);
    }

    public void onProductDeleteButtonClicked(CartListModel cartListModel, CartProductModel cartProduct,int position) {
        this.cartListView.onDeleteProductButtonClicked(cartListModel,cartProduct,position);
    }
}
