package numan947.com.bizzybay.presenter;

import com.example.HistoryList;
import com.example.exception.ErrorBundle;
import com.example.interactor.GetHistoryListUseCase;

import java.util.ArrayList;

import numan947.com.bizzybay.mapper.HistoryModelDataMapper;
import numan947.com.bizzybay.model.HistoryListModel;
import numan947.com.bizzybay.view.HistoryListView;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryListPresenter implements Presenter {

    private HistoryListView historyListView;
    private GetHistoryListUseCase historyListUseCase;
    private HistoryModelDataMapper historyModelDataMapper;


    private GetHistoryListUseCase.Callback providedCallback = new GetHistoryListUseCase.Callback() {
        @Override
        public void onHistoryListLoaded(int pageNumber, ArrayList<HistoryList> historyList) {
            HistoryListPresenter.this.successfulLoading(pageNumber,historyList);
        }

        @Override
        public void onError(ErrorBundle errorBundle) {
            HistoryListPresenter.this.unSuccessfulLoading(errorBundle);
        }
    };

    public HistoryListPresenter(HistoryListView historyListView, GetHistoryListUseCase historyListUseCase, HistoryModelDataMapper historyModelDataMapper) {

        if(historyListUseCase==null||historyListView==null||historyModelDataMapper==null)
            throw  new IllegalArgumentException("CONSTRUCTOR PARAMEtERS CAN't be null dumbass");

        this.historyListView = historyListView;
        this.historyListUseCase = historyListUseCase;
        this.historyModelDataMapper = historyModelDataMapper;
    }

    private void unSuccessfulLoading(ErrorBundle errorBundle) {
        this.hideLoadingView();
        this.hideListView();
        this.showRetryView();
    }

    private void successfulLoading(int pageNumber, ArrayList<HistoryList> historyList) {
        ArrayList<HistoryListModel>transformedModel = historyModelDataMapper.transform(historyList);

        this.hideRetryView();
        this.hideLoadingView();
        this.showListView();

        historyListView.renderHistoryList(pageNumber,transformedModel);
    }


    private void showRetryView() {
        historyListView.showRetry();
    }

    private void showListView() {
        historyListView.showList();
    }

    private void hideLoadingView() {
        historyListView.hideLoading();
    }


    @Override
    public void onResume() {
//todo do something here may be?
    }

    @Override
    public void onPause() {
//todo do something here may be?
    }

    public void onItemClicked(int orderId, int shopId, int productId) {
        //pass to the implementation, that'll be passed to the activity
        historyListView.viewProductHistory(orderId,shopId,productId);
    }

    public void initialize(int pageNumber) {
        this.loadHistoryList(pageNumber);
    }

    private void loadHistoryList(int pageNumber) {
        this.hideRetryView();
        this.hideListView();
        this.showLoadingView();
        this.getHistoryList(pageNumber);
    }

    private void getHistoryList(int pageNumber) {
        historyListUseCase.execute(pageNumber,providedCallback);
    }

    private void showLoadingView() {
        historyListView.showLoading();
    }

    private void hideListView() {
        historyListView.hideList();
    }

    private void hideRetryView() {
        historyListView.hideRetry();
    }
}
