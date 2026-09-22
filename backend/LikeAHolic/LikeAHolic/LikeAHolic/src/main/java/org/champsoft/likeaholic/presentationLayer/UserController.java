package org.champsoft.likeaholic.presentationLayer;

import org.champsoft.likeaholic.businessLogicLayer.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<UserResponseModel> getAllUsers() {
        return this.userService.getAllUsers();
    }


    @GetMapping("/users/{userId}")
    public UserResponseModel getUserByUserId(@PathVariable String userId) {
        return this.userService.getUserByUserId(userId);
    }
    @PostMapping("/users/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody UserRequestModel loginRequest) {
        Map<String, String> loginResult = this.userService.login(loginRequest);

        if ("Login successful".equals(loginResult.get("message"))) {
            return ResponseEntity.ok(loginResult);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResult);
        }
    }
    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody UserRequestModel newUserData) {

        String responseMessage = this.userService.addUser(newUserData);


        if (responseMessage.contains("successfully")) {

            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
        }
    }


    @PutMapping("/user/{userId}")
    public String updateUser(@PathVariable String userId, @RequestBody UserRequestModel updatedUserData) {
        return this.userService.updateUser(userId, updatedUserData);
    }


    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return this.userService.deleteUser(userId);
    }
}