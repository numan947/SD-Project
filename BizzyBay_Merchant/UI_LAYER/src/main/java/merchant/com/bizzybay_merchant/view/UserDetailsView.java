package merchant.com.bizzybay_merchant.view;

import merchant.com.bizzybay_merchant.model.UserDetailsModel;

/**
 * @author numan947
 * @since 7/7/17.<br>
 **/

public interface UserDetailsView extends DataToLoad {
    void renderUserDetails(UserDetailsModel userDetailsModel);
    void showUserDetails();
    void hideUserDetails();
}
