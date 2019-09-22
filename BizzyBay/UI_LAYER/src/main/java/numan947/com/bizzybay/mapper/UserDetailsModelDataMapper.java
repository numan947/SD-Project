package numan947.com.bizzybay.mapper;

import com.example.UserDetails;

import numan947.com.bizzybay.model.UserDetailsModel;

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
