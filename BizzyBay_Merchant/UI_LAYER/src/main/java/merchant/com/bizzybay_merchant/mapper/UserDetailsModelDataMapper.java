package merchant.com.bizzybay_merchant.mapper;

import com.merchant_example.UserDetails;

import merchant.com.bizzybay_merchant.model.UserDetailsModel;

/**
 * @author numan947
 * @since 7/7/17.<br>
 **/

public class UserDetailsModelDataMapper {
    public UserDetailsModel transform(UserDetails userDetails) {
        return new UserDetailsModel(userDetails.getUserId(),userDetails.getUserImageUrl(),userDetails.getAccountName(),
                userDetails.getUserName(),userDetails.getPhone(),userDetails.getEmail(),userDetails.getPassword(),userDetails.getWhatsapp(),
                userDetails.getFacebook());
    }
}
