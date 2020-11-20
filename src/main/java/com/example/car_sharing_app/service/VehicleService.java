package com.example.car_sharing_app.service;

import com.example.car_sharing_app.model.Vehicle;
import com.example.car_sharing_app.request.VehicleUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    List<Vehicle> findAll();

    Optional<Vehicle> findById(Integer id);

    Vehicle addVehicle(VehicleUpdateRequest vehicleUpdateRequest);

    Vehicle updateVehicle(Integer id, VehicleUpdateRequest vehicleUpdateRequest);

    Vehicle deleteVehicle(Integer id);
}
