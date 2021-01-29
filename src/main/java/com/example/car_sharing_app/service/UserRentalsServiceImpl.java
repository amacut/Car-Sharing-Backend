package com.example.car_sharing_app.service;

import com.example.car_sharing_app.model.User;
import com.example.car_sharing_app.model.UserRental;
import com.example.car_sharing_app.model.Vehicle;
import com.example.car_sharing_app.model.VehicleType;
import com.example.car_sharing_app.repository.UserRentalsRepository;
import com.example.car_sharing_app.repository.UserRepository;
import com.example.car_sharing_app.repository.VehicleRepository;
import com.example.car_sharing_app.request.UserRentalRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserRentalsServiceImpl implements UserRentalsService {

    UserRentalsRepository rentalsRepository;
    VehicleRepository vehicleRepository;
    UserService userService;
    UserRepository userRepository;
    UserWalletService walletService;

    private Double totalDrivingPrice;
    private Double totalDistancePrice;
    private Double totalStopOverPrice;

    public UserRentalsServiceImpl(UserRentalsRepository rentalsRepository,
                                  VehicleRepository vehicleRepository,
                                  UserService userService,
                                  UserRepository userRepository,
                                  UserWalletService walletService) {
        this.rentalsRepository = rentalsRepository;
        this.vehicleRepository = vehicleRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.walletService = walletService;
    }

    @Override
    public UserRental getById(Integer id) {
        return rentalsRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "Wrong id" + id
        ));
    }

    @Override
    public UserRental addNewUserRental(UserRentalRequest userRentalRequest) {
        UserRental newUserRental = new UserRental();

        Integer userId = userRentalRequest.getUserId();
        User userById = userService.findById(userId);
        this.setRentalDetails(newUserRental, userRentalRequest, userById);

        Boolean isUserHasMoney = walletService.debitsWallet(userId, newUserRental.getTotalPrice());
        if (isUserHasMoney) {
            List<UserRental> userRentalList = userById.getRentalList();
            userRentalList.add(newUserRental);

            userRepository.save(userById);

            return newUserRental;
        } else {
            throw new IllegalStateException("Brak środków na koncie");
        }

    }


    public Double calculateRentalTotalPrice(Integer vehicleId, UserRentalRequest userRentalRequest) {
        Vehicle vehicle = this.getVehicle(vehicleId);
        VehicleType vehicleType = vehicle.getVehicleModel().getVehicleType();

        Double drivingPrice = vehicleType.getDrivingPrice();
        Double distancePrice = vehicleType.getDistancePrice();
        Double stopOverPrice = vehicleType.getStopOverPrice();

        totalDrivingPrice = Math.ceil(userRentalRequest.getDrivingTime()/60.0) * drivingPrice;
        totalDistancePrice = userRentalRequest.getDistance() * distancePrice/1000;
        totalStopOverPrice = Math.ceil(userRentalRequest.getStopOverTime()/60.0) * stopOverPrice;

        return totalDrivingPrice + totalDistancePrice + totalStopOverPrice;
    }

    private void setRentalDetails(UserRental newUserRental, UserRentalRequest userRentalRequest, User user) {
        Integer vehicleId = userRentalRequest.getVehicleId();
        Vehicle vehicle = this.getVehicle(vehicleId);

        Double totalPrice = this.calculateRentalTotalPrice(vehicleId, userRentalRequest);
        Integer drivingTime = userRentalRequest.getDrivingTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime rentDate = LocalDateTime.now();
        LocalDateTime returnDateTime = rentDate.plusSeconds(drivingTime);
        String formatRentDate = LocalDateTime.now().format(formatter);
        String formatReturnDate = returnDateTime.format(formatter);
        
        newUserRental.setUser(user);
        newUserRental.setRentDate(LocalDateTime.parse(formatRentDate, formatter));
        newUserRental.setReturnDate(LocalDateTime.parse(formatReturnDate, formatter));
        newUserRental.setOrigin(userRentalRequest.getOrigin());
        newUserRental.setDestination(userRentalRequest.getDestination());
        newUserRental.setDrivingTime(drivingTime);
        newUserRental.setDrivingPrice(totalDrivingPrice);
        newUserRental.setDistance(userRentalRequest.getDistance());
        newUserRental.setDistancePrice(totalDistancePrice);
        newUserRental.setStopOverTime(userRentalRequest.getStopOverTime());
        newUserRental.setStopOverPrice(totalStopOverPrice);
        newUserRental.setTotalPrice(totalPrice);
        newUserRental.setActive(false);
        newUserRental.setVehicle(vehicle);
//        rentalsRepository.save(newUserRental);
    }

    private Vehicle getVehicle(Integer vehicleId) {
        return vehicleRepository.findById(vehicleId).orElseThrow(() -> new IllegalStateException(
                "Wrong vehicleId " + vehicleId
        ));
    }
}
