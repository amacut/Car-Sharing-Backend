package com.example.car_sharing_app.controller;

import com.example.car_sharing_app.model.User;
import com.example.car_sharing_app.model.UserRental;
import com.example.car_sharing_app.model.Vehicle;
import com.example.car_sharing_app.request.UserRentalRequest;
import com.example.car_sharing_app.response.UserRentalsDetailsResponse;
import com.example.car_sharing_app.response.UserRentalsResponse;
import com.example.car_sharing_app.service.UserRentalsService;
import com.example.car_sharing_app.service.UserService;
import com.example.car_sharing_app.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RentalsController {

    UserRentalsService rentalsService;
    VehicleService vehicleService;
    UserService userService;

    public RentalsController(UserRentalsService rentalsService, VehicleService vehicleService, UserService userService) {
        this.rentalsService = rentalsService;
        this.vehicleService = vehicleService;
        this.userService = userService;
    }

    @GetMapping("/rentals/{id}")
    public List<UserRentalsResponse> getAllRentals(@PathVariable Integer id) {
        User userById = userService.findById(id);
        return userById.getRentalList().stream()
                .map(UserRentalsResponse::new)
                .collect(Collectors.toList());
    }
    @GetMapping("/rentalsDetails/{id}")
    public UserRentalsDetailsResponse getRentalsDetails(@PathVariable Integer id) {
        UserRental rental = rentalsService.getById(id);
        return new UserRentalsDetailsResponse(rental);
    }

    @PostMapping("/newRental")
    public UserRental addUserRental(@RequestBody UserRentalRequest userRentalRequest) {
        return rentalsService.addNewUserRental(userRentalRequest);
    }

    @PatchMapping("/calculateRent/{id}")
    public Double calculateRent(@PathVariable Integer id, @RequestBody UserRentalRequest userRentalRequest){
        Vehicle vehicle = vehicleService.findById(id);
        return rentalsService.calculateRentalTotalPrice(vehicle, userRentalRequest);
    }
}
