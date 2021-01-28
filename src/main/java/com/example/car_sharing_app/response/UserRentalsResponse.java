package com.example.car_sharing_app.response;

import com.example.car_sharing_app.helper.DateHelper;
import com.example.car_sharing_app.model.UserRental;
import lombok.Data;


@Data
public class UserRentalsResponse {

    private Integer id;
    private Integer vehicleId;
    private String rentDate;
    private String returnDate;
    private Double totalPrice;

    public UserRentalsResponse(UserRental userRental) {
        this.id = userRental.getId();
        this.vehicleId = userRental.getVehicle().getId();
        this.rentDate = DateHelper.dateTimeToString(userRental.getRentDate());
        this.returnDate = DateHelper.dateTimeToString(userRental.getReturnDate());
        this.totalPrice = userRental.getTotalPrice();
    }
}
