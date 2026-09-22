package org.champsoft.likeaholic.businessLogicLayer;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.champsoft.likeaholic.dataAccessLayer.Post;
import org.champsoft.likeaholic.dataAccessLayer.PostRepository;
import org.champsoft.likeaholic.dataAccessLayer.User;
import org.champsoft.likeaholic.dataAccessLayer.UserRepository;
import org.champsoft.likeaholic.presentationLayer.UserRequestModel;
import org.champsoft.likeaholic.presentationLayer.UserResponseModel;
import org.champsoft.likeaholic.utilities.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final EntityManager entityManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PostRepository postRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<UserResponseModel> getAllUsers() {

        List<User> users = userRepository.findAll();


        List<UserResponseModel> response = new ArrayList<>();


        for (User user : users) {
            UserResponseModel dto = new UserResponseModel();
            BeanUtils.copyProperties(user, dto);
            response.add(dto);
        }


        return response;
    }

    @Override
    public UserResponseModel getUserByUserId(String userId) {

        User user = userRepository.getByUserId(userId);


        if (user == null) {
            throw new NotFoundException("User with userId " + userId + " not found.");
        }


        UserResponseModel dto = new UserResponseModel();


        BeanUtils.copyProperties(user, dto);


        return dto;
    }

    @Override
    public String addUser(UserRequestModel userData) {
        String userId = userData.getUserId();


        if (userRepository.getByUserId(userId) != null) {
            return "User with userId: " + userId + " already exists.";
        }


        User user = new User();
        user.setUserId(userData.getUserId());
        user.setName(userData.getName());
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());


        userRepository.save(user);

        return "User saved successfully.";
    }

    @Override
    public String updateUser(String userId, UserRequestModel userRequestModel) {

        User foundUser = userRepository.getByUserId(userId);
        if (foundUser == null) {
            throw new NotFoundException("UserId: " + userId + " not found.");
        }


        foundUser.setUserId(userRequestModel.getUserId());
        foundUser.setName(userRequestModel.getName());
        foundUser.setEmail(userRequestModel.getEmail());
        foundUser.setPassword(userRequestModel.getPassword());


        User updatedUser = userRepository.save(foundUser);
        return updatedUser != null ? "User updated successfully." : "Could not update user.";
    }

    @Transactional
    public String deleteUser(String userId) {
        User user = userRepository.getByUserId(userId);

        if (user == null) {
            return "User not found.";
        }

        List<Post> userPosts = postRepository.findAll().stream()
                 .filter(post -> post.getUserId().equals(userId))
                 .toList();

        for (Post post : userPosts) {
            entityManager.createNativeQuery("DELETE FROM likes_table WHERE post_id = ?")
                    .setParameter(1, post.getId())
                    .executeUpdate();
        }

        postRepository.deleteAll(userPosts);

        userRepository.delete(user);
        return "User deleted successfully.";
    }

    public Map<String, String> login(UserRequestModel loginRequest) {
        Map<String, String> response = new HashMap<>();

        User user = userRepository.getByUserId(loginRequest.getUserId());

        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            response.put("message", "Login successful");
        } else {
            response.put("message", "Invalid credentials");
        }

        return response;
    }
}