package merchant.com.bizzybay_merchant.presenter;

import com.merchant_example.UserDetails;
import com.merchant_example.exception.ErrorBundle;
import com.merchant_example.interactor.GetUserDetailsUseCase;

import merchant.com.bizzybay_merchant.mapper.UserDetailsModelDataMapper;
import merchant.com.bizzybay_merchant.model.UserDetailsModel;
import merchant.com.bizzybay_merchant.view.UserDetailsView;

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
