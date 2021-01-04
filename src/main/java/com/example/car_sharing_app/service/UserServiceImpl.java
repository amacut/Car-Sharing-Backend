package com.example.car_sharing_app.service;

import com.example.car_sharing_app.model.User;
import com.example.car_sharing_app.repository.UserRepository;
import com.example.car_sharing_app.request.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(UserUpdateRequest userUpdateRequest) {
        User newUser = new User();
        this.setUser(newUser, userUpdateRequest);
        return newUser;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "User " + id + " does not exists"
        ));
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User userRepositoryByEmailAndPassword = userRepository.findByEmailAndPassword(email, password);
        if (userRepositoryByEmailAndPassword.getDeletedAt() != null) {
            return null;
        }
        return userRepositoryByEmailAndPassword;
    }


    @Override
    public User updateUser(Integer id, UserUpdateRequest userUpdateRequest) {
        User userToUpdate = userRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "User " + id + " does not exists"
        ));
        this.setUser(userToUpdate, userUpdateRequest);
        return userToUpdate;
    }

    @Override
    public User deleteUser(String email) {
        User userToDelete = userRepository.findByEmail(email);
        userToDelete.setDeletedAt(LocalDateTime.now());
        userRepository.save(userToDelete);
        return userToDelete;
    }

    private void setUser(User user, UserUpdateRequest updateRequest) {
        user.setFirstName(updateRequest.getFirstName());
        user.setLastName(updateRequest.getLastName());
        user.setGender(updateRequest.getGender());
        user.setEmail(updateRequest.getEmail());
        user.setPhoneNumber(updateRequest.getPhoneNumber());
        user.setPassword(updateRequest.getPassword());
        user.setBirthDate(LocalDate.parse(updateRequest.getBirthDate()));
        user.setCountry(updateRequest.getCountry());
        user.setStreet(updateRequest.getStreet());
        user.setHouseNoFlatNo(updateRequest.getHouseNoFlatNo());
        user.setPostcode(updateRequest.getPostcode());
        user.setCity(updateRequest.getCity());
        userRepository.save(user);
    }
}
