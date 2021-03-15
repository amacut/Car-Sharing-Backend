package com.example.car_sharing_app.request;

import lombok.Data;

@Data
public class ReservationRequest {

    private String userId;
    private Integer vehicleId;
    private Integer reservationTime;
}
