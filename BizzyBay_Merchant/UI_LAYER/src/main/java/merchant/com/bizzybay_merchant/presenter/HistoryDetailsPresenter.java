package merchant.com.bizzybay_merchant.presenter;

import com.merchant_example.HistoryDetails;
import com.merchant_example.exception.ErrorBundle;
import com.merchant_example.interactor.GetHistoryDetailsUseCase;

import merchant.com.bizzybay_merchant.mapper.HistoryModelDataMapper;
import merchant.com.bizzybay_merchant.model.HistoryDetailsModel;
import merchant.com.bizzybay_merchant.view.HistoryDetailsView;

/**
 * @author numan947
 * @since 5/11/17.<br>
 **/

public class HistoryDetailsPresenter implements Presenter {

    private GetHistoryDetailsUseCase getHistoryDetails;
    private HistoryModelDataMapper historyModelDataMapper;
    private HistoryDetailsView historyDetailsView;


    private GetHistoryDetailsUseCase.Callback createdCallback = new GetHistoryDetailsUseCase.Callback() {
        @Override
        public void onHistoryDetailsLoaded(HistoryDetails historyDetails) {
            HistoryDetailsPresenter.this.showHistoryDetails(historyDetails);
        }

        @Override
        public void onError(ErrorBundle errorBundle) {
            HistoryDetailsPresenter.this.showError(errorBundle);
        }
    };

    private void showError(ErrorBundle errorBundle) {
        this.hideLoading();
        this.hideMainView();
        this.showRetry();
    }

    private void showRetry() {
        this.historyDetailsView.showRetry();
    }

    private void showHistoryDetails(HistoryDetails historyDetails) {
        HistoryDetailsModel historyDetailsModel = this.historyModelDataMapper.transform(historyDetails);
        this.hideLoading();
        this.hideRetry();

        this.showMainView();
        historyDetailsView.renderHistoryDetails(historyDetailsModel);
    }

    private void showMainView() {
        historyDetailsView.showDetails();
    }

    private void hideLoading() {
        historyDetailsView.hideLoading();
    }

    public HistoryDetailsPresenter(HistoryDetailsView historyDetailsView, GetHistoryDetailsUseCase getHistoryDetails, HistoryModelDataMapper historyModelDataMapper) {
        this.getHistoryDetails = getHistoryDetails;
        this.historyModelDataMapper = historyModelDataMapper;
        this.historyDetailsView = historyDetailsView;
    }

    @Override
    public void onResume() {
        //todo do anything awesome here -_-, may be save the data here, not in the view? :/
    }

    @Override
    public void onPause() {
        //todo do anything awesome here -_-, may be save the data here, not in the view? :/
    }

    public void initialize(int orderId,int shopId,int productId) {
        this.hideRetry();
        this.hideMainView();
        this.showLoading();
        this.loadHistoryDetails(orderId,shopId,productId);
    }

    private void loadHistoryDetails(int orderId, int shopId, int productId) {
        this.getHistoryDetails.execute(orderId,shopId,productId, createdCallback);
    }

    private void showLoading() {
        historyDetailsView.showLoading();
    }

    private void hideMainView() {
        historyDetailsView.hideDetails();
    }

    private void hideRetry() {
        historyDetailsView.hideRetry();
    }

    public void viewProduct(int productId, int shopId) {
        //chain to fragment and that'd be chained to activity
        this.historyDetailsView.onProductNameClicked(productId,shopId);
    }

    public void viewShop(int shopId) {
        //chain to fragment and that'd be chained to activity
        this.historyDetailsView.onShopNameClicked(shopId);
    }
}
