package org.champsoft.likeaholic.dataMapperLayer;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.champsoft.likeaholic.dataAccessLayer.User;
import org.champsoft.likeaholic.presentationLayer.UserRequestModel;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-21T18:42:33-0400",
    comments = "version: 1.6.0, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class UserRequestMapperImpl implements UserRequestMapper {

    @Override
    public User requestModelToEntity(UserRequestModel userRequestModel) {
        if ( userRequestModel == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( userRequestModel.getUserId() );
        user.setName( userRequestModel.getName() );
        user.setEmail( userRequestModel.getEmail() );
        user.setPassword( userRequestModel.getPassword() );

        return user;
    }

    @Override
    public List<User> requestModelListToEntity(List<UserRequestModel> userRequestModel) {
        if ( userRequestModel == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userRequestModel.size() );
        for ( UserRequestModel userRequestModel1 : userRequestModel ) {
            list.add( requestModelToEntity( userRequestModel1 ) );
        }

        return list;
    }
}
