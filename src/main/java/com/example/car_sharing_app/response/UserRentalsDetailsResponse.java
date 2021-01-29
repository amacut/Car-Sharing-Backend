package com.example.car_sharing_app.response;

import com.example.car_sharing_app.helper.DateHelper;
import com.example.car_sharing_app.model.UserRental;
import lombok.Data;

@Data
public class UserRentalsDetailsResponse {

    private Integer id;
    private String rentDate;
    private String returnDate;
    private String origin;
    private String destination;
    private String drivingTime;
    private Double drivingPrice;
    private Double distance;
    private Double distancePrice;
    private String stopOverTime;
    private Double stopOverPrice;
    private Double totalPrice;
    private String registration;
    private String brand;
    private String model;
    private String vehicleType;
    private Double vehicleTypeDrivingPrice;
    private Double vehicleTypeStopOverPrice;
    private Double vehicleTypeDistancePrice;

    public UserRentalsDetailsResponse(UserRental userRental) {
        this.id = userRental.getId();
        this.rentDate = DateHelper.dateTimeToString(userRental.getRentDate());
        this.returnDate = DateHelper.dateTimeToString(userRental.getReturnDate());
        this.origin = userRental.getOrigin();
        this.destination = userRental.getDestination();
        this.drivingTime = DateHelper.durationToString(userRental.getDrivingTime());
        this.drivingPrice = userRental.getDrivingPrice();
        this.distance = userRental.getDistance();
        this.distancePrice = userRental.getDistancePrice();
        this.stopOverTime = DateHelper.durationToString(userRental.getStopOverTime());
        this.stopOverPrice = userRental.getStopOverPrice();
        this.totalPrice = userRental.getTotalPrice();
        this.registration = userRental.getVehicle().getRegistration();
        this.brand = userRental.getVehicle().getVehicleModel().getBrand();
        this.model = userRental.getVehicle().getVehicleModel().getModel();
        this.vehicleType = userRental.getVehicle().getVehicleModel().getVehicleType().getName();
        this.vehicleTypeDrivingPrice = userRental.getVehicle().getVehicleModel().getVehicleType().getDrivingPrice();
        this.vehicleTypeStopOverPrice = userRental.getVehicle().getVehicleModel().getVehicleType().getStopOverPrice();
        this.vehicleTypeDistancePrice = userRental.getVehicle().getVehicleModel().getVehicleType().getDistancePrice();
    }
}
