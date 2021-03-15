package com.example.car_sharing_app.service;

import com.example.car_sharing_app.model.Vehicle;
import com.example.car_sharing_app.request.VehicleCoordinatesRequest;
import com.example.car_sharing_app.request.VehicleUpdateRequest;
import com.example.car_sharing_app.response.VehicleResponse;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    List<Vehicle> findAll(Integer userId);

    Vehicle findById(Integer id);

    Vehicle addVehicle(VehicleUpdateRequest vehicleUpdateRequest);

    VehicleResponse changeVehicleDetails(Integer id, VehicleCoordinatesRequest vehicleCoordinatesRequest);

    Vehicle deleteVehicle(Integer id);
}
