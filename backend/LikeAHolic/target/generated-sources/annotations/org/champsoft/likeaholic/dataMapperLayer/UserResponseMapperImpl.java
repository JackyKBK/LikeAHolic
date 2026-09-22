package org.champsoft.likeaholic.dataMapperLayer;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.champsoft.likeaholic.dataAccessLayer.User;
import org.champsoft.likeaholic.presentationLayer.UserResponseModel;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-21T18:42:34-0400",
    comments = "version: 1.6.0, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class UserResponseMapperImpl implements UserResponseMapper {

    @Override
    public UserResponseModel entityToResponseModel(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseModel userResponseModel = new UserResponseModel();

        userResponseModel.setId( user.getId() );
        userResponseModel.setUserId( user.getUserId() );
        userResponseModel.setName( user.getName() );
        userResponseModel.setEmail( user.getEmail() );
        userResponseModel.setPassword( user.getPassword() );

        return userResponseModel;
    }

    @Override
    public List<UserResponseModel> entityListToResponseModelList(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponseModel> list = new ArrayList<UserResponseModel>( users.size() );
        for ( User user : users ) {
            list.add( entityToResponseModel( user ) );
        }

        return list;
    }
}
