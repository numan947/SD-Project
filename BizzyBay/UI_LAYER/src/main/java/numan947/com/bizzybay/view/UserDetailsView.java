package numan947.com.bizzybay.view;

import numan947.com.bizzybay.model.UserDetailsModel;

/**
 * @author numan947
 * @since 7/7/17.<br>
 **/

public interface UserDetailsView extends DataToLoad {
    void renderUserDetails(UserDetailsModel userDetailsModel);
    void showUserDetails();
    void hideUserDetails();
}
