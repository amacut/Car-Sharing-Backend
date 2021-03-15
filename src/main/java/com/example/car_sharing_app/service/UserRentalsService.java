package com.example.car_sharing_app.service;

import com.example.car_sharing_app.model.UserRental;
import com.example.car_sharing_app.model.Vehicle;
import com.example.car_sharing_app.request.UserRentalRequest;

public interface UserRentalsService {

    UserRental getById(Integer id);

    UserRental addNewUserRental(UserRentalRequest userRentalRequest);

    Double calculateRentalTotalPrice(Vehicle vehicle, UserRentalRequest userRentalRequest);
}
