package numan947.com.data_layer.entity.mapper;

import com.example.UserDetails;

import numan947.com.data_layer.entity.UserDetailsEntity;

/**
 * @author numan947
 * @since 7/7/17.<br>
 **/

public class UserDetailsEntityDataMapper {
    public UserDetails transform(UserDetailsEntity userDetailsEntity) {
        return new UserDetails(userDetailsEntity.getUserId(),userDetailsEntity.getUserImageUrl(),userDetailsEntity.getAccountName(),userDetailsEntity.getUserName(),
                userDetailsEntity.getPhone(),userDetailsEntity.getEmail(),userDetailsEntity.getPassword(),userDetailsEntity.getWhatsapp(),userDetailsEntity.getFacebook());
    }
}
