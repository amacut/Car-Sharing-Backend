package com.example.car_sharing_app.service;

import com.example.car_sharing_app.model.Vehicle;
import com.example.car_sharing_app.repository.VehicleRepository;
import com.example.car_sharing_app.request.VehicleUpdateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Optional<Vehicle> findById(Integer id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public Vehicle addVehicle(VehicleUpdateRequest vehicleUpdateRequest) {
        Vehicle newVehicle = new Vehicle();
        this.setVehicle(newVehicle, vehicleUpdateRequest);
        return newVehicle;
    }

    @Override
    public Vehicle updateVehicle(Integer id, VehicleUpdateRequest vehicleUpdateRequest) {
        Vehicle vehicleToUpdate = vehicleRepository.findById(id).orElse(new Vehicle());
        this.setVehicle(vehicleToUpdate, vehicleUpdateRequest);
        return vehicleToUpdate;
    }

    @Override
    public Vehicle deleteVehicle(Integer id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(new Vehicle());
        vehicle.setDeletedAt(LocalDateTime.now());
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    private void setVehicle(Vehicle vehicle, VehicleUpdateRequest vehicleUpdateRequest) {
        vehicle.setVehicleModelId(vehicleUpdateRequest.getVehicleModelId());
        vehicle.setRegistration(vehicleUpdateRequest.getRegistration());
        vehicle.setMaxFuel(vehicleUpdateRequest.getMaxFuel());
        vehicle.setCurrentFuel(vehicleUpdateRequest.getCurrentFuel());
        vehicle.setMaxRange(vehicleUpdateRequest.getMaxRange());
        vehicle.setCurrentRange(vehicleUpdateRequest.getCurrentRange());
        vehicle.setLatitude(vehicleUpdateRequest.getLatitude());
        vehicle.setLongitude(vehicleUpdateRequest.getLongitude());
        vehicleRepository.save(vehicle);
    }
}
