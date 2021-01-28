package com.example.car_sharing_app.repository;

import com.example.car_sharing_app.model.UserRental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRentalsRepository extends JpaRepository<UserRental, Integer> {
}
