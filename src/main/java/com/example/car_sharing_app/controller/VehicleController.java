package com.example.car_sharing_app.controller;

import com.example.car_sharing_app.model.Vehicle;
import com.example.car_sharing_app.request.VehicleCoordinatesRequest;
import com.example.car_sharing_app.request.VehicleUpdateRequest;
import com.example.car_sharing_app.response.VehicleResponse;
import com.example.car_sharing_app.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class VehicleController {

    VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/all/{userId}")
//    @CrossOrigin(origins = "http://localhost:4200")
    public List<VehicleResponse> findAll(@PathVariable Integer userId) {
        return vehicleService.findAll(userId).stream()
                .map(VehicleResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public VehicleResponse findVehicleById(@PathVariable Integer id) {
        Vehicle vehicle = vehicleService.findById(id);
        return new VehicleResponse(vehicle);
    }

}
