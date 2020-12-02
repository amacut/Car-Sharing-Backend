package com.example.car_sharing_app.controller;

import com.example.car_sharing_app.model.User;
import com.example.car_sharing_app.request.UserUpdateRequest;
import com.example.car_sharing_app.response.UserResponse;
import com.example.car_sharing_app.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

//    @PostMapping("/users")
//    public UserResponse addUser(@RequestBody UserUpdateRequest request) {
//        List<User> usersFromDbByEmail = userService.findByEmail(request.getEmail());
//        if (!usersFromDbByEmail.isEmpty()) {
//            return null;
//        }
//        return new UserResponse(userService.addUser(request));
//    }

    @PostMapping("/registeruser")
    public User registerUser(@RequestBody User user) throws Exception {
        String tempEmail = user.getEmail();
        if (tempEmail != null && !"".equals(tempEmail)) {
            User userFromDbByEmail = userService.findByEmail(tempEmail);
            if (userFromDbByEmail != null) {
                throw new Exception("user with " + tempEmail + " is already exist");
            }
        }
        User userObj = null;
        userObj = userService.saveUser(user);
        return userObj;
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user) throws Exception{
        String tempEmail = user.getEmail();
        String tempPass = user.getPassword();
        User userObj = null;
        if (tempEmail != null && tempPass != null) {
            userObj = userService.findByEmailAndPassword(tempEmail, tempPass);
        }
        if (userObj == null) {
            throw new Exception("Bad credentials");
        }
        return userObj;
    }

//    @GetMapping("/{id}")
//    public UserResponse findUserById(@PathVariable Integer id) {
//        return userService.findById(id)
//                .map(UserResponse::new)
//                .get();
//    }
//
//    @DeleteMapping("/{id}")
//    public UserResponse deleteUser(@PathVariable Integer id) {
//        return new UserResponse(userService.deleteUser(id));
//    }
//
//    @PatchMapping({"{/id}"})
//    public UserResponse updateUser(@PathVariable Integer id, @RequestBody UserUpdateRequest updateRequest) {
//        return new UserResponse(userService.updateUser(id, updateRequest));
//    }
}
