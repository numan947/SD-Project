package numan947.com.bizzybay.presenter;

import com.example.UserDetails;
import com.example.exception.ErrorBundle;
import com.example.interactor.GetUserDetailsUseCase;

import numan947.com.bizzybay.mapper.UserDetailsModelDataMapper;
import numan947.com.bizzybay.model.UserDetailsModel;
import numan947.com.bizzybay.view.UserDetailsView;

/**
 * @author numan947
 * @since 7/7/17.<br>
 **/

public class UserDetailsPresenter implements Presenter {
    private UserDetailsView userDetailsView;
    private GetUserDetailsUseCase getUserDetailsUseCase;
    private UserDetailsModelDataMapper modelDataMapper;

    public UserDetailsPresenter(UserDetailsView userDetailsView, GetUserDetailsUseCase getUserDetailsUseCase, UserDetailsModelDataMapper modelDataMapper) {
        this.userDetailsView = userDetailsView;
        this.getUserDetailsUseCase = getUserDetailsUseCase;
        this.modelDataMapper = modelDataMapper;
    }


    private GetUserDetailsUseCase.Callback createdCallback = new GetUserDetailsUseCase.Callback() {
        @Override
        public void onUserDetailsLoaded(UserDetails userDetails) {
            UserDetailsModel userDetailsModel = modelDataMapper.transform(userDetails);
            UserDetailsPresenter.this.renderUserDetails(userDetailsModel);
        }

        @Override
        public void onError(ErrorBundle errorBundle) {
            UserDetailsPresenter.this.renderError(errorBundle);
        }
    };

    private void renderError(ErrorBundle errorBundle) {
        this.hideLoadingView();
        this.hideDetailsView();
        this.showRetryView();
    }


    private void renderUserDetails(UserDetailsModel userDetailsModel) {
        this.hideRetryView();
        this.hideLoadingView();
        this.showDetailsView();
        userDetailsView.renderUserDetails(userDetailsModel);
    }

    private void showRetryView() {
        userDetailsView.showRetry();
    }

    private void showDetailsView() {
        userDetailsView.showUserDetails();
    }

    private void hideLoadingView() {
        userDetailsView.hideLoading();
    }

    public void initialize(int userId,int flag) { //flag indicates whether to reload from internet or just use the cached data
        this.loadUserDetails(userId);
    }

    private void loadUserDetails(int userId) {
        this.hideRetryView();
        this.hideDetailsView();
        this.showLoadingView();
        this.getUserDetailsUseCase.getUserDetails(userId,createdCallback);
    }

    private void showLoadingView() {
        this.userDetailsView.showLoading();
    }

    private void hideDetailsView() {
        this.userDetailsView.hideUserDetails();
    }

    private void hideRetryView() {
       this.userDetailsView.hideRetry();
    }
    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
}
