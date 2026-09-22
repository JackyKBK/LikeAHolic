package org.champsoft.likeaholic.businessLogicLayer;

import org.champsoft.likeaholic.presentationLayer.UserRequestModel;
import org.champsoft.likeaholic.presentationLayer.UserResponseModel;

import java.util.List;

public interface UserService {

    List<UserResponseModel> getAllUsers();


    UserResponseModel getUserByUserId(String user_id);
    String updateUser(String user_id, UserRequestModel userRequestModel);
    String deleteUser(String user_id);
    String addUser(UserRequestModel userRequestModel);



}
