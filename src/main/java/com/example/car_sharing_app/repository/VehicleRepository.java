package com.example.car_sharing_app.repository;

import com.example.car_sharing_app.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    List<Vehicle> findAll();
}
