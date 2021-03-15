package com.example.car_sharing_app.service;

import com.example.car_sharing_app.helper.VehicleHelper;
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
    VehicleService vehicleService;
    UserRepository userRepository;
    UserWalletService walletService;

    private Double totalDrivingPrice;
    private Double totalDistancePrice;
    private Double totalStopOverPrice;

    public UserRentalsServiceImpl(UserRentalsRepository rentalsRepository,
                                  VehicleRepository vehicleRepository,
                                  UserService userService,
                                  VehicleService vehicleService,
                                  UserRepository userRepository,
                                  UserWalletService walletService) {
        this.rentalsRepository = rentalsRepository;
        this.vehicleRepository = vehicleRepository;
        this.userService = userService;
        this.vehicleService = vehicleService;
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
        Integer vehicleId = userRentalRequest.getVehicleId();
        Vehicle vehicleById = vehicleService.findById(vehicleId);
        this.setRentalDetails(newUserRental, userRentalRequest, userById, vehicleById);
        Double fuelAfterRental = this.updateVehicleAfterRental(vehicleById, userRentalRequest);

        Boolean isUserHasMoney = walletService.debitsWallet(userId, newUserRental.getTotalPrice());
        if (isUserHasMoney) {
            List<UserRental> userRentalList = userById.getRentalList();
            userRentalList.add(newUserRental);
            vehicleById.setCurrentFuel(fuelAfterRental);
            vehicleById.setLatitude(userRentalRequest.getLatitude());
            vehicleById.setLongitude(userRentalRequest.getLongitude());
            vehicleRepository.save(vehicleById);

            userRepository.save(userById);

            return newUserRental;
        } else {
            throw new IllegalStateException("Brak środków na koncie");
        }

    }


    public Double calculateRentalTotalPrice(Vehicle vehicle, UserRentalRequest userRentalRequest) {
        VehicleType vehicleType = vehicle.getVehicleModel().getVehicleType();

        Double drivingPrice = vehicleType.getDrivingPrice();
        Double distancePrice = vehicleType.getDistancePrice();
        Double stopOverPrice = vehicleType.getStopOverPrice();

        totalDrivingPrice = Math.ceil(userRentalRequest.getDrivingTime() / 60.0) * drivingPrice;
        totalDistancePrice = userRentalRequest.getDistance() * distancePrice / 1000;
        totalStopOverPrice = Math.ceil(userRentalRequest.getStopOverTime() / 60.0) * stopOverPrice;

        return totalDrivingPrice + totalDistancePrice + totalStopOverPrice;
    }

    private void setRentalDetails(UserRental newUserRental, UserRentalRequest userRentalRequest, User user, Vehicle vehicle) {

        Double distance = userRentalRequest.getDistance();


        Double totalPrice = this.calculateRentalTotalPrice(vehicle, userRentalRequest);
        Integer drivingTime = userRentalRequest.getDrivingTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime rentDate = LocalDateTime.now();
        LocalDateTime returnDateTime = rentDate.plusSeconds(drivingTime);
        String formatRentDate = rentDate.format(formatter);
        String formatReturnDate = returnDateTime.format(formatter);

        newUserRental.setUser(user);
        newUserRental.setRentDate(LocalDateTime.parse(formatRentDate, formatter));
        newUserRental.setReturnDate(LocalDateTime.parse(formatReturnDate, formatter));
        newUserRental.setOrigin(userRentalRequest.getOrigin());
        newUserRental.setDestination(userRentalRequest.getDestination());
        newUserRental.setDrivingTime(drivingTime);
        newUserRental.setDrivingPrice(totalDrivingPrice);
        newUserRental.setDistance(distance);
        newUserRental.setDistancePrice(totalDistancePrice);
        newUserRental.setStopOverTime(userRentalRequest.getStopOverTime());
        newUserRental.setStopOverPrice(totalStopOverPrice);
        newUserRental.setTotalPrice(totalPrice);
        newUserRental.setVehicle(vehicle);
//        rentalsRepository.save(newUserRental);
    }

    private Double updateVehicleAfterRental(Vehicle vehicle, UserRentalRequest userRentalRequest) {
        Double currentFuel = VehicleHelper.updateCurrentFuel(vehicle, userRentalRequest.getDistance());
        return currentFuel;
    }

}
