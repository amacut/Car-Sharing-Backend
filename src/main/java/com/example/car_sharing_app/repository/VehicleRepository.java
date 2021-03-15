package com.example.car_sharing_app.repository;

import com.example.car_sharing_app.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    List<Vehicle> findAllBy();
}
