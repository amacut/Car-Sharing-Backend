package com.example.car_sharing_app.service;

import com.example.car_sharing_app.model.User;
import com.example.car_sharing_app.request.UserUpdateRequest;

public interface UserService {

    User addUser(UserUpdateRequest userUpdateRequest);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User updateUser(Integer id, UserUpdateRequest userUpdateRequest);

    User deleteUser(String email);
}
