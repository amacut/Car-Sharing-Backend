package com.example.car_sharing_app.controller;

import com.example.car_sharing_app.response.VehicleResponse;
import com.example.car_sharing_app.service.VehicleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class VehicleController {

    VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/all")
    public List<VehicleResponse> findAll() {
        return vehicleService.findAll().stream()
                .map(VehicleResponse::new)
                .collect(Collectors.toList());
    }
}
