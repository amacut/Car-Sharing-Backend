package com.example.car_sharing_app.service;

import com.example.car_sharing_app.model.UserRental;
import com.example.car_sharing_app.repository.UserRentalsRepository;
import com.example.car_sharing_app.request.UserRentalRequest;
import org.springframework.stereotype.Service;

@Service
public class UserRentalsServiceImpl implements UserRentalsService {

    UserRentalsRepository rentalsRepository;

    public UserRentalsServiceImpl(UserRentalsRepository rentalsRepository) {
        this.rentalsRepository = rentalsRepository;
    }

    @Override
    public UserRental getById(Integer id) {
        return rentalsRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Wrong id" + id
        ));
    }

    @Override
    public UserRental addNewUserRental(UserRentalRequest userRentalRequest) {
        return null;
    }
}
