package com.example.car_sharing_app.service;

import com.example.car_sharing_app.model.User;
import com.example.car_sharing_app.request.UserUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User saveUser(User user);

    User addUser(UserUpdateRequest userUpdateRequest);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    Optional<User> findById(Integer id);

    User updateUser(Integer id, UserUpdateRequest userUpdateRequest);

    User deleteUser(Integer id);
}
