package com.example.car_sharing_app.controller;

import com.example.car_sharing_app.model.User;
import com.example.car_sharing_app.request.UserUpdateRequest;
import com.example.car_sharing_app.response.UserResponse;
import com.example.car_sharing_app.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{email}")
    public UserResponse findUser(@PathVariable String email) {
        User userByEmail = userService.findByEmail(email);
        return new UserResponse(userByEmail);

    }

    @PostMapping("/registration")
    public UserResponse registerUser(@RequestBody UserUpdateRequest updateRequest) throws Exception {
        String tempEmail = updateRequest.getEmail();
        if (tempEmail != null && !"".equals(tempEmail)) {
            User userFromDbByEmail = userService.findByEmail(tempEmail);
            if (userFromDbByEmail != null) {
                throw new Exception("user with " + tempEmail + " is already exist");
            }
        }
        User userObj = null;
        userObj = userService.addUser(updateRequest);
        System.out.println(userObj.toString());
        return new UserResponse(userObj);
    }

    @PostMapping("/login")
    public UserResponse loginUser(@RequestBody User user) throws Exception {
        String tempEmail = user.getEmail();
        String tempPass = user.getPassword();
        User userObj = null;
        if (tempEmail != null && tempPass != null) {
            userObj = userService.findByEmailAndPassword(tempEmail, tempPass);
        }
        if (userObj == null) {
            throw new Exception("Bad credentials");
        }
        return new UserResponse(userObj);
    }

    @DeleteMapping("/{email}")
    public UserResponse deleteUser(@PathVariable String email) {
        return new UserResponse(userService.deleteUser(email));
    }

    @PatchMapping("/{id}")
    public UserResponse updateUser(@PathVariable Integer id, @RequestBody UserUpdateRequest updateRequest) {
        return new UserResponse(userService.updateUser(id, updateRequest));
    }
}
