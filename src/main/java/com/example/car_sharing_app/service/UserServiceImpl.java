package com.example.car_sharing_app.service;

import com.example.car_sharing_app.model.User;
import com.example.car_sharing_app.repository.UserRepository;
import com.example.car_sharing_app.request.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
       return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
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
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(Integer id, UserUpdateRequest userUpdateRequest) {
       User userToUpdate = userRepository.findById(id).get();
       this.setUser(userToUpdate, userUpdateRequest);
        return userToUpdate;
    }

    @Override
    public User deleteUser(Integer id) {
        User user = userRepository.findById(id).get();
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
        return user;
    }

    private void setUser(User user, UserUpdateRequest updateRequest) {
        user.setId(updateRequest.getUserId());
        user.setFirstName(updateRequest.getFirstName());
        user.setLastName(updateRequest.getLastName());
        user.setEmail(updateRequest.getEmail());
        user.setPhoneNumber(updateRequest.getPhoneNumber());
        user.setPassword(updateRequest.getPassword());
        user.setBirthDate(updateRequest.getBirthDate());
        user.setCountry(updateRequest.getCountry());
        user.setStreet(updateRequest.getStreet());
        user.setHouseNoFlatNo(updateRequest.getHouseNoFlatNo());
        user.setPostcode(updateRequest.getPostcode());
        user.setCity(updateRequest.getCity());
    }
}
