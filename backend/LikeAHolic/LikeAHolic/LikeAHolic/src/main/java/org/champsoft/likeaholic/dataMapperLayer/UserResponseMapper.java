package org.champsoft.likeaholic.dataMapperLayer;

import org.champsoft.likeaholic.dataAccessLayer.User;
import org.champsoft.likeaholic.presentationLayer.UserResponseModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserResponseMapper {

    UserResponseModel entityToResponseModel(User user);
    List<UserResponseModel> entityListToResponseModelList(List<User> users);
}
