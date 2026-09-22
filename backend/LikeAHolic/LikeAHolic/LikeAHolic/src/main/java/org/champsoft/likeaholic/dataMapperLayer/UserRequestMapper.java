package org.champsoft.likeaholic.dataMapperLayer;


import org.champsoft.likeaholic.dataAccessLayer.User;
import org.champsoft.likeaholic.presentationLayer.UserRequestModel;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {


    User requestModelToEntity(UserRequestModel userRequestModel);

    List<User> requestModelListToEntity(List<UserRequestModel> userRequestModel);

}
