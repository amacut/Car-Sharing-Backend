package com.example.car_sharing_app.repository;

import com.example.car_sharing_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

}
