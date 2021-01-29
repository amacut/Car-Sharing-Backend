package com.example.car_sharing_app.request;

import lombok.Data;

@Data
public class UserRentalRequest {


    private Integer userId;
    private String origin;
    private String destination;
    private Integer drivingTime;
    private Double distance;
    private Integer stopOverTime;
    private Integer vehicleId;
}
